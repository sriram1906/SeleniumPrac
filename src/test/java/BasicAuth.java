import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BasicAuth extends BaseTest {
    @Test
    public void basicAuthTest() {
        // Embed credentials in the URL
        String username = "admin";
        String password = "admin";
        String url = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";

        // Navigate to the page with credentials
        driver.get(url);

        // Verify the success message
        WebElement successMessage = driver.findElement(By.cssSelector("p"));
        String messageText = successMessage.getText();
        System.out.println("Message: " + messageText);
    }}