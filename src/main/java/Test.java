import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.List;

public class Test{
    WebDriver driver;
    public Test(WebDriver driver1)
    {
        driver = driver1;
    }

    public void CheckboxTest()
    {
        WebElement check = driver.findElement(By.name("checkBoxOption1"));
        check.click();
        List<WebElement> allcheckboxes =driver.findElements(By.xpath("//*[@id='checkbox-example']//input[@type='checkbox']"));
        for (WebElement checkbox:allcheckboxes)
        {
            System.out.println(checkbox.getAttribute("name"));
        }
    }
    public void uploadFileTest() throws InterruptedException {
        WebElement fileInput = driver.findElement(By.id("singleFileInput"));

        // Absolute path to the file on your Windows machine
        String filePath = "C:\\Users\\srira\\Downloads\\Test PDF.pdf"; // Replace with actual file path

        // Use sendKeys to set the file path (bypasses the Windows dialog)
        fileInput.sendKeys(filePath); //sendkeys sets the path into the "value" attribute of input element
        System.out.println("Printing file path -- " + driver.findElement(By.id("singleFileInput")).getAttribute("value"));
        Thread.sleep(10000);
        // Locate and click the "Upload Single File" button to submit the form
        WebElement uploadButton = driver.findElement(By.xpath("//button[text()='Upload Single File']"));
        uploadButton.click();

        String confirmationmessage = driver.findElement(By.xpath("//*[@id='singleFileStatus']")).getText();
        if (confirmationmessage.contains("Single file selected: Test PDF.pdf"))
        {
            System.out.println("File uploaded successfully");
        }

        // Optional: Verify the upload (e.g., check for a success message)
        try {
            Thread.sleep(10000); // Wait to observe the result (replace with explicit wait if needed)
            System.out.println("File uploaded: " + filePath);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
