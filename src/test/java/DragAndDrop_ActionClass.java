import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class DragAndDrop_ActionClass extends BaseTest {

    @Test
    public void dragAndDropTest() {
        String url = "https://the-internet.herokuapp.com/drag_and_drop";
        driver.get(url);

        // Wait for the elements to be visible
        //Note that wait.until does two things it waits and creates a webelement
        //For Box-A it is done in two steps, step-1 wait and step-2 create webelement
        //For Box-B it is done in single step
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-a")));
        WebElement boxA = driver.findElement(By.id("column-a"));
        WebElement boxB = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("column-b")));

        // Verify initial state
        String initialBoxAHeader = boxA.findElement(By.tagName("header")).getText();
        String initialBoxBHeader = boxB.findElement(By.tagName("header")).getText();
        Assert.assertEquals(initialBoxAHeader, "A", "Initial Box A header should be 'A'");
        Assert.assertEquals(initialBoxBHeader, "B", "Initial Box B header should be 'B'");

        // Perform drag and drop using Actions
        Actions actions = new Actions(driver);
        actions.dragAndDrop(boxA, boxB).build().perform();


        // Wait for the swap to complete (sometimes the UI takes a moment to update)
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("column-a"), "B"));

        // Verify the swap
        String finalBoxAHeader = driver.findElement(By.id("column-a")).findElement(By.tagName("header")).getText();
        String finalBoxBHeader = driver.findElement(By.id("column-b")).findElement(By.tagName("header")).getText();
        Assert.assertEquals(finalBoxAHeader, "B", "Box A should now be labeled 'B'");
        Assert.assertEquals(finalBoxBHeader, "A", "Box B should now be labeled 'A'");

        System.out.println("Drag and Drop successful: Box A and Box B swapped.");
    }
}
