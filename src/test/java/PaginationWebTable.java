import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class PaginationWebTable extends BaseTest {

    // Test 1: Print all Name and Price by navigating through pages
    @Test
    public void printNameAndPriceTest() {
        String url = "https://testautomationpractice.blogspot.com/";
        driver.get(url);

        // Wait for the table to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTable")));

        // Get the total number of pages from pagination links
        List<WebElement> pageLinks = driver.findElements(By.xpath("//ul[@id='pagination']/li/a"));

        // Iterate through each page
        for (int page = 1; page <= pageLinks.size(); page++) {
            // Click the page link (re-find to avoid stale element)
            WebElement pageLink = driver.findElement(By.xpath("//ul[@id='pagination']/li/a[text()='" + page + "']"));
            pageLink.click();

            // Wait for the table to update (ensure at least one row is visible)
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#productTable tbody tr")));

            // Locate the table and get rows
            WebElement table = driver.findElement(By.id("productTable"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));

            // Print Name and Price for each row (skip header)
            System.out.println("Page " + page + ":");
            for (int i = 1; i < rows.size(); i++) { // Skip header row
                List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
                if (cells.size() >= 3) { // Ensure enough columns
                    String name = cells.get(1).getText(); // Name (index 1)
                    String price = cells.get(2).getText(); // Price (index 2)
                    System.out.println("Name: " + name + ", Price: " + price);
                }
            }

            // Re-find rows to avoid stale element issues
            rows = table.findElements(By.tagName("tr"));
        }
    }

    // Test 2: Find row for Name = "Soundbar" and select the corresponding checkbox
    @Test
    public void selectSoundbarCheckboxTest() {
        String url = "https://testautomationpractice.blogspot.com/";
        driver.get(url);

        // Wait for the table to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("productTable")));

        boolean found = false;
        // Get the total number of pages
        List<WebElement> pageLinks = driver.findElements(By.xpath("//ul[@id='pagination']/li/a"));

        // Iterate through each page until Soundbar is found
        for (int page = 1; page <= pageLinks.size() && !found; page++) {
            // Click the page link
            WebElement pageLink = driver.findElement(By.xpath("//ul[@id='pagination']/li/a[text()='" + page + "']"));
            pageLink.click();

            // Wait for the table to update
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#productTable tbody tr")));

            // Locate the table and get rows
            WebElement table = driver.findElement(By.id("productTable"));
            List<WebElement> rows = table.findElements(By.tagName("tr"));

            // Search for Soundbar
            for (int i = 1; i < rows.size(); i++) { // Skip header
                List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
                if (cells.size() >= 4) { // Ensure enough columns
                    String name = cells.get(1).getText(); // Name (index 1)
                    if (name.equals("Soundbar")) {
                        WebElement checkbox = cells.get(3).findElement(By.tagName("input")); // Checkbox (index 3)
                        if (!checkbox.isSelected()) {
                            checkbox.click();
                            System.out.println("Selected checkbox for Soundbar on page " + page);
                        }
                        found = true;
                        break;
                    }
                }
            }
        }

        if (!found) {
            System.out.println("Soundbar not found in any page!");
        }
    }
}