import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DropdownTest extends BaseTest {

    @Test
    public void SelectDropdownValue() {
        driver.get("https://testautomationpractice.blogspot.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("colors")));

        Select colorsDropdown = new Select(driver.findElement(By.id("colors")));
        List<org.openqa.selenium.WebElement> options = colorsDropdown.getOptions();

        for (int i=0;i<options.size();i++)
        {
            System.out.println(options.get(i).getText());
            if (options.get(i).getText().trim().equals("Yellow"))
            {
                /*All the below statements select the item - "Yellow"*/
               //colorsDropdown.selectByValue(options.get(i).getAttribute("value"));
                colorsDropdown.selectByVisibleText(options.get(i).getText());
               //colorsDropdown.selectByIndex(i);
            }

        }

       /* int lastButOneIndex = options.size() - 2; // Last-but-one index
        String lastButOneColor = options.get(lastButOneIndex).getText();
        colorsDropdown.selectByIndex(lastButOneIndex);

        List<org.openqa.selenium.WebElement> selectedOptions = colorsDropdown.getAllSelectedOptions();
        String selectedColor = selectedOptions.get(selectedOptions.size() - 1).getText();
        Assert.assertEquals(selectedColor, lastButOneColor, "Last but one color should be selected");
        System.out.println("Selected last but one color: " + selectedColor);*/
    }
}