import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Profile {
    public WebDriver driver = new ChromeDriver();
    public SoftAssert soft = new SoftAssert();


    @BeforeClass public void beforeClass(){
        driver.get("https://abs.firat.edu.tr/tr/mustafaulas");
        driver.manage().window().maximize();
    }

    @BeforeMethod public void beforeMethod(){
        driver.get("https://abs.firat.edu.tr/tr/mustafaulas");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test public void mobileBtn(){
        boolean open = driver.findElement(By.xpath("//div[@class='mobil-button']")).isEnabled();
        Assert.assertTrue(open);
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        boolean close = driver.findElement(By.xpath("//div[@class='mobil-button']")).isEnabled();
        Assert.assertFalse(close);
    }

    @Test public void toMain(){
        driver.findElement(By.xpath("//p[.='Anasayfa']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://abs.firat.edu.tr/tr");
    }

    @Test public void Login(){
        driver.findElement(By.xpath("//p[.='Giriş Yap']")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://jasig.firat.edu.tr/cas/login?service=https%3A%2F%2Fabs.firat.edu.tr%2Ftr%2Flogin%2Ffirat%2Fcas");
    }

    @Test public void leftPanel_Profile(){
        driver.findElement(By.xpath("//ul[@class='menu']/li[1]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[.='Genel Bilgiler']")).getText(),"Genel Bilgiler");
    }

    @Test public void leftPanel_EgitimBilgileri(){
        driver.findElement(By.xpath("//ul[@class='menu']/li[2]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[contains(text(),'Eğitim Bilgileri')]")).getText(),"Eğitim Bilgileri");
    }

    @Test public void leftPanel_ArastirmaAlanlari(){
        driver.findElement(By.xpath("//ul[@class='menu']/li[3]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[contains(text(),'Araştırma Alanları')]")).getText(),"Araştırma Alanları");
    }

    @Test public void leftPanel_AkademikDeneyim(){
        driver.findElement(By.xpath("//ul/li[4]//li[1]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h5[.='Akademik Unvanlar']")).getText(),"Akademik Unvanlar");
    }

    @Test public void leftPanel_IdariDeneyim(){
        driver.findElement(By.xpath("//ul/li[4]//li[2]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h5[.='İdari Unvanlar']")).getText(),"İdari Unvanlar");
    }

    @Test public void leftPanel_Yayinlar() throws InterruptedException {
        driver.findElement(By.xpath("//ul//li[5]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[contains(text(),'Yayınlar & Eserler')]")).getText(), "Yayınlar & Eserler");

        driver.findElement(By.xpath("(//div[@class='main-div'])[1]")).click();
        driver.findElement(By.xpath("(//div[@class='main-div'])[2]")).click();
        driver.findElement(By.xpath("(//div[@class='main-div'])[3]")).click();

        List<WebElement> makale = driver.findElements(By.xpath("(//div[@class='publications-title'])[1]//h6"));
        for (int i = 0; i < makale.size(); i++) {
            driver.findElement(By.xpath("(//button[contains(text(),'Detay')])[" + (i+1) + "]")).click();
            Thread.sleep(300);
            soft.assertEquals(makale.get(i).getText(), driver.findElement(By.xpath("(//p[contains(text(),'Adı : ')])[" + (i+1) + "]")).getText().split(" : ")[1]);
            driver.findElement(By.xpath("(//i[@class='fas fa-times'])[" + (i+1) + "]")).click();
        }
        soft.assertAll();

        List<WebElement> bildiri = driver.findElements(By.xpath("//div[@id='muh3']//div[@class='publications-title']//h6"));
        for (int i = 0; i < bildiri.size(); i++) {
            driver.findElement(By.xpath("(//button[contains(text(),'Detay')])[" + (i+22) + "]")).click();
            Thread.sleep(300);
            soft.assertEquals(bildiri.get(i).getText(), driver.findElement(By.xpath("(//p[contains(text(),'Bildiri Adı : ')])[" + (i+1) + "]")).getText().split(" : ")[1]);
            driver.findElement(By.xpath("(//i[@class='fas fa-times'])[" + (i+22) + "]")).click();
        }
        soft.assertAll();
    }

    @Test public void leftPanel_ProjePatent() throws InterruptedException {
        driver.findElement(By.xpath("//ul//li[6]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[.='Proje & Patent']")).getText(), "Proje & Patent");

        driver.findElement(By.xpath("//div[@id='muh1']//div[@class='main-div']")).click();
        List<WebElement> projeler = driver.findElements(By.xpath("(//div[@class='project-sub-title'])"));
        for (int i = 0 ; i < projeler.size();i++) {
            driver.findElement(By.xpath("(//button[contains(text(),'Proje Detayları')])[" + (i+1) + "]")).click();
            Thread.sleep(200);
            soft.assertEquals(projeler.get(i).getText(), driver.findElement(By.xpath("(//p[contains(strong,'Proje Adı')])[" + (i+1) + "]")).getText().split(" : ")[1]);
            driver.findElement(By.xpath("(//i[@class='fas fa-times'])[" + (i+1) + "]")).click();
        }
        soft.assertAll();
    }

    @Test public void leftPanel_BilimselFaaliyetler(){
        driver.findElement(By.xpath("//ul//li[7]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h3[.='Bilimsel Faaliyetler']")).getText(), "Bilimsel Faaliyetler");
    }

    @Test public void leftPanel_Iletisim(){
        driver.findElement(By.xpath("//ul//li[8]")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h5[.='Mail Bilgileri']")).getText(), "Mail Bilgileri");

        boolean email = driver.findElement(By.xpath("//p[text()='Mail Adresi : ']//a[@href]")).isEnabled();
        Assert.assertTrue(email);
    }

    @AfterClass public void close(){
        driver.close();
        driver.quit();
    }
}