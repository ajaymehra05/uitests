import config.ConfigManager;
import logger.ILogger;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HotStarTest implements ILogger {

    WebDriver driver;
    HomePage homePage;

    {
        BasicConfigurator.configure();
    }

    @BeforeTest
    public void init() {
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(ConfigManager.getInstance().getString("url"));
    }


    @Test
    public void testHotStarPage() {
        homePage = new HomePage(driver);
        try {
            int no_of_trays = ConfigManager.getInstance().getInt("no_of_tray");
            List<WebElement> listBeforeSignIn = homePage.getNumberOfTopics();
            LOG.debug("List of tray title retrieved before login");

            if(listBeforeSignIn.size() >= no_of_trays) {
                for (int i = 0; i <= no_of_trays; i++) {
                    LOG.debug(i + ")" + listBeforeSignIn.get(i).getText());
                }
            } else {
                throw new RuntimeException("no of trays are less than the input size before login");
            }
            //sign in to the page
            // homePage.signIn();

            List<WebElement> listAfterSignIn = homePage.getNumberOfTopics();

            LOG.debug("List of tray title retrieved after login");

            if(listAfterSignIn.size() >= no_of_trays) {
                for (int i = 0; i <= no_of_trays; i++) {
                    LOG.debug(i + ")" + listBeforeSignIn.get(i).getText());
                }
            } else {
                throw new RuntimeException("no of trays are less than the input size after login");
            }

            for(int i = 0; i <= no_of_trays; i++) {
                if(!listBeforeSignIn.get(i).getText().contains(listAfterSignIn.get(i).getText())) {
                    LOG.debug("a) Missing tray titles after login when compared to list before login");
                    LOG.info(i+")" + listBeforeSignIn.get(i).getText());
                }
            }

            for(int i = 0; i <= no_of_trays; i++) {
                if(!listBeforeSignIn.get(i).getText().contains(listAfterSignIn.get(i).getText())) {
                    LOG.debug("b) additional tray title after login");
                    LOG.info(i+")" + listAfterSignIn.get(i).getText());
                }
            }
            } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
