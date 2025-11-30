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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        By valueDropdown = By.xpath("//div[@id='withOptGroup']");

        suppressAds();
        wait.until(ExpectedConditions.elementToBeClickable(valueDropdown)).click();

        By optionLocator = By.xpath("//div[contains(@class,'option') and normalize-space()='Group 2, option 1']");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", option);
        suppressAds();
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

        By selectedValueLocator = By.xpath("//div[@id='withOptGroup']//div[contains(@class,'singleValue')]");
        String selectedText = wait.until(ExpectedConditions.visibilityOfElementLocated(selectedValueLocator)).getText();

        Assert.assertEquals(selectedText, "Group 2, option 1");
    }

    private void suppressAds() {
        String script = "document.querySelectorAll(\"iframe[id^='google_ads_iframe'], div[id^='google_ads_iframe'], #adplus-anchor\")"
                + ".forEach(el => el.style.display = 'none');";
        ((JavascriptExecutor) driver).executeScript(script);
    }
}
