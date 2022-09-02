import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class MainPage {
    public WebDriver driver = new ChromeDriver();
    public Actions actions = new Actions(driver);

    @BeforeMethod (groups = {"header" , "search", "others"}) public void toMain(){
        driver.get("https://abs.firat.edu.tr/tr");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test (groups = { "header"}) public void hizliErisim_obs(){
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Hızlı Eri')]"))).perform();
        driver.findElement(By.xpath("//div[@class='top-right-sub-list']//ul[1]//li[1]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://obs.firat.edu.tr/");
    }

    @Test (groups = { "header"}) public void hizliErisim_pbs(){
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Hızlı Eri')]"))).perform();
        driver.findElement(By.xpath("//div[@class='top-right-sub-list']//ul[1]//li[2]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://pbs.firat.edu.tr/");
    }

    @Test (groups = { "header"}) public void hizliErisim_UniMain(){
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Hızlı Eri')]"))).perform();
        driver.findElement(By.xpath("//div[@class='top-right-sub-list']//ul[1]//li[3]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.firat.edu.tr/tr");
    }

    @Test (groups = { "header"}) public void hizliErisim_PhoneBook(){
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Hızlı Eri')]"))).perform();
        driver.findElement(By.xpath("//div[@class='top-right-sub-list']//ul[1]//li[4]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.firat.edu.tr/tr/contact");
    }

    @Test (groups = { "header"}) public void language_tr(){
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Dil')]"))).perform();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/nav[1]/ul[1]/li[2]/div[1]/ul[1]/li[1]/a[1]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://abs.firat.edu.tr/tr");
    }

    @Test (groups = { "header"}) public void language_en(){
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Dil')]"))).perform();
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/nav[1]/ul[1]/li[2]/div[1]/ul[1]/li[2]/a[1]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://abs.firat.edu.tr/en");
    }

    @Test (groups = { "header"}) public void toMainPage(){
        driver.findElement(By.xpath("//li[contains(text(),'ANASAYFA')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr");
    }

    @Test (groups = {"header"}) public void statistics(){
        driver.findElement(By.xpath("//li[contains(text(),'STAT')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr/statistics");
    }

    @Test (groups = {"header"}) public void btn_Login(){
        driver.findElement(By.xpath("//p[contains(text(),'Yap')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://jasig.firat.edu.tr/cas/login?service=https%3A%2F%2Fabs.firat.edu.tr%2Ftr%2Flogin%2Ffirat%2Fcas");
    }

    @Test (groups = {"search"}) public void searchBox_visible(){
        driver.findElement(By.id("search")).sendKeys(" ",Keys.ENTER);
        boolean first = driver.findElement(By.xpath("//div[@class='input-select']/div/a[1]")).isDisplayed();
        Assert.assertFalse(first);
        driver.findElement(By.id("search")).sendKeys("./_*?",Keys.ENTER);
        boolean second = driver.findElement(By.xpath("//div[@class='input-select']/div/a[1]")).isDisplayed();
        Assert.assertFalse(second);
        driver.findElement(By.id("search")).clear();
        boolean third = driver.findElement(By.xpath("//div[@class='input-select']/div/a[1]")).isDisplayed();
        Assert.assertFalse(third);
    }

    @Test (groups = {"search"}) public void searchBox_Item() throws InterruptedException {
        driver.findElement(By.id("search")).sendKeys("mustafa u");
        Thread.sleep(200);
        driver.findElement(By.id("search")).sendKeys(" ");
        Thread.sleep(200);
        List<WebElement> searhItems = driver.findElements(By.xpath("//div[@class='input-select']/div/a"));
        Thread.sleep(200);
        for (int i = 0 ; i <searhItems.size() ; i++) {
            driver.findElement(By.cssSelector("#search")).sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(300);
            if (searhItems.get(i).getText().contains("MUSTAFA ULAŞ"))
                driver.findElement(By.cssSelector("#search")).sendKeys(Keys.ENTER);
            Thread.sleep(300);
        }
        Thread.sleep(500);
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr/mustafaulas");
    }

    @Test (groups = {"others"}) public void lastUpdated() throws IOException {
        List<WebElement> last = driver.findElements(By.xpath("//div[@class='last-updated col-xl-4 col-md-6']//a"));
        SoftAssert soft = new SoftAssert();

        for (WebElement a: last) {
            String url = a.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            soft.assertTrue(conn.getResponseCode() < 400,a.getText() + "          BROKEN LINK");
        }
        soft.assertAll();
    }

    @Test (groups = {"others"}) public void lastAdded() throws IOException {
        List<WebElement> href = driver.findElements(By.xpath("//div[@class='recently-added-posts col-xl-4 col-md-6']//a"));
        List<WebElement> text = driver.findElements(By.xpath("//div[@class='recently-added-posts col-xl-4 col-md-6']//h6"));
        SoftAssert soft = new SoftAssert();

        for (int i = 0; i < href.size(); i++) {
            String url = href.get(i).getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            soft.assertTrue(conn.getResponseCode() < 400,text.get(i).getText() + "             BROKEN LINK");
        }
        soft.assertAll();
    }

    @Test (groups = {"others"}) public void email_ddyo(){
        boolean isit = driver.findElement(By.xpath("//div[@class='mail']//a")).isEnabled();
        Assert.assertTrue(isit);
    }

    @Test(groups = {"others"}) public void footer_ddyo(){
        driver.findElement(By.linkText("Dijital Dönüşüm ve Yazılım Ofisi")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://ddyo.firat.edu.tr/tr");
    }

    @AfterClass (groups = {"header", "search", "others"}) public void close(){
        driver.quit();
    }
}
