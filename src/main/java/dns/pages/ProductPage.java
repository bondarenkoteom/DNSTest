package dns.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ProductPage extends BasePage {


    @FindBy(xpath = "//span[@class='current-price-value']")
    WebElement productPrice;

    @FindBy(xpath = "//h1[@class]")
    WebElement productName;

    @FindBy(xpath = "//label[contains(text(),'Купить')]")
    WebElement basketButton;


    public void addInBasket() {
        String productNameXpath = waitElementClick(productName).getText();
        String priceProduct = (waitElementClick(productPrice).getAttribute("data-price-value"));
        Double price = Double.parseDouble(priceProduct);
        BasketV.addInBasket(productNameXpath, price);
        waitElementClick(basketButton).click();


    }

}
