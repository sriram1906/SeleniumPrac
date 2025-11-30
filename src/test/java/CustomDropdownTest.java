import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CustomDropdownTest extends BaseTest {

    @Test
    public void selectValueFromCustomDropdown() {
        driver.get("https://demoqa.com/select-menu");

        // Select Value dropdown (custom react select)
        By valueDropdown = By.xpath("//div[@id='withOptGroup']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(valueDropdown)).click();

        By optionLocator = By.xpath("//div[contains(@class,'option') and normalize-space()='Group 2, option 1']");
        // Scroll the option into view and click via JS to avoid bottom anchor ads intercepting the click
        JavascriptExecutor js = (JavascriptExecutor) driver;
        var option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        js.executeScript("arguments[0].scrollIntoView({block:'center'}); arguments[0].click();", option);

        By selectedValueLocator = By.xpath("//div[@id='withOptGroup']//div[contains(@class,'singleValue')]");
        String selectedText = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedValueLocator)).getText();

        Assert.assertEquals(selectedText, "Group 2, option 1");
    }
}
