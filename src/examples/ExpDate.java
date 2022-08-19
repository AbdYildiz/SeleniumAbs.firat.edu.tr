package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExpDate {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.driver.chrome","C:\\Users\\Si\\Desktop\\RAZER\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String year = "1911";
        String month = "Şub";
        String day = "6";

        driver.get("https://appiso.firat.edu.tr/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.id("email")).sendKeys("170541028@firat.edu.tr");
        driver.findElement(By.id("password")).sendKeys("123456789");
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-user btn-block']")).click();
        driver.findElement(By.xpath("//h4[contains(text(),'vurular')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),\"vuru Yap\")]")).click();
        driver.findElement(By.id("dogum_tarihi")).click();
        driver.findElement(By.xpath("//div[@class='datepicker-days']//th[@class='datepicker-switch']")).click();
        driver.findElement(By.xpath("//div[@class='datepicker-months']//th[@class='datepicker-switch']")).click();

        for (int i = 0; i < 100; i++) {
            if (Integer.parseInt(driver.findElement(By.xpath("//div[@class='datepicker-years']//tbody//span[1]")).getText()) > Integer.parseInt(year)) {
                driver.findElement(By.xpath("//div[@class='datepicker-years']//thead//th[@class='prev']")).click();
            } else {
                driver.findElement(By.xpath("//div[@class='datepicker-years']//span[contains(text(),'" + year + "')]")).click();
                break;
            }
        }

        driver.findElement(By.xpath("//div[@class='datepicker-months']//span[contains(text(),'"+month+"')]")).click();
        driver.findElement(By.xpath("//div[@class='datepicker-days']//tbody//td[not(contains(@class,'new'))]"+
                " [not(contains(@class,'old'))] [not(contains(@class,'cw'))] [.='"+day+"']")).click();

        Thread.sleep(3000);
        driver.close();
        driver.quit();
    }
}
