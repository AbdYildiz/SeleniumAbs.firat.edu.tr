import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

public class MainPage {
    public WebDriver driver = new ChromeDriver();
    public Actions action = new Actions(driver);

    @BeforeSuite public void getSite(){
        driver.get("https://testabs.firat.edu.tr/tr");
        driver.manage().window().setSize(new Dimension(700,650));
    }

    @Test public void tc1_emptySearchBox() {
        driver.findElement(By.id("search")).click();
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
    }

    @Test public void tc2_invalidSearchChar(){
        driver.findElement(By.id("search")).sendKeys(".?-*8");
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
    }

    @Test public void tc3_isDropDownVisible(){
        driver.findElement(By.id("search")).sendKeys("mustafa");
        driver.findElement(By.id("search")).clear();
        driver.findElement(By.id("search")).click();
        boolean dropDown = driver.findElement(By.xpath("//form//a[1]")).isDisplayed();
        Assert.assertFalse(dropDown);
    }

    @Test public void tc4_mobileButton(){
        driver.navigate().refresh();
        boolean mButton = driver.findElement(By.xpath("//div[@class='mobileMenu']//a[contains(text(),'renci Bilgi Sistemi')]")).isDisplayed();
        Assert.assertFalse(mButton);
    }

    @Test public void tc5_isLoginVis(){
        boolean login = driver.findElement(By.xpath("//p[contains(text(),'Yap')]")).isDisplayed();
        Assert.assertTrue(login);
    }

    @Test public void tc6_pieChartData(){
        //action.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'1,293 (64.2%)')]"))).perform();

    }
    @Test public void tc8_pieChartRightClick() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='chartSlideButton']//i[@class='fas fa-arrow-right-long']")).click();
        driver.findElement(By.xpath("//div[@class='chartSlideButton']//i[@class='fas fa-arrow-right-long']")).click();
        driver.findElement(By.xpath("//div[@class='chartSlideButton']//i[@class='fas fa-arrow-right-long']")).click();
        Thread.sleep(1000);
        boolean rClick = driver.findElement(By.xpath("//p[contains(text(),'n Erkek Oran')]")).isDisplayed();
        Assert.assertTrue(rClick);
    }

    @Test public void tc9_pieChartLeftClick() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='chartSlideButton']//i[@class='fas fa-arrow-left-long']")).click();
        driver.findElement(By.xpath("//div[@class='chartSlideButton']//i[@class='fas fa-arrow-left-long']")).click();
        driver.findElement(By.xpath("//div[@class='chartSlideButton']//i[@class='fas fa-arrow-left-long']")).click();
        Thread.sleep(1000);
        boolean lClick = driver.findElement(By.xpath("//p[contains(text(),'Akademik Personelin Unvanlara Göre Da')]")).isDisplayed();
        Assert.assertTrue(lClick);
    }

    @Test public void tc10_lastUpdated(){
        driver.findElement(By.xpath("//h6[contains(text(),'MEHMET GEÇ')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://testabs.firat.edu.tr/tr/190290057");
        driver.navigate().back();
    }

    @Test public void tc12_lastAddedPubs() throws InterruptedException {
        driver.findElement(By.xpath("//body//div[3]//div[3]//a[1]//i[1]")).click();
        String expected = driver.getCurrentUrl();
        driver.navigate().back();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//h6[contains(text(),'Akiferinin Kirlenebilirli')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),expected);
    }

    @Test public void tc15_location(){
        driver.findElement(By.xpath("//a[contains(text(),'rat Üniversitesi')]")).click();
        String expected = "https://www.google.com/maps/place/F%C4%B1rat+University/@38.6777569,39.2019556,17z/data="+
                "!3m1!4b1!4m5!3m4!1s0x4076c043f0ec934d:0x97da54a9bdfebc9a!8m2!3d38.6777569!4d39.2019556";
        Assert.assertEquals(driver.getCurrentUrl(),expected);
        driver.navigate().back();
    }

    @Test public void tc16_upperRightMainPage(){
        driver.findElement(By.xpath("//li[contains(text(),'ANASAYFA')]")).click();
        String expected = "https://testabs.firat.edu.tr/tr";
        Assert.assertEquals(driver.getCurrentUrl(),expected);
    }

    @Test public void tc17_statistics(){
        driver.findElement(By.xpath("//li[contains(text(),'KLER')]")).click();
        String expected = "https://testabs.firat.edu.tr/tr/statistics";
        Assert.assertEquals(driver.getCurrentUrl(),expected);
        driver.navigate().back();
    }

    @Test public void tc18_login(){
        driver.findElement(By.xpath("//div[@id='header-menu']//a//div")).click();
        Assert.assertEquals(driver.getTitle(),"Central Authentication Service");
        driver.navigate().back();
    }

    @AfterTest public void tc19_quickAccessToOBS(){
        driver.findElement(By.xpath("//html//body//div//div//div//nav//ul//li//div//ul//li//a[contains(text(),'renci Bilgi Sistemi')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://obs.firat.edu.tr/");
        driver.navigate().back();
    }
    @AfterTest public void tc20_quickAccessToPBS(){
        driver.findElement(By.xpath("//html//body//div//div//div//nav//ul//li//div//ul//li//a[contains(text(),'Personel Bilgi Sistemi')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://pbs.firat.edu.tr/");
        driver.navigate().back();
    }

    @AfterTest public void tc21_quickAccessToUni(){
        driver.findElement(By.xpath("//html//body//div//div//div//nav//ul//li//div//ul//li//a[contains(text(),'Üniversite Anasayfa')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.firat.edu.tr/tr");
        driver.navigate().back();
    }

    @AfterTest public void tc22_quickAccessToPhoneBook(){
        driver.findElement(By.xpath("//html//body//div//div//div//nav//ul//li//div//ul//li//a[contains(text(),'Telefon Rehberi')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.firat.edu.tr/tr/contact");
        driver.navigate().back();
    }

    @AfterTest public void tc23_lanEnglish(){
        driver.findElement(By.xpath("//html//body//div//div//div//nav//ul//li//div//ul//li//a[contains(text(),'English')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://testabs.firat.edu.tr/en");
        driver.navigate().back();
    }

    @AfterTest public void tc24_lanTurkish(){
        driver.findElement(By.xpath("//html//body//div//div//div//nav//ul//li//div//ul//li//a[contains(text(),'Türkçe')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://testabs.firat.edu.tr/tr");
    }

    @AfterSuite public void close() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}
