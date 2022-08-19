package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExpFrames {
    public static void main(String[] args) {
        System.setProperty("webdriver.driver.chrome","C:\\Users\\Si\\Desktop\\RAZER\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.linkText("Nested Frames")).click();
        driver.switchTo().frame("frame-top");
        driver.switchTo().frame("frame-middle");
        System.out.println(driver.findElement(By.id("content")).getText());

        driver.switchTo().defaultContent();
        driver.quit();
    }
}
