import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class AlertsTests extends BaseTest {

    private void navigateToPage() {
        driver.get("https://testautomationpractice.blogspot.com/");
    }

    @Test
    public void testSimpleAlert() {
        navigateToPage();

        // Click the "Simple Alert" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement simpleAlertButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("alertBtn")));
        System.out.println("Clicking Simple Alert button...");
        simpleAlertButton.click();

        // Handle the alert
        Alert simpleAlert;
        try {
            simpleAlert = wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            Assert.fail("Failed to find alert within 10 seconds: " + e.getMessage());
            return; // Exit the test to avoid further execution
        }

        String alertText = simpleAlert.getText();
        Assert.assertEquals(alertText, "I am an alert box!", "Simple Alert text should match");
        simpleAlert.accept();

        System.out.println("Simple Alert handled successfully: " + alertText);
    }

    @Test
    public void testConfirmationAlert() {
        navigateToPage();

        // Click the "Confirmation Alert" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmAlertButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("confirmBtn")));
        confirmAlertButton.click();

        // Handle the alert - Accept (OK)
        Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
        confirmAlert.accept();

        // Verify the result
        WebElement resultText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("demo")));
        String result = resultText.getText();
        Assert.assertEquals(result, "You pressed OK!", "Confirmation Alert OK result should match");

        // Click again to test Cancel
        confirmAlertButton.click();
        confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
        confirmAlert.dismiss();

        // Wait for the text to update and then retrieve the element
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("demo"), "You pressed Cancel!"));
        resultText = driver.findElement(By.id("demo"));
        result = resultText.getText();
        Assert.assertEquals(result, "You pressed Cancel!", "Confirmation Alert Cancel result should match");

        System.out.println("Confirmation Alert handled successfully: OK and Cancel tested");
    }

    @Test
    public void testPromptAlert() {
        navigateToPage();

        // Click the "Prompt Alert" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement promptAlertButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("promptBtn")));
        promptAlertButton.click();

        // Handle the alert - Enter text and accept
        Alert promptAlert = wait.until(ExpectedConditions.alertIsPresent());
        String inputText = "John Doe";
        promptAlert.sendKeys(inputText);
        promptAlert.accept();

        // Wait for the text to update and then retrieve the element
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("demo"), "Hello " + inputText));
        WebElement resultText = driver.findElement(By.id("demo"));
        String result = resultText.getText();
        Assert.assertTrue(result.contains("Hello " + inputText), "Prompt Alert result should contain entered text");

        System.out.println("Prompt Alert handled successfully: " + result);
    }
}