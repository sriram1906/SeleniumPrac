import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/*if the underlying element has a input tag then sendkeys method can be used to set the path in the "value" property
But if it is not a input tag then, Use JavaScript to trigger the upload and pair it with Robot or AutoIT to handle the dialog*/

public class Fileupload extends BaseTest {
    @Test
    public void FileuploadTest() throws InterruptedException {
        String url = "https://testautomationpractice.blogspot.com/";
        driver.get(url);
        WebElement fileInput = driver.findElement(By.id("singleFileInput"));

        // Absolute path to the file on your Windows machine
        String filePath = "C:\\Users\\srira\\Downloads\\Test PDF.pdf"; // Replace with actual file path

        // Use sendKeys to set the file path (bypasses the Windows dialog)
        //sendkeys sets the path into the "value" attribute of input element
        fileInput.sendKeys(filePath);
        System.out.println("Printing file path -- " + driver.findElement(By.id("singleFileInput")).getAttribute("value"));
        Thread.sleep(5000);
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
