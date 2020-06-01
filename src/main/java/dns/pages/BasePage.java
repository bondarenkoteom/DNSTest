package dns.pages;

import dns.Init;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;
    WebDriverWait wait;

    public BasePage() {
        driver = Init.getDriver();
        wait = new WebDriverWait(driver, 5);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@class='cart-link__price']")
    WebElement goInBasket;

    String check = "//span[@class='cart-link__badge']";//когда загорается в корзине цифра


    public WebElement waitElementClick(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitElementVisibleBasket() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(check))); //жду когда загориться
    }

    public void goInBasket() {
        waitElementClick(goInBasket).click();
    }

    public void waitPrice(WebElement totalPrice, WebElement changePrice) {
        String oldPrice = totalPrice.getText();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        waitElementClick(changePrice).click();
        wait.until(webDriver -> {
            String currentValue = totalPrice.getText();
            return !currentValue.equals(oldPrice);
        });
    }


}
