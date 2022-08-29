package examples;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class ExpScreenShot {
    public static WebDriver driver = new ChromeDriver();

    public static void main(String[] args) throws IOException {
        driver.manage().window().maximize();
        driver.get("https://abs.firat.edu.tr/tr");
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,new File("C:\\Users\\Si\\hello.png"));

        driver.quit();

    }
}
