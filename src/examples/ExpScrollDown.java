package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.testng.Assert;

import java.util.List;

public class ExpScrollDown {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.driver.chrome","C:\\Users\\Si\\Desktop\\RAZER\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        js.executeScript("window.scrollBy(0,500)");
        js.executeScript("document.querySelector(\".tableFixHead\").scrollTop=500");
        List<WebElement> amount = driver.findElements(By.cssSelector("#product td:nth-child(4)"));

        int total = 0;
        for (WebElement i: amount) {
            total += Integer.parseInt(i.getText());
        }

        System.out.println(total);
        System.out.println(Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(": ")[1]));
        Assert.assertEquals(total,Integer.parseInt(driver.findElement(By.cssSelector(".totalAmount")).getText().split(": ")[1]));
        Thread.sleep(3000);
        driver.close();
        driver.quit();
    }

}
