package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ExpAutoSuggestionDropdown {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.driver.chrome", "C:\\Users\\Si\\Desktop\\RAZER\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.xpath("//input[@id='autocomplete']")).clear();
        driver.findElement(By.xpath("//input[@id='autocomplete']")).sendKeys("tu");
        String text;
        do {
            driver.findElement(By.xpath("//input[@id='autocomplete']")).sendKeys(Keys.ARROW_DOWN);
            String val = "return document.getElementById(\"autocomplete\").value;";
            text = (String) js.executeScript(val);
        }while (!(text.equals("Turkey")));
        driver.findElement(By.xpath("//input[@id='autocomplete']")).sendKeys(Keys.ENTER);
        System.out.println(driver.findElement(By.id("autocomplete")).getAttribute("value"));

        Thread.sleep(3000);
        driver.close();
        driver.quit();
    }
}
