package dns.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BasketPage extends BasePage {


    private static String goodsPriceXpath = "//div[@data-cart-product-id][%s]//span[@class='price__current']";

    private static String goodsNameXpath = "(//div[@class='cart-items__product-name'])[%s]";

    private static String deleteGoods = "(//button[contains(text(), 'Удалить')])[%s]";

    private static String nameGoods = "//a[contains(text(), '%s')]";


    @FindBy(xpath = "//div[@class='total-amount__label']//span[@class='price__current']")
    WebElement basketTotalPrice;

    @FindBy(xpath = "//div[@data-commerce-target = 'basket_additional_warranty_24']")
    WebElement warranty;

    @FindBy(xpath = "//div[@data-cart-product-id][1]//*[@class='count-buttons__icon-plus']")
    WebElement addGoods;

    @FindBy(xpath = "//a[@class='empty-message-restore-btn ui-link_pseudolink']")
    WebElement goodsReturn;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
    }

    public BasketPage() {

    }


    public boolean checkTotalPriceBasket() {
        String price = basketTotalPrice.getText().replaceAll("\\s+", "");
        Double priceNow = Double.parseDouble(price);
        Double expectPrice = BasketV.getPrices().values().stream().reduce(Double::sum).get();
        return priceNow.equals(expectPrice);
    }

    public boolean checkGoodsPrice(String num) {
        By placePrice = By.xpath(String.format(goodsPriceXpath, num));
        By placeName = By.xpath(String.format(goodsNameXpath, num));
        WebElement goodsPrice = driver.findElement(placePrice);
        WebElement goodsName = driver.findElement(placeName);
        String priceGoods = goodsPrice.getText().replaceAll("\\s+", "");
        Double price = Double.parseDouble(priceGoods);
        Double expectGoodsPrice = BasketV.getPrices().get(goodsName.getText());
        return price.equals(expectGoodsPrice);
    }

    public void clickToWarranty24() {
        waitPrice(basketTotalPrice, warranty);
    }

    public void saveWarrantyPrice() {
        String correctFormatOfPrice = basketTotalPrice.getText().replaceAll("\\s+", "");
        Double price = Double.parseDouble(correctFormatOfPrice);
        BasketV.getPrices().clear();
        BasketV.addInBasket("Товаров с гарантией", price);
    }

    public boolean checkGoods(String name) {
        By place = By.xpath(String.format(nameGoods, name));
        return driver.findElements(place).isEmpty();
    }

    public void returnGoods() {
        waitElementClick(goodsReturn).click();
    }

    public void deleteGoods(String productNumber) {
        By placeDeleteGoods = By.xpath(String.format(deleteGoods, productNumber));
        By placeName = By.xpath(String.format(goodsNameXpath, productNumber));
        WebElement goodsDelete = driver.findElement(placeDeleteGoods);
        WebElement goodsName = driver.findElement(placeName);

        BasketV.getPrices().remove(goodsName.getText());

        waitElementClick(goodsDelete);
        goodsDelete.click();
    }

    public WebElement addGoodWithPlus() {
        return addGoods;
    }

    public BasketPage addGoodsInBasket(WebElement element) {
        element.click();
        return new BasketPage(driver);
    }


}
