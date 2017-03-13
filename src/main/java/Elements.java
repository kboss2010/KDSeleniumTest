import org.openqa.selenium.By;

/**
 * Created by KXD0643 on 2/6/2017.
 */
public interface Elements {

    //General Elements
    String headerSearch = ".//input[@id='headerSearch']";
    String headerSearchInput = ".//input[@id='headerSearch']";
    String headerSearchButton = ".//button[@id='headerSearchButton']";
    String itemWrapper = ".//div[@class='pod-inner']";
    String addToCart = ".//a/span[contains(text(),'Add To Cart')]";
    String itemDescription = ".//div[@class='pod-plp__description']";
    String priceWrapper = ".//*[@class='price']";
    String itemQty = ".//div/input[@class='input-mini localStr-Qnt smrtQtyInp']";
    String itemsInCart = ".//*[@id='headerCart']/div[1]/span[2]";
    String numberInStock = ".//*[@class='smrtOvNumInStock b']";


    //Overlay Elements
    String overlayPage = ".//a[@id='atc-continue-shopping']";
    String smartOverlay = ".//*[@id='smartOverlay']";
    String addToCartOverlay = ".//*[contains(text(),'ADD TO CART')]";


    //Nail Stuff
    String galNail = ".//a[contains(@href,'Galvanized')]";

    //Shopping Cart & Checkout
    String shoppingCartPage = ".//*[@id='ShopCartForm']";
    String shoppingCartItemWrapper2 = ".//div[@class='col__12-12 u__p-side-off']";
    String shoppingCartItemWrapper = ".//div[@class='tpl-content']";

    String shoppingCartItemDescription = ".//div[@class='m-right-small']";
    String shipToHomeIndicator = ".//div[@class='radio-header-wrapper']/div[@class='u__normal ' and contains(text(),'Ship to Home')]";
    String shoppingCartQty = ".//div[@class='qty-wrapper quantity-section u__text-align--right']/input[@value]";
    String shoppingCartHammer = ".//a[contains(@href,'Hammer')]";
    String shoppingCartCheckoutNow = ".//a/span[contains(text(),'Checkout Now')]";
    String shoppingCartSecureCheckoutPage = ".//*[@class='page_title']";
    String shoppingCartSecureViewCartLink = ".//*[@id='LoginViewCart']";
    String shoppingCartAddToList = ".//*[@class='u__m-bottom-xsmall dual-sign-in-pop-up addToListMCC u__default-link body__cursor--pointer mcmMyList']";
    String expressDeliveryIndicator = ".//div[@class='radio-header-wrapper']/div[@class='u__normal ' and contains(text(),'Express')]";
    String expressDeliveryIndicator2 = ".//div[@class='radio-header-wrapper']/div[@class='u__normal ' and contains(text(),'Express')]/following-sibling::div[@class='deliveryServiceContainer']/span";
    String removeButton = ".//div[@class='u__m-bottom-xsmall']/a[@class='delete-cart-item u__default-link' and contains (text () , 'Remove')]";
    String shoppingCartScrewdriver = ".//a[contains(@href,'Screwdriver')]";

}
