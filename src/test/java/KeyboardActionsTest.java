import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class KeyboardActionsTest extends BaseTest {

    @Test
    public void selectCountryWithKeyboard() {
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement autocomplete = wait.until(ExpectedConditions.elementToBeClickable(By.id("autocomplete")));
        autocomplete.sendKeys("ind");

        // Wait for the suggestion list to render after typing
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-1")));

        Actions actions = new Actions(driver);
        // Move through suggestions with ARROW_DOWN until the field reads "India" (or attempts exhausted)
        for (int i = 0; i < 10; i++) { // small safety cap on attempts
            actions.sendKeys(Keys.ARROW_DOWN).perform();
            if ("India".equalsIgnoreCase(autocomplete.getAttribute("value"))) {
                break;
            }
        }

        actions.sendKeys(Keys.ENTER).perform();
        Assert.assertEquals(autocomplete.getAttribute("value"), "India", "Autocomplete value should be India after selection.");
    }
}
