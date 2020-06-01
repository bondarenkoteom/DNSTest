package dns.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Search extends BasePage {

    @FindBy(xpath = "//input[@placeholder = 'Поиск по сайту']")
    WebElement searchInput;

    @FindBy(xpath = "//form[@action='/search/']/div/div[2]/span[2]")
    WebElement searchButton;


    public void searchAndClick(String text) {
        waitElementClick(searchInput).sendKeys(text);
        waitElementClick(searchButton).click();

    }


}
