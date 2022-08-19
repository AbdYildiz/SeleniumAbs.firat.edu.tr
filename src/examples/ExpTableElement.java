package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ExpTableElement {
    public static void main(String[] args) {
        System.setProperty("webdriver.driver.chrome","C:\\Users\\Si\\Desktop\\RAZER\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        int rows = driver.findElements(By.cssSelector(".table-display tr")).size();
        int columns = driver.findElements(By.cssSelector(".table-display th")).size();
        String secondRow = driver.findElement(By.cssSelector(".table-display tr:nth-child(3)")).getText();

        System.out.println("Number of rows = " + rows);
        System.out.println("Number of columns = " + columns);
        System.out.println("Second Row = " + secondRow);

        driver.close();
        driver.quit();
    }
}
