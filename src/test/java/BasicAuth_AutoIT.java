import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BasicAuth_AutoIT extends BaseTest{
    @Test
    public void basicAuthWithAutoItTest() throws Exception {
        // Navigate to the Basic Auth page (triggers the dialog)
        String url = "https://the-internet.herokuapp.com/basic_auth";
        driver.get(url);
        Thread.sleep(5000);
        // Execute the AutoIt script
        String autoItScriptPath = "C:\\Users\\srira\\Desktop\\basic_auth.au3"; // Update this path
        String autoItExePath = "C:\\Program Files (x86)\\AutoIt3\\AutoIt3.exe"; // Update if different
        String command = "\"" + autoItExePath + "\" \"" + autoItScriptPath + "\"";
        Runtime.getRuntime().exec(command);

        // Wait for the page to load after authentication
        Thread.sleep(2000); // Adjust if needed

        // Verify the success message
        WebElement successMessage = driver.findElement(By.cssSelector("p"));
        String messageText = successMessage.getText();
        System.out.println("Message: " + messageText);

       /* Below is the copy of the code used in basic_auth.au3 script
        WinWaitActive("[REGEXP:Sign in.*]") ; Adjust to exact title
        Send("admin")
        Send("{TAB}")
        Send("admin")
        Send("{ENTER}")
        MsgBox(0, "Success", "Credentials sent!")   */
    }
}
