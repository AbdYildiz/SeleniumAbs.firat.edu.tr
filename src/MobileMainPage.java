import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class MobileMainPage {
    public WebDriver driver = new ChromeDriver();

    @BeforeMethod public void getSite(){
        driver.get("https://abs.firat.edu.tr/tr");
        driver.manage().window().setSize(new Dimension(375,667));
    }

    @Test public void emptySearchBox(){
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr?arast%C4%B1rma=");
    }

    @Test public void invalidSearchChar(){
        String temp = ".?-*8";
        driver.findElement(By.id("search")).sendKeys(temp);
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr?arast%C4%B1rma=.%3F-*8");
    }

    @Test public void isDropDownVisible(){
        driver.findElement(By.id("search")).sendKeys("mustafa");
        driver.findElement(By.id("search")).clear();
        driver.findElement(By.id("search")).click();
        boolean dropDown = driver.findElement(By.xpath("//form//a[1]")).isDisplayed();
        Assert.assertFalse(dropDown);
    }

    @Test public void isMobileButtonVisible(){
        driver.manage().window().setSize(new Dimension(750,750));
        driver.navigate().refresh();
        boolean mb = driver.findElement(By.xpath("//body/div[@class='mobileMenu']/ul/li[1]/a[1]")).isDisplayed();
        Assert.assertFalse(mb);
    }

    @Test public void toMobileLogin(){
        driver.findElement(By.xpath("//a[contains(text(),'Yap')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://jasig.firat.edu.tr/cas/login?service=https%3A%2F%2Fabs.firat.edu.tr%2Ftr%2Flogin%2Ffirat%2Fcas");
    }

    @Test public void toMobileStatistics(){
        driver.findElement(By.xpath("//a[contains(text(),'statistikler')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://abs.firat.edu.tr/tr/statistics");
    }

    @Test public void toMobileEnglish(){
        driver.findElement(By.xpath("//div[@class='mobileMenu']//a[contains(text(),'English')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/en");
    }

    @Test public void toMobileTurkish(){
        driver.findElement(By.xpath("//div[@class='mobileMenu']//a[contains(text(),'Türkçe')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr");
    }

    @Test public void toMobileOBS(){
        driver.findElement(By.xpath("//div[@class='mobileMenu']//a[contains(text(),'renci Bilgi Sistemi')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://obs.firat.edu.tr/");
    }

    @Test public void toMobilePBS(){
        driver.findElement(By.xpath("//div[@class='mobileMenu']//a[contains(text(),'Personel Bilgi Sistemi')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://pbs.firat.edu.tr/");
    }

    @Test public void toMobileUniMain(){
        driver.findElement(By.xpath("//div[@class='mobileMenu']//a[contains(text(),'Üniversite Anasayfa')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.firat.edu.tr/tr");
    }

    @Test public void toMobilePhoneBook(){
        driver.findElement(By.xpath("//div[@class='mobileMenu']//a[contains(text(),'Telefon Rehberi')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.firat.edu.tr/tr/contact");
    }

    @Test public void MobileBtOpen(){
        driver.findElement(By.xpath("//button[@class='navbar-toggler collapsed close']")).click();
        boolean mb = driver.findElement(By.xpath("//body/div[@class='mobileMenu']/ul/li[1]/a[1]")).isDisplayed();
        Assert.assertTrue(mb);
    }

    @Test public void MobileBtClose() throws InterruptedException {
        driver.findElement(By.xpath("//button[@class='navbar-toggler collapsed close']")).click();
        driver.findElement(By.xpath("//div[@class='mobil-button']")).click();
        Thread.sleep(1000);
        boolean mb = driver.findElement(By.xpath("//body/div[@class='mobileMenu']/ul/li[1]/a[1]")).isDisplayed();
        Assert.assertFalse(mb);
    }

    @AfterClass public void close() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
