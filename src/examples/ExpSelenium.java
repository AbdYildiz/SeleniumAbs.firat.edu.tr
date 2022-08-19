package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExpSelenium {
    public static void main(String[] args) {
        System.setProperty("webdriver.driver.chrome","C:\\Users\\Si\\Desktop\\RAZER\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        String n = "3";
        driver.findElement(By.xpath("//div[@id='checkbox-example']//label["+ n +"]/input")).click();
        String opt = driver.findElement(By.xpath("//div[@id='checkbox-example']//label["+n+"]")).getText();
        driver.findElement(By.xpath("//select[@id='dropdown-class-example']")).click();
        driver.findElement(By.xpath("//select[@id='dropdown-class-example']//option[text()='"+opt+"']")).click();
        driver.findElement(By.xpath("//fieldset[@class='pull-right']/input[@id='name']")).sendKeys(opt);
        driver.findElement(By.xpath("//fieldset[@class='pull-right']/input[@id='alertbtn']")).click();
        System.out.println(driver.switchTo().alert().getText().split(" ")[1].split(",")[0]);
        driver.switchTo().alert().accept();

        driver.close();
        driver.quit();
    }
}
