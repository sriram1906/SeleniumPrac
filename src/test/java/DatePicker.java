import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class DatePicker extends BaseTest{
    @Test
    public void datePickerTest() {
        String url = "https://testautomationpractice.blogspot.com/";
        driver.get(url);
        // Date Picker 1 (jQuery UI, not readonly)
        WebElement datePicker1 = driver.findElement(By.id("datepicker"));
        datePicker1.sendKeys("12/25/2024"); // Directly set the date (MM/DD/YYYY format)
        System.out.println("Date Picker 1 set to: 12/25/2024");

        // Date Picker 2 (jQuery UI, readonly) - Use JavaScript to set the value
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('txtDate').value = '12/26/2024';");
        // Alternatively, trigger the datepicker and select a date (more complex)
        System.out.println("Date Picker 2 set to: 12/26/2024");

        // Date Picker 3 (HTML5 date inputs)
        WebElement startDate = driver.findElement(By.id("start-date"));
        WebElement endDate = driver.findElement(By.id("end-date"));

        // HTML5 date inputs expect YYYY-MM-DD format
        startDate.sendKeys("02-12-2023");
        endDate.sendKeys("03-21-2025");

        // Click the Submit button to calculate the range
        WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
        submitButton.click();

        // Verify the result with explicit wait
        WebElement result = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("result")));
        System.out.println("Date Picker 3 Result: " + result.getText());
    }
}
