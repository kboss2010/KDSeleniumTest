import com.thoughtworks.selenium.Selenium;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by KXD0643 on 2/6/2017.
 */
public class VerifyCanAddToCart extends SeleniumUtils {

    @Test
    public void VerifyCanAdd() {
        // Verify land in THD.com and can add hammer and nail to cart
        Assert.assertTrue("Error on step #1: Couldn't validate that Selenium landed in home page.", verifyLandingPage("http://www.homedepot.com", headerSearch));
        Assert.assertTrue("Error on step #2: Couldn't input hammer on home page.", verifyCanSearch("hammer", headerSearchInput, headerSearchButton));
        Assert.assertTrue ("Error on step #3: Couldn't add to cart.", verifyItemWithinPriceRange("price", itemWrapper, priceWrapper, addToCart));
        Assert.assertTrue ("Error on step #4: Couldn't close overlay.", closeOverlay(overlayPage));
        Assert.assertTrue("Error on step #5: Couldn't input nail on home page.", verifyCanSearch("nail", headerSearchInput, headerSearchButton));
        Assert.assertTrue("Error on step #6: Couldn't add Galvanized nail to cart.", verifyGalNail("Galvanized",itemWrapper,addToCart));
        Assert.assertTrue("Error on step #7: Couldn't verify Galvanized nail Smart Overlay and add Qty 1 to Cart", verifySmartOverlay("1",smartOverlay));
        Assert.assertTrue("Error on step #8: Couldn't verify 2 Items are in the Cart", verifyCart("2 items",itemsInCart));
        Assert.assertTrue("Error on step #9: Couldn't verify screwdriver and landing page", verifyCanSearch("screwdriver", headerSearchInput, headerSearchButton));
        Assert.assertTrue("Error on step #10: Couldn't add 3rd screwdriver to cart", huskytoCart("Husky", 3, addToCart));
        Assert.assertTrue("Error on step #11: Couldn't capture number of available screwdrivers", getNumbInStock(numberInStock));
        Assert.assertTrue("Error on step #12: Couldn't verify screwdriver nail Smart Overlay and add Qty 1 to Cart", verifySmartOverlay("1",smartOverlay));
        Assert.assertTrue("Error on step #13: Couldn't verify 3 Items are in the Cart nor land in shopping cart", verifyCart("3 items",itemsInCart));
        Assert.assertTrue("Error on step #14: Couldn't verify that we landed on the Shopping Cart Page", shoppingCartVerify(shoppingCartPage));
        Assert.assertTrue("Error on step #00: Couldn't PrintOut", printOut());
        Assert.assertTrue("Error on step #15: Couldn't verify if any items in cart can't be shipped to home", shipToHomeVerify(shipToHomeIndicator));
        Assert.assertTrue("Error on step #16: Couldn't Change Hammer Qty to 2", shoppingCartChangeHammerQty("2",1));
        Assert.assertTrue("Error on step #17: Couldn't verify landed on 'Choose Your Secure Checkout Method' page", shoppingCartCheckout("Choose Your Secure Checkout Method"));
        Assert.assertTrue("Error on step #18: Couldn't verify that we landed on the Shopping Cart Page", secureCheckoutPage (shoppingCartPage));
        Assert.assertTrue("Error on step #19: Couldn't remove Galvanized Nail", removeGalNail ("0"));
        Assert.assertTrue("Error on step #20: Couldn't Change Hammer Qty to 1", shoppingCartChangeHammerQty("1",2));
        Assert.assertTrue("Error on step #21: Couldn't Add Hammer to List", addHammerToList());
        Assert.assertTrue("Error on step #22: Couldn't Add Select Express Delivery for Hammer", expressDeliv());
        Assert.assertTrue("Error on step #23: Couldn't Remove Hammer", removeHammer());
        Assert.assertTrue("Error on step #24: Couldn't Remove Screwdriver", removeScrewdriver());
     }

   /* @AfterClass
    public static void cleanUp () {
        driver.close();
        }*/
    }