package examples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.Iterator;
import java.util.Set;

public class ExpChangingTabs {
    public static void main(String[] args) {
        System.setProperty("webdriver.driver.chrome","C:\\Users\\Si\\Desktop\\RAZER\\Selenium\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/windows");
        driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> it = tabs.iterator();
        String parent = it.next();
        String child = it.next();
        driver.switchTo().window(child);
        child = driver.findElement(By.xpath("//h3[contains(text(),'New Window')]")).getText();
        driver.switchTo().window(parent);
        parent = driver.findElement(By.xpath("//h3[contains(text(),'Opening a new window')]")).getText();

        System.out.println(child + "\n" + parent);

        driver.quit();
    }
}
