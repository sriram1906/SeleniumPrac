import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class PopupWindowsTest extends BaseTest {

    @Test
    public void testPopupWindows() {
        // Navigate to the page
        String url = "https://testautomationpractice.blogspot.com/";
        driver.get(url);

        // Store the main window handle
        String mainWindow = driver.getWindowHandle();

        // Locate and click the "Popup Windows" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popupButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("PopUp")));
        popupButton.click();

        // Wait for the two pop-up windows to open
        //wait.until(driver -> driver.getWindowHandles().size() == 3); // Main window + 2 pop-ups
        wait.until(ExpectedConditions.numberOfWindowsToBe(3));

        // Get all window handles
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(mainWindow); // Remove main window to iterate over pop-ups

        // Expected URLs
        String seleniumUrl = "https://www.selenium.dev/";
        String playwrightUrl = "https://playwright.dev/";

        // Verify URLs and close each pop-up
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            String currentUrl = driver.getCurrentUrl();

            if (currentUrl.contains("selenium.dev")) {
                Assert.assertEquals(currentUrl, seleniumUrl, "Selenium pop-up URL should match");
                System.out.println("Verified Selenium pop-up URL: " + currentUrl);
            } else if (currentUrl.contains("playwright.dev")) {
                Assert.assertEquals(currentUrl, playwrightUrl, "Playwright pop-up URL should match");
                System.out.println("Verified Playwright pop-up URL: " + currentUrl);
            } else {
                Assert.fail("Unexpected pop-up URL: " + currentUrl);
            }

            // Close the pop-up window
            driver.close();
        }

        // Switch back to the main window
        driver.switchTo().window(mainWindow);

        // Verify that only the main window remains
        Assert.assertEquals(driver.getWindowHandles().size(), 1, "Only the main window should remain");
        System.out.println("Pop-up windows closed successfully. Back to main window: " + driver.getCurrentUrl());
    }
}