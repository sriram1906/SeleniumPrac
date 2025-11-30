import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DoubleClickTest extends BaseTest {

    @Test
    public void doubleClickTest() {
        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement field1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("field1")));
        WebElement field2 = driver.findElement(By.id("field2"));
        WebElement copyButton = driver.findElement(By.xpath("//button[text()='Copy Text']"));

        String initialText = field1.getAttribute("value");
        Assert.assertEquals(initialText, "Hello World!", "Field1 should contain 'Hello World!'");

        Actions actions = new Actions(driver);
        actions.doubleClick(copyButton).perform();

        wait.until(ExpectedConditions.attributeToBe(field2, "value", initialText));
        Assert.assertEquals(field2.getAttribute("value"), "Hello World!", "Field2 should contain 'Hello World!'");
        System.out.println("Double-click successful: Text copied to Field2");
    }
}