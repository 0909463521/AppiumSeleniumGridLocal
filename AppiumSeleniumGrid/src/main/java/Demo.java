//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;
//
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Demo extends WebDriverTestCase {
    /*
      !!! Do not create your own WebDriver. !!!
      You can copy credentials from here:
       - login@codility.com    password
       - unknown@codility.com  password
    */

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public WebElement waitedElement(By locator)
    {
        System.out.println(locator);

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(Duration.ofSeconds(100))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                System.out.println("A");
                return webDriver.findElement(locator);
            }
        });
        return element;
    }
    public List<WebElement> waitedListElement(By locator)
    {
        System.out.println(locator);

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(Duration.ofSeconds(100))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        List <WebElement> elements = wait.until(new Function<WebDriver, List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebDriver webDriver) {
                System.out.println("A");
                return webDriver.findElements(locator);
            }
        });
        return elements;
    }
    public boolean waitforElementAndDisplayed(By locator)
    {
        WebElement element = waitedElement(locator);
        FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
                .withTimeout(Duration.ofMillis(15))
                .pollingEvery(Duration.ofMillis(1))
                .ignoring(NoSuchElementException.class);

        boolean isDisplayed = wait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                boolean flag = element.isDisplayed();
                return flag;
            }
        });
        return isDisplayed;
    }




    public void validateText(String message, String className) {
        WebElement lbl_message = waitedElement(By.xpath("//*[text() = '"+message+"']"));
        lbl_message.isDisplayed();
        assertEquals(lbl_message.getAttribute("class"), className);
        assertEquals(lbl_message.getText(), message);
    }

    @Test
    public void testLoginFormPresent() {
        // below you can find already setup webDriver
        List <WebElement> listSibling = waitedListElement(By.xpath("//h2[contains(text(),'A few of our most popular courses')]/..//div[//a[text()='SELENIUM']]/following-sibling::div[@class='rt-grid-2 rt-omega']"));

        for (WebElement webElement : listSibling) {
            System.out.println(webElement.getText());
        }
    }



}

class WebDriverTestCase {
    WebDriver webDriver;
    @BeforeTest
    @Parameters({"platformName"})
    public void initializeWD(String platformName) throws MalformedURLException {


//            System.out.println("vao day");
//            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//            desiredCapabilities.setCapability("udid", "R52R90F9H0Y");
//
//            desiredCapabilities.setCapability("platformName", platformName);
//            desiredCapabilities.setCapability("browserName", "chrome");
//
//            desiredCapabilities.setCapability("deviceName", "*");
//            desiredCapabilities.setCapability("automationName", "UiAutomator2");
//            desiredCapabilities.setCapability("app","/Users/kobiton/Desktop/basicaccessibility_1.6_clickcrash.apk");

//            desiredCapabilities.setCapability("chromedriverExecutable", "/Users/kobiton/Desktop/Movie/chromedriver");
//
//
//            webDriver = new RemoteWebDriver(new URL("http://192.168.36.50:4444/wd/hub"),desiredCapabilities);
//            webDriver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_login_page/9a83bda125cd7398f9f482a3d6d45ea4/static/attachments/reference_page.html");



                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setBrowserName(platformName);
//            desiredCapabilities.setPlatform(Platform.MAC);

                webDriver = new RemoteWebDriver(new URL("http://192.168.36.50:4444/wd/hub"),desiredCapabilities);
                webDriver.manage().window().maximize();
                webDriver.get("http://demo.guru99.com/test/guru99home/");








    }

    @AfterTest
    public void closeWD() {
        webDriver.quit();
    }
}
