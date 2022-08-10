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

    @BeforeClass public void maximize(){
        driver.manage().window().maximize();
    }

    @BeforeMethod public void getSite(){
        driver.get("https://abs.firat.edu.tr/tr");
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

    @Test public void toLogin(){
        driver.findElement(By.xpath("//a[contains(text(),'Yap')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://jasig.firat.edu.tr/cas/login?service=https%3A%2F%2Fabs.firat.edu.tr%2Ftr%2Flogin%2Ffirat%2Fcas");
    }

    @Test public void toStatistics(){
        driver.findElement(By.xpath("//li[contains(text(),'KLER')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://abs.firat.edu.tr/tr/statistics");
    }

    @Test public void toMain(){
        driver.findElement(By.xpath("//li[contains(text(),'ANASAYFA')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr");
    }

    @Test public void toEnglish(){
        driver.findElement(By.cssSelector("div.header:nth-child(1) div.top-right-menus div.top-right nav:nth-child(1) ul:nth-child(1) li:nth-child(2) div.top-right-sub-list > a:nth-child(1)")).click();
        driver.findElement(By.cssSelector("div.header:nth-child(1) div.top-right-menus div.top-right li:nth-child(2) div.top-right-sub-list ul:nth-child(3) li:nth-child(2) > a:nth-child(1)")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/en");
    }

    @Test public void toTurkish(){
        driver.findElement(By.cssSelector("div.header:nth-child(1) div.top-right-menus div.top-right nav:nth-child(1) ul:nth-child(1) li:nth-child(2) div.top-right-sub-list > a:nth-child(1)")).click();
        driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Türkçe')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr");
    }

    @Test public void toOBS(){
        driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'renci Bilgi Sistemi')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://obs.firat.edu.tr/");
    }

    @Test public void toPBS(){
        driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Personel Bilgi Sistemi')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://pbs.firat.edu.tr/");
    }

    @Test public void toUniMain(){
        driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Üniversite Anasayfa')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.firat.edu.tr/tr");
    }

    @Test public void toPhoneBook(){
        driver.findElement(By.xpath("//div[@class='top-right-sub-list']//a[contains(text(),'Telefon Rehberi')]")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.firat.edu.tr/tr/contact");
    }

    @Test public void toLastUpdated(){
        driver.findElement(By.xpath("//div[@class='part2 container']//a[1]//div[1]")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://abs.firat.edu.tr/tr/askinsen");
    }

    @AfterClass public void close() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
