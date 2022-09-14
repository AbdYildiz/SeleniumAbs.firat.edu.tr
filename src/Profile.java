import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Profile {
    public WebDriver driver = new ChromeDriver();
    public SoftAssert soft = new SoftAssert();

    @BeforeClass (groups = {"others"}) public void beforeClass(){
        driver.get("https://abs.firat.edu.tr/tr/mustafaulas");
        driver.manage().window().maximize();
    }

//    @BeforeMethod (groups = {"others"}) public void beforeMethod(){
//        driver.get("https://abs.firat.edu.tr/tr/mustafaulas");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//    }

    @Test (groups = {"others"}) public void mobileBtn() throws InterruptedException {
        boolean open = driver.findElement(By.xpath("//span[normalize-space()='Profil']")).isDisplayed();
        Assert.assertTrue(open);
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        Thread.sleep(3000);
        boolean close = driver.findElement(By.xpath("//span[normalize-space()='Profil']")).isDisplayed();
        Assert.assertFalse(close);
    }

    @Test (groups = {"others"}) public void toMain(){
        driver.findElement(By.xpath("//div[@class='home-page']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://abs.firat.edu.tr/tr");
    }

    @Test (groups = {"others"}) public void Login(){
        driver.findElement(By.xpath("//div[@class='login-button']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://jasig.firat.edu.tr/cas/login?service=https%3A%2F%2Fabs.firat.edu.tr%2Ftr%2Flogin%2Ffirat%2Fcas");
    }

    @Test (groups = {"others"}) public void leftPanel_Profile() throws IOException {
        driver.findElement(By.xpath("//ul[@class='menu']/li[1]")).click();
        soft.assertEquals(driver.findElement(By.xpath("//h3[.='Genel Bilgiler']")).getText(),"Genel Bilgiler");

        boolean mail = driver.findElement(By.xpath("//div[@class='contact']/p[2]/a")).isEnabled();
        soft.assertTrue(mail);

        driver.findElement(By.xpath("//div[@class='BMVehicles']//a[1]")).click();
        soft.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr/mustafaulas/publications");
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='BMVehicles']//a[2]")).click();
        soft.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr/mustafaulas/publications");
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='BMVehicles']//a[3]")).click();
        soft.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr/mustafaulas/projects");
        driver.navigate().back();

        soft.assertAll();
    }

    @Test (groups = {"others"}) public void scoialLinks() throws IOException {
        List<WebElement> social = driver.findElements(By.xpath("//div[@class='valid']//div[2]/a"));
        for (WebElement e: social) {
            String url = e.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            System.out.println(conn.getResponseCode());
            soft.assertTrue(conn.getResponseCode() < 400,e.getAttribute("href") + "          BROKEN LINK");
        }
        soft.assertAll();
    }

    @Test (groups = {"others"}) public void leftPanel_EgitimBilgileri(){
        driver.findElement(By.xpath("//ul[@class='menu']/li[2]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[contains(text(),'Eğitim Bilgileri')]")).getText(),"Eğitim Bilgileri");
    }

    @Test (groups = {"others"}) public void leftPanel_ArastirmaAlanlari(){
        driver.findElement(By.xpath("//ul[@class='menu']/li[3]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[contains(text(),'Araştırma Alanları')]")).getText(),"Araştırma Alanları");
    }

    @Test (groups = {"others"}) public void leftPanel_AkademikDeneyim(){
        driver.findElement(By.xpath("//ul[@class='menu']/li[4]")).click();
        driver.findElement(By.xpath("//ul/li[4]//li[1]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h5[.='Akademik Unvanlar']")).getText(),"Akademik Unvanlar");
    }

    @Test (groups = {"others"}) public void leftPanel_IdariDeneyim(){
        driver.findElement(By.xpath("//ul[@class='menu']/li[4]")).click();
        driver.findElement(By.xpath("//ul/li[4]//li[2]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h5[.='İdari Unvanlar']")).getText(),"İdari Unvanlar");
    }

    @Test public void leftPanel_Yayinlar() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath("//ul//li[5]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[contains(text(),'Yayınlar & Eserler')]")).getText(), "Yayınlar & Eserler");

        List<WebElement> main = driver.findElements(By.xpath("(//div[@class='main-div'])"));
        for (WebElement e:main) {
            e.click();
            Thread.sleep(300);
        }

        List<WebElement> section = driver.findElements(By.xpath("(//div[@class='inner-div'])"));
        for (int i = 0; i <section.size() ; i++) {
            List<WebElement> buttons = driver.findElements(By.xpath("(//div[@class='inner-div'])["+(i+1)+"]//button"));
            System.out.println(buttons.size());
            for (int j = 0; j <buttons.size() ; j++) {
                buttons.get(j).click();
                Thread.sleep(300);
                WebElement closeBtn = driver.findElement(By.xpath("((//div[@class='inner-div'])["+(i+1)+"]//i)["+(j+1)+"]"));
                boolean tru = closeBtn.isDisplayed();
                soft.assertTrue(tru);
                closeBtn.click();
                Thread.sleep(300);
            }
        }
        soft.assertAll();
    }

    @Test (groups = {"others"}) public void leftPanel_ProjePatent() throws InterruptedException {
        driver.findElement(By.xpath("//ul//li[6]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[.='Proje & Patent']")).getText(), "Proje & Patent");

        driver.findElement(By.xpath("//div[@id='muh1']//div[@class='main-div']")).click();
        List<WebElement> projeler = driver.findElements(By.xpath("//div[@class='project-sub-title']"));
        for (int i = 0 ; i < projeler.size();i++) {
            driver.findElement(By.xpath("(//button[contains(text(),'Proje Detayları')])[" + (i+1) + "]")).click();
            Thread.sleep(400);
            soft.assertEquals(projeler.get(i).getText(), driver.findElement(By.xpath("(//p[contains(strong,'Proje Adı')])[" + (i+1) + "]")).getText().split(" : ")[1]);
            driver.findElement(By.xpath("(//i[@class='fas fa-times'])[" + (i+1) + "]")).click();
        }
        soft.assertAll();
    }

    @Test (groups = {"others"}) public void leftPanel_BilimselFaaliyetler(){
        driver.findElement(By.xpath("//ul//li[7]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[.='Bilimsel Faaliyetler']")).getText(), "Bilimsel Faaliyetler");
    }

    @Test (groups = {"others"}) public void leftPanel_Iletisim(){
        driver.findElement(By.xpath("//ul//li[8]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h5[.='Mail Bilgileri']")).getText(), "Mail Bilgileri");

        boolean email = driver.findElement(By.xpath("//p[text()='Mail Adresi : ']//a[@href]")).isEnabled();
        Assert.assertTrue(email);
    }

    @AfterClass (groups = {"others"}) public void close(){
        driver.close();
        driver.quit();
    }
}
