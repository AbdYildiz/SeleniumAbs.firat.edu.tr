import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;


public class MobileMainPage {
    public WebDriver driver = new ChromeDriver();

    @BeforeClass (groups = {"mobileBtn", "search", "others"}) public void newDimensions(){
        driver.manage().window().setSize(new Dimension(375,667));
        driver.get("https://abs.firat.edu.tr/tr");
    }

    @BeforeMethod (groups = {"mobileBtn" , "search", "others"}) public void toMain(){
        driver.get("https://abs.firat.edu.tr/tr");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test (groups = { "mobileBtn"}) public void mobileBtn_obs(){
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        driver.findElement(By.xpath("//div[@class='mobileMenu']//li[.='Öğrenci Bilgi Sistemi']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://obs.firat.edu.tr/");
    }

    @Test (groups = { "mobileBtn"}) public void mobileBtn_pbs(){
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        driver.findElement(By.xpath("//div[@class='mobileMenu']//li[.='Personel Bilgi Sistemi']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://pbs.firat.edu.tr/");
    }

    @Test (groups = { "mobileBtn"}) public void mobileBtn_UniMain(){
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        driver.findElement(By.xpath("//div[@class='mobileMenu']//li[.='Üniversite Anasayfa']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.firat.edu.tr/tr");
    }

    @Test (groups = { "mobileBtn"}) public void mobileBtn_PhoneBook(){
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        driver.findElement(By.xpath("//div[@class='mobileMenu']//li[.='Telefon Rehberi']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.firat.edu.tr/tr/contact");
    }

    @Test (groups = { "mobileBtn"}) public void mobileBtn_language_tr(){
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        driver.findElement(By.xpath("//div[@class='mobileMenu']//li[.='Türkçe']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://abs.firat.edu.tr/tr");
    }

    @Test (groups = { "mobileBtn"}) public void mobileBtn_language_en() {
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        driver.findElement(By.xpath("//div[@class='mobileMenu']//a[.='English']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://abs.firat.edu.tr/en");
    }

    @Test (groups = { "mobileBtn"}) public void mobileBtn_toMainPage(){
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        driver.findElement(By.xpath("//div[@class='mobileMenu']//li[.='Anasayfa']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr");
    }

    @Test (groups = {"mobileBtn"}) public void mobileBtn_statistics(){
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'stat')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr/statistics");
    }

    @Test (groups = {"mobileBtn"}) public void mobileBtn_btn_Login(){
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Yap')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://jasig.firat.edu.tr/cas/login?service=https%3A%2F%2Fabs.firat.edu.tr%2Ftr%2Flogin%2Ffirat%2Fcas");
    }

    @Test (groups = {"search"}) public void searchBox(){
        driver.findElement(By.id("search")).sendKeys(" ",Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr");
        driver.findElement(By.id("search")).sendKeys("./_*?",Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr");
        driver.findElement(By.id("search")).clear();
    }
    @Test (groups = {"search"}) public void searchBox_firstItem(){
        driver.findElement(By.id("search")).sendKeys("mustafa ulas");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='userOption']//a[1]")));
        driver.findElement(By.id("search")).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr/mustafaulas");
    }

    @Test (groups = {"search"}) public void searchBox_lastItem() {
        driver.findElement(By.id("search")).sendKeys("mustafa ulas");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='userOption']//a[1]")));
        driver.findElement(By.id("search")).sendKeys(Keys.ARROW_DOWN,Keys.ARROW_DOWN,Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr/m.ulas");
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
        String isit = driver.findElement(By.xpath("//p[contains(text(),'ddyo@firat.edu.tr')]")).getAttribute("href");
        Assert.assertNotNull(isit);
    }

    @Test(groups = {"others"}) public void footer_ddyo(){
        driver.findElement(By.linkText("Dijital Dönüşüm ve Yazılım Ofisi")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://ddyo.firat.edu.tr/tr");
    }


    @AfterClass (groups = {"mobileBtn", "search", "others"}) public void close(){
        driver.quit();
    }
}
