package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ExpBrokenLink {
    public static WebDriver driver = new ChromeDriver();

    public static void main(String[] args) throws IOException {
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> footer = driver.findElements(By.cssSelector("#gf-BIG a"));
        SoftAssert soft = new SoftAssert();

        for (WebElement a: footer) {
            String url = a.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            soft.assertTrue(conn.getResponseCode() < 400,a.getText() + " broken link");
//            if (conn.getResponseCode() > 400){
//                System.out.println(a.getText() + " : broken link");
//            }else {
//                System.out.println(a.getText() + " : working link");
//            }
        }

        soft.assertAll();
        driver.quit();
    }
}
