import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class JavascriptExecutorUtilities extends BaseTest {

    /**
     * General note:
     *  - JavascriptExecutor is a LAST RESORT helper when normal WebDriver actions
     *    (click, sendKeys, scroll, etc.) are not working reliably.
     *  - Typical use cases:
     *      * Scroll an element into view on very long / complex pages
     *      * Click an element when normal click() is intercepted by overlays/ads
     *      * Set or read values directly from the DOM (e.g. hidden/readonly fields)
     */

    /**
     * Example 1: Scroll an element into view using JavaScript.
     *
     * UI element:
     *   - Page: https://rahulshettyacademy.com/AutomationPractice/
     *   - Element: "Mouse Hover" section, <div> with id = "mousehover"
     *
     * When this is useful:
     *  - The element is far down the page and not visible.
     *  - Normal WebDriver actions fail with "element not clickable" because it's off-screen.
     */
    @Test
    public void scrollToElementWithJavascript() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement mouseHoverSection = driver.findElement(By.id("mousehover"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", mouseHoverSection);

        // After scrolling, the element should be in view and interactable
        Assert.assertTrue(mouseHoverSection.isDisplayed(),
                "Expected mouse hover section to be visible after JS scroll");
    }

    /**
     * Example 2: Click an element using JavaScript as a fallback.
     *
     * UI element:
     *   - Page: https://rahulshettyacademy.com/AutomationPractice/
     *   - Element: Checkbox "Option1", <input type="checkbox"> with id = "checkBoxOption1"
     *
     * When this is useful:
     *  - Normal element.click() throws ElementClickInterceptedException
     *    (e.g. because of sticky banners, ads, or overlapping elements).
     *  - You still want to click the element without changing the app.
     *
     * Here we deliberately wrap click() in a try/catch and fall back to JS click.
     */
    @Test
    public void clickCheckboxWithJavascriptIfNormalClickFails() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement checkbox = driver.findElement(By.id("checkBoxOption1"));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            // First try the normal Selenium click
            checkbox.click();
        } catch (ElementClickInterceptedException e) {
            // If something is overlapping / intercepting the click, fall back to JS
            js.executeScript("arguments[0].click();", checkbox);
        }

        Assert.assertTrue(checkbox.isSelected(),
                "Checkbox should be selected after normal or JS click");
    }

    /**
     * Example 3: Set an input's value using JavaScript.
     *
     * UI element:
     *   - Page: https://rahulshettyacademy.com/AutomationPractice/
     *   - Element: Name input text box, <input> with id = "name"
     *
     * When this is useful:
     *  - The element is readonly/disabled or hidden but you need to
     *    set its value for a very specific scenario (e.g. quick debug, spike).
     *  - NOTE: This bypasses normal user flows, so use carefully.
     */
    @Test
    public void setInputValueWithJavascript() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebElement nameInput = driver.findElement(By.id("name"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='Sriram via JS';", nameInput);

        String value = nameInput.getAttribute("value");
        Assert.assertEquals(value, "Sriram via JS",
                "Expected input value to be set via JavaScript");
    }
}
