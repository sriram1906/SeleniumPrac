import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class GetAllLinks extends BaseTest{
    @Test
    public void GetLinks()
    {
        driver.get("https://the-internet.herokuapp.com/");
        List<WebElement> links =  driver.findElements(By.tagName("a"));
        for (WebElement link:links )
        {
            if (link.getText().contains("Menu"))
            {
                System.out.println(link.getText() + "---" + link.getAttribute("href"));
            }

        }
    }
}
