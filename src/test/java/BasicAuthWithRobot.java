import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

public class BasicAuthWithRobot extends BaseTest {
    @Test
    public void basicAuthWithRobotTest() throws Exception {
        String url = "https://the-internet.herokuapp.com/basic_auth";
        driver.get(url);

        // Wait for the page to load (using WebDriverWait instead of Thread.sleep)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("basic_auth")); // Wait until the page starts loading

        // Use Robot to simulate keypresses
        Robot robot = new Robot();

        // Type username: "admin"
        typeString(robot, "admin");

        // Press Tab to move to password field
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

        // Type password: "admin"
        typeString(robot, "admin");

        // Press Enter to submit
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Wait for the success page to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p")));

        // Verify the success message
        String message = driver.findElement(By.cssSelector("p")).getText();
        System.out.println("Message: " + message);
    }

    // Helper method to type a string using Robot
    private void typeString(Robot robot, String text) {
        for (char c : text.toCharArray()) {
            int keyCode = KeyEvent.getExtendedKeyCodeForChar(c);
            if (keyCode != KeyEvent.CHAR_UNDEFINED) { // Ensure valid key code
                robot.keyPress(keyCode);
                robot.keyRelease(keyCode);
                robot.delay(50); // Small delay between keypresses to mimic human typing
            }
        }
    }
}