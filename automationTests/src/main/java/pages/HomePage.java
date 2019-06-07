package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public List<WebElement> getNumberOfTopics() {
        By tray_locator = By.xpath("//div[@class='trays']//div[contains(@class, 'tray-container')]//div[contains(@class, 'tray-carousel')]//a[contains(@class, 'tray-link')]");
        return driver.findElements(tray_locator);
    }

    public void signinClick(){
        driver.findElement(By.xpath("//div[@class='signIn']")).click();
    }

    public void login(int mobileNumber) {
        // enter the mobile number and click on sign in
        //get the otp from the db for this number and enter and login

    }

}
