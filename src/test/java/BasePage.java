import com.google.common.collect.ImmutableMap;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.tools.ant.types.selectors.SelectSelector;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;


public class BasePage extends BaseTest {

    final static Logger  logger = Logger.getLogger(BasePage.class.getName());

    @Step("<wait> saniye kadar bekle")
    public void waitForSeconds(int wait) throws InterruptedException {
        logger.info(wait + "Saniye kadar bekleniyor.");
        Thread.sleep(1000 * wait);
    }

    @Step("id <id> 'li elemene tıkla")
    public void clickById(String id) throws InterruptedException {
        appiumDriver.findElement(By.id(id)).click();
        logger.info(id + " 'li elemente tıklandı.");

    }

    @Step("xpatli <xpath> 'li elemene tıkla")
    public void clickByxpath(String xpath) throws InterruptedException {
        appiumDriver.findElement(By.xpath(xpath)).click();
        logger.info(xpath + " 'li elemente tıklandı.");

    }

    @Step("<id>'li Elemene Tıkla ve <text> değerini yaz")
    public void sendKeysId(String id, String text) {
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        logger.info(id + " id'li elementini bulundu ve " + text + "değeri yazıldı");
    }

    @Step("Klavyede arama butonuna bas")
    public void clickSearchButtonOnKeyboard() {
        ((JavascriptExecutor) appiumDriver).executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
    }

    @Step("Uygulama acılıs kontorlü")
    public void controlAppOpen() throws InterruptedException {
        if (appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/tv_startShoppingStore")).isDisplayed()) {
            logger.info("Uygulama acilmistir.");
            appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/tv_startShoppingStore")).click();
            waitForSeconds(3);
            }
        else{
            logger.info("Uygulama acilamamistir.");
            }
        }


    @Step("Uygulamaya giriş kontorlü")
    public void controlMainPageOpen() throws InterruptedException {
        if (appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/iv")).isDisplayed()) {
            logger.info("Uygulama ana sayfaya giris yapilmiştir.");
            waitForSeconds(3);
        }
        else{
            logger.info("Uygulama ana sayfaya giris yapilamamiştir.");
            }
    }

    @Step("Kategori Giris Kontrolu")
    public void controlChategoryPageOpen() throws InterruptedException {
        if (appiumDriver.findElement(By.xpath("//android.widget.TextView[@text = 'Kategoriler']")).isDisplayed()) {
            logger.info("Kategorilere giris yapilmiştir.");
            waitForSeconds(3);
        }
        else{
            logger.info("Kategorilere giris yapilamamistir.");
        }
    }

    @Step("Rasgele ürün seçimi yap")
    public void selectRandomProduct() {
        Random rnd = new Random();
        List<MobileElement> prd = appiumDriver.findElements(By.id("com.ozdilek.ozdilekteyim:id/imageView"));
        logger.info("pd" + prd);
        MobileElement element = prd.get(rnd.nextInt(prd.size()));
        logger.info("element" + element);
        element.click();
    }

    @Step("Random Ürün Kontrolü")
    public void controlRandomProduct() throws InterruptedException {
        if (appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/relLayInstallment")).isDisplayed()) {
            logger.info("Rastgele urune tiklanmistir.");
            waitForSeconds(3);
        }
        else{
            logger.info("Rastgele urune tiklanamamistir.");
            }
    }

    @Step("Login Sayfası Kontrolü")
    public void controlLoginPage() throws InterruptedException {
        if (appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/btnLogin")).isDisplayed()) {
            logger.info("Login sayfasi acilmistir.");
            waitForSeconds(3);
        }
        else{
            logger.info("Login sayfasi acilmamistir.");
        }
    }

    @Step("Rasgele beden seçimi yap")
    public void selectRandomSize() {
        Random rnd = new Random();
        List<MobileElement> prd = appiumDriver.findElements(By.id("com.ozdilek.ozdilekteyim:id/tvInSizeItem"));
        logger.info("pd" + prd);
        MobileElement element = prd.get(rnd.nextInt(prd.size()));
        logger.info("element" + element);
        element.click();
    }

    @Step("Ekranı <times> defa sayfa sonuna kaydır ve <wait> saniye bekle")
    public void scroll(int times, int wait) throws InterruptedException {
        logger.info("Sayfa" + times + "defa asagiya kaydirilildi");
        for (int i = 0; i < times; i++) {
            TouchAction scroll = new TouchAction(appiumDriver)
                    .press(PointOption.point(530, 1900))
                    .waitAction(waitOptions(ofMillis(800)))
                    .moveTo(PointOption.point(530, 300))
                    .release()
                    .perform();
            Thread.sleep(1000 * wait);
        }
    }


}



















