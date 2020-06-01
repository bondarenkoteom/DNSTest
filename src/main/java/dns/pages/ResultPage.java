package dns.pages;

import org.openqa.selenium.By;

public class ResultPage extends BasePage {


    final String resultSearch = "//a[contains(text(), '%s')]";

    public void choose(String name) {
        By place = By.xpath(String.format(resultSearch, name));
        driver.findElement(place).click();
    }

}
