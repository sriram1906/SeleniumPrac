import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NestedFramesTest extends BaseTest {

    @Test
    public void testNestedFrames() {
        // Navigate to the Nested Frames page
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        // Wait for the frames to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("frame-top")));

        // Switch to frame-top
        driver.switchTo().frame("frame-top");

        // Switch to frame-left and verify text
        driver.switchTo().frame("frame-left");
        WebElement leftFrameBody = driver.findElement(By.tagName("body"));
        String leftText = leftFrameBody.getText();
        Assert.assertEquals(leftText, "LEFT", "Left frame text should be 'LEFT'");
        System.out.println("Left frame text: " + leftText);

        // Switch back to frame-top
        driver.switchTo().parentFrame();

        // Switch to frame-middle and verify text
        driver.switchTo().frame("frame-middle");
        WebElement middleFrameBody = driver.findElement(By.tagName("body"));
        String middleText = middleFrameBody.getText();
        Assert.assertEquals(middleText, "MIDDLE", "Middle frame text should be 'MIDDLE'");
        System.out.println("Middle frame text: " + middleText);

        // Switch back to frame-top
        driver.switchTo().parentFrame();

        // Switch to frame-right and verify text
        driver.switchTo().frame("frame-right");
        WebElement rightFrameBody = driver.findElement(By.tagName("body"));
        String rightText = rightFrameBody.getText();
        Assert.assertEquals(rightText, "RIGHT", "Right frame text should be 'RIGHT'");
        System.out.println("Right frame text: " + rightText);

        // Switch back to the main content
        driver.switchTo().defaultContent();

        // Switch to frame-bottom and verify text
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("frame-bottom")));
        WebElement bottomFrameBody = driver.findElement(By.tagName("body"));
        String bottomText = bottomFrameBody.getText();
        Assert.assertEquals(bottomText, "BOTTOM", "Bottom frame text should be 'BOTTOM'");
        System.out.println("Bottom frame text: " + bottomText);

        // Switch back to the main content
        driver.switchTo().defaultContent();

        System.out.println("Nested frames test completed successfully.");
    }
}