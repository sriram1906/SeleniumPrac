import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HoverAndContextMenuTest extends BaseTest {

    /**
     * Simple example of mouse hover:
     * Move the mouse over an image and verify that a caption becomes visible.
     */
    @Test
    public void hoverOnImageShowsCaption() {
        driver.get("https://the-internet.herokuapp.com/hovers");

        // This page has 3 user images with the same structure.
        // We'll just use the first one.
        WebElement firstFigure = driver.findElement(By.cssSelector(".figure"));

        // Actions class lets us do advanced user interactions
        Actions actions = new Actions(driver);
        actions.moveToElement(firstFigure).perform();

        // After hover, a caption with a <h5> becomes visible inside this figure
        WebElement caption = firstFigure.findElement(By.tagName("h5"));

        Assert.assertTrue(
                caption.isDisplayed(),
                "Expected caption to be visible after hovering on the image"
        );
    }

    /**
     * Simple example of context menu:
     * Right-click on a box and verify the JavaScript alert text.
     */
    @Test
    public void rightClickShowsAlert() {
        driver.get("https://the-internet.herokuapp.com/context_menu");

        // The grey box that responds to right-click
        WebElement hotSpot = driver.findElement(By.id("hot-spot"));

        Actions actions = new Actions(driver);
        // Right-click = contextClick
        actions.contextClick(hotSpot).perform();

        // A JavaScript alert should appear
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        Assert.assertEquals(
                alertText,
                "You selected a context menu",
                "Unexpected alert text after right-click"
        );

        // Always good practice to close the alert
        alert.accept();
    }
}
