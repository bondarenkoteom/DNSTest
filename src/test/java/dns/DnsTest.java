package dns;

import dns.pages.BasketPage;
import dns.pages.ProductPage;
import dns.pages.ResultPage;
import dns.pages.Search;
import org.junit.Assert;
import org.junit.Test;


public class DnsTest extends BaseTest {



    @Test
    public void startTest() throws InterruptedException {
        new Search().searchAndClick("playstation"); // в поиске ищем playstation | 2 пункт ДЗ
        ResultPage resultPage = new ResultPage();
        resultPage.choose("PlayStation 4 Slim Black"); // кликнуть по playstation 4 slim black | 3 пункт ДЗ
        ProductPage productPageFirst = new ProductPage();
        productPageFirst.addInBasket(); //  запомнить цену, Нажать Купить | 4 и 5 пункт ДЗ
//        productPageFirst.waitElementVisibleBasket(); //ждем отображения элемента в корзине

        new Search().searchAndClick("Detroit"); // вполнить поиск Detroit | 6 пункт ДЗ
        ProductPage productPageSecond = new ProductPage();
        productPageSecond.addInBasket(); // запомнить цену, Нажать Купить | 7 и 8 пункт ДЗ
        productPageSecond.waitElementVisibleBasket(); // ждем отображения элемента в корзине
        productPageSecond.goInBasket(); // перейри в корзину | 10 пункт ДЗ

        BasketPage basketPage = new BasketPage();
        Assert.assertTrue("Сумма не равна", basketPage.checkTotalPriceBasket()); // проверить сумму | 11 пункт ДЗ
        Assert.assertTrue("Цена на 1ый товар не равна", basketPage.checkGoodsPrice("1")); //проверить цену 1ого товара | 11 пункт ДЗ
        Assert.assertTrue("Цена на 2ой товар не равна", basketPage.checkGoodsPrice("2")); //проверить цену 2ого товара | 11 пункт ДЗ

        basketPage.clickToWarranty24(); // в корзине для playstation Доп.гарантия - выбрать 24 месяца | 12 пункт ДЗ
        basketPage.saveWarrantyPrice(); // дождаться изменения цены и запомнить цену с гарантией | 13 пункт ДЗ
        basketPage.deleteGoods("2"); // удалить из корзины Detroit | 14 пункт ДЗ
        basketPage.clickToWarranty24();
        basketPage.saveWarrantyPrice();

        Assert.assertTrue("Товар не удален", basketPage.checkGoods("Detroit")); // проверить что Detroit нет больше в корзине | 15 пункт ДЗ
        Assert.assertTrue("Цена после удаления 'Detroit' не стала меньше)", basketPage.checkTotalPriceBasket()); // сумма уменьшилась на цену Detroit  | 15 пункт ДЗ

        basketPage.addGoodsInBasket(basketPage.addGoodWithPlus()); // добавить еще 2 playstation (кнопкой +) | 16 пункт ДЗ
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        basketPage.addGoodsInBasket(basketPage.addGoodWithPlus());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        basketPage.saveWarrantyPrice();

        Assert.assertTrue("Цена после добавление 2х приставок не зименилась", basketPage.checkTotalPriceBasket()); // проверить что сумма верна | 16 пункт ДЗ

        basketPage.deleteGoods("1"); //  удалить Playstation из корзины  | 17 пункт ДЗ
        basketPage.returnGoods(); //  нажать вернуть удаленный товаров | 18 пункт ДЗ

        Assert.assertTrue("Цена и гарантия после возвращения товара в корзину не изменились", basketPage.checkTotalPriceBasket());

    }
}
