import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CustomDropdownTest extends BaseTest {

    @Test
    public void selectValueFromCustomDropdown() {
        driver.get("https://demoqa.com/select-menu");

        By valueDropdown = By.xpath("//div[@id='withOptGroup']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(valueDropdown)).click();

        // Remove potential anchor ad iframe that can intercept clicks
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelectorAll(\"iframe[id^='google_ads_iframe']\").forEach(el => el.remove());");

        By optionLocator = By.xpath("//div[contains(@class,'option') and normalize-space()='Group 2, option 1']");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", option);
        option.click();

        By selectedValueLocator = By.xpath("//div[@id='withOptGroup']//div[contains(@class,'singleValue')]");
        String selectedText = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedValueLocator)).getText();

        Assert.assertEquals(selectedText, "Group 2, option 1");
    }
}
