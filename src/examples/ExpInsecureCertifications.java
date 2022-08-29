package examples;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ExpInsecureCertifications {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        System.setProperty("webdriver.driver.chrome", "C:\\Users\\Si\\Desktop\\RAZER\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://expired.badssl.com/");
        System.out.println(driver.getTitle());

        driver.quit();
    }
}
