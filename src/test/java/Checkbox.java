import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Checkbox extends BaseTest{
    @Test
    public void CheckboxTest()
    {
        String url = "https://rahulshettyacademy.com/AutomationPractice/";
        driver.get(url);
        WebElement check = driver.findElement(By.name("checkBoxOption1"));
        check.click();
        List<WebElement> allcheckboxes = driver.findElements(By.xpath("//*[@id='checkbox-example']//input[@type='checkbox']"));
        for (WebElement checkbox : allcheckboxes)
        {
            System.out.println("Checkbox name: " + checkbox.getAttribute("name") + " | selected: " + checkbox.isSelected());
        }

        // changed code: assert that there are exactly 3 checkboxes
        Assert.assertEquals(allcheckboxes.size(), 3, "Expected 3 checkboxes on the page.");
    }
}
