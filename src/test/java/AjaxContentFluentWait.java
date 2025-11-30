import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class AjaxContentFluentWait extends BaseTest {

    @Test
    public void testAjaxContentLoadingWithFluentWait() {
        // Navigate to the Hidden Elements & AJAX page
        String url = "https://testautomationpractice.blogspot.com/p/gui-elements-ajax-hidden.html";
        driver.get(url);

        // Locate and click the "Load AJAX Content" button
        WebElement loadButton = driver.findElement(By.xpath("//button[contains(text(), 'Load AJAX Content')]"));
        loadButton.click();

        // Use FluentWait to wait for the AJAX content to load
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        WebElement ajaxContent;
        try {
            ajaxContent = wait.until(new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver driver) {
                    WebElement element = driver.findElement(By.xpath("//div[@id='ajaxContent']//h2"));
                    return element.isDisplayed() ? element : null;
                }
            });
        } catch (TimeoutException e) {
            Assert.fail("AJAX content did not load within 10 seconds: " + e.getMessage());
            return; // Prevent further execution if the wait fails
        }

        // Verify the loaded content
        String loadedText = ajaxContent.getText();
        Assert.assertTrue(loadedText.contains("AJAX Content Loaded"), "AJAX content should contain 'AJAX Content Loaded'");
        System.out.println("AJAX content loaded successfully: " + loadedText);
    }
}