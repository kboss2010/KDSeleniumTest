import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by KXD0643 on 2/6/2017.
 */
public class SeleniumUtils implements Elements {
    //Selenium Properties
    public static final int MAX_WAIT_SECS = 10;
    public static final String DRIVER_LOCATION = "C:\\Users\\kxd0643\\Desktop\\SeleniumTest\\src\\main\\resources\\chromedriver.exe";

    public static WebDriver driver;

    public SeleniumUtils() {
        System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
    }

    public boolean waitUntilElementDisplayed(String expression) {
        int counter = 0;
        do {
            if (verifyDisplayed(expression)) {
                return true;
            } else if (counter > 3) {
                if (verifyEnabled(expression)) {
                    return true;
                } else if (verifyLocation(expression)) {
                    return true;
                }
            }

            counter++;
            try {
                TimeUnit.MILLISECONDS.sleep(950);
            } catch (Exception e) {
                return false;
            }
        } while (counter < 10);

        return false;
    }

    public boolean verifyDisplayed(String expression) {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(By.xpath(expression)).isDisplayed()) {
                return true;
            }
        } catch (Exception ne) {
            return false;
        }
        return false;
    }

    public boolean verifyEnabled(String expression) {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(By.xpath(expression)).isEnabled()) {
                return true;
            }
        } catch (Exception ne) {
            return false;
        }
        return false;
    }

    public boolean verifyLocation(String expression) {
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(By.xpath(expression)).getLocation().x < 0 ||
                    driver.findElement(By.xpath(expression)).getLocation().y < 0) {
                return true;
            }
        } catch (Exception ne) {
            return false;
        }
        return false;
    }

    public boolean navigatePage(String url) {
        try {
            driver.get(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyLandingPage(String url, String landingPage) {
        if (navigatePage(url)) {
            if (waitUntilElementDisplayed(landingPage)) {
                return true;
            }
        }
        return false;
    }

    public WebElement getElement(WebElement elm,String xpath)
    {
        try{
            return elm.findElement(By.xpath(xpath));
        }
        catch(NoSuchElementException ex)
        {
            return null;
        }
    }


    //Actual Testing Starts Here
/*-------------------------------------------------------------------------------------------------------------
---------------------------------------- Hammer, Nail & Screwdriver Search -----------------------------------------------
-------------------------------------------------------------------------------------------------------------*/

    public boolean verifyCanSearch(String item, String inputId, String headerSearchButton) {
        try {
            TimeUnit.SECONDS.sleep(3);
            driver.findElement(By.xpath(headerSearch)).clear();
            driver.findElement(By.xpath(inputId)).sendKeys(item);
            TimeUnit.SECONDS.sleep(3);
            driver.findElement(By.xpath(headerSearchButton)).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    /*-------------------------------------------------------------------------------------------------------------
Hammer Search // Find Prics >10 <15 // Add to Cart
-------------------------------------------------------------------------------------------------------------*/

    public boolean verifyItemWithinPriceRange(String item, String itemWrapper, String priceWrapper, String addToCart) {
        try {
            TimeUnit.SECONDS.sleep(30);//for display purposes
            //WebElement elm = driver.findElement(By.xpath(priceWrapper));


            List<WebElement> list;
            list = driver.findElements(By.xpath(itemWrapper));
            for (WebElement element : list) {
                String price = element.findElement(By.xpath(priceWrapper)).getText().split("\\$")[1].trim();
                int pricelen = price.length();
                TimeUnit.SECONDS.sleep(3);

                String hammerPrice1 = price.substring(0, pricelen - 2);
                String hammerPrice2 = price.substring(pricelen - 2, pricelen);
                String priceReformat = hammerPrice1 + "." + hammerPrice2;

                Double dblPrice = Double.parseDouble(priceReformat);
                if (dblPrice < 15.00 && dblPrice > 10.00) {
                    TimeUnit.SECONDS.sleep(3);
                    element.findElement(By.xpath(addToCart)).click();
                    //         System.out.println("price:"+priceReformat);
                    return true;

                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }
    /*-------------------------------------------------------------------------------------------------------------
Hammer Search // Close Add to Cart Overlay and Return to Hammer Landing Page
-------------------------------------------------------------------------------------------------------------*/

    public boolean closeOverlay(String overlayPage) {
        try {

            if (waitUntilElementDisplayed(overlayPage)) ;
            {
                // driver.switchTo().frame(overlayPage);
                driver.findElement(By.xpath(overlayPage)).click();
                //  driver.switchTo().defaultContent();

                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
/*-------------------------------------------------------------------------------------------------------------
-------------------------------------- Galvanized Nail To Cart ----------------------------------------------
-------------------------------------------------------------------------------------------------------------*/

/*-------------------------------------------------------------------------------------------------------------
Galvanized Nail To Cart on landing page //
-------------------------------------------------------------------------------------------------------------*/

    public boolean verifyGalNail(String item, String itemWrapper, String addToCart) {
        try {

            List<WebElement> list;
            list = driver.findElements(By.xpath(itemWrapper));
            int count = 0;
            for (WebElement element : list) {
                TimeUnit.SECONDS.sleep(3);
                //  element.findElement(By.xpath(itemWrapper));
                //  TimeUnit.SECONDS.sleep(3);element

                try {
                    element.findElement(By.xpath(galNail));
                    count++;
                    TimeUnit.SECONDS.sleep(3);
                    if (count==2){
                    element.findElement(By.xpath(addToCart)).click();
                    TimeUnit.SECONDS.sleep(3);
                    //   element.findElement(By.xpath(itemQty)).sendKeys("1");
                    //     driver.findElement(By.partialLinkText(item));
                    return true;}

                } catch (NoSuchElementException e) {
                    continue;
                }
                // driver.findElement(By.xpath(galNail));


            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

   /*-------------------------------------------------------------------------------------------------------------
Galvanized Nail & Screwdriver To Cart // Verify Smart Overlay // Add 1 To Cart // Close Overlay
-------------------------------------------------------------------------------------------------------------*/

    public boolean verifySmartOverlay(String item, String smartOverlay) {
        try {

            if (waitUntilElementDisplayed(smartOverlay)) ;
            {
                driver.findElement(By.xpath(itemQty)).sendKeys(item);
                TimeUnit.SECONDS.sleep(3);
                driver.findElement(By.xpath(addToCartOverlay)).click();
                TimeUnit.SECONDS.sleep(3);
                driver.findElement(By.xpath(overlayPage)).click();
                TimeUnit.SECONDS.sleep(3);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }


/*-------------------------------------------------------------------------------------------------------------
Galvanized Nail & Screwdriver To Cart // Verify 2 or 3 Items in Cart // If 3 Click on Cart
-------------------------------------------------------------------------------------------------------------*/

    public boolean verifyCart(String item, String itemsInCart) {
        String str = driver.findElement(By.xpath(itemsInCart)).getText();
        try {


            if (waitUntilElementDisplayed(itemsInCart)) ;
            {
                driver.findElement(By.xpath(itemsInCart)).getText().equalsIgnoreCase(item);
                // String str = driver.findElement(By.xpath(itemsInCart)).getText();

                if (str.contains("3"))
                {
                    driver.findElement(By.xpath(itemsInCart)).click();
                    return true;

                }else return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /*-------------------------------------------------------------------------------------------------------------
-------------------------------------- Screw Driver To Cart ----------------------------------------------
-------------------------------------------------------------------------------------------------------------*/


    /*-------------------------------------------------------------------------------------------------------------
Screw Driver // Add Third Husky to Cart from landingPage
-------------------------------------------------------------------------------------------------------------*/

    public boolean huskytoCart(String item, int item2, String addToCart) {
        try {
            TimeUnit.SECONDS.sleep(3);
            driver.findElement(By.xpath(itemWrapper)).getText();

            List<WebElement> list;
            list = driver.findElements(By.xpath(itemWrapper));
            int counter = 0;

            for (WebElement element : list) {
                if (waitUntilElementDisplayed(itemDescription)) ;
                {
                    String str = element.findElement(By.xpath(itemDescription)).getText();

                    if (str.contains(item)) {
                        counter++;
                        if (counter == item2) {
                            element.findElement(By.xpath(addToCart)).click();
                            return true;
                        }
                    }
                }

            }
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

          }

    }


/*-------------------------------------------------------------------------------------------------------------
    Screw Driver // Verify # Instock
-------------------------------------------------------------------------------------------------------------*/

    public boolean getNumbInStock(String numberInStock) {
        try {
            if (waitUntilElementDisplayed(overlayPage)) ;{

            String str = driver.findElement(By.xpath(numberInStock)).getText();
                System.out.println("Available screwdrivers in store: "+ str);

            return true;
            }


        } catch (Exception e) {
            e.printStackTrace();
            return false;


        }
    }

     /*-------------------------------------------------------------------------------------------------------------
-------------------------------------- Shopping Cart Page ----------------------------------------------
-------------------------------------------------------------------------------------------------------------*/


/*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // Verify Shipped to Home
-------------------------------------------------------------------------------------------------------------*/

    public boolean shoppingCartVerify (String shoppingCartPage) {
        try {
            if (waitUntilElementDisplayed(shoppingCartPage)) ;
            return true;


            } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

/*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // Record items that can't be shipped to home
-------------------------------------------------------------------------------------------------------------*/


    public boolean shipToHomeVerify (String shipToHomeIndicator) {
        try {
            TimeUnit.SECONDS.sleep(3);

            List<WebElement> list = null;
            if (waitUntilElementDisplayed(shoppingCartItemWrapper)) {
                list = driver.findElements(By.xpath(shoppingCartItemWrapper));
            }

            for (WebElement element : list) {

                if (getElement(element,shipToHomeIndicator) == null){
                    element.findElement(By.xpath(shoppingCartItemDescription)).getText();

                    //System.out.println(element.findElement(By.xpath(shoppingCartItemDescription)).getText());
                    return true;
                }

            }
            return false;

         } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }

/*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // Change hammer Qty
-------------------------------------------------------------------------------------------------------------*/

    public boolean shoppingCartChangeHammerQty (String item, int count) {
        try {
            List<WebElement> list;
            list = driver.findElements(By.xpath(shoppingCartItemWrapper));
            int counter = 0;

            for (WebElement element : list) {

                try {

                    String str = element.findElement(By.xpath(shoppingCartItemDescription)).getText();

                    counter++;

                    if (getElement(element,shoppingCartHammer )!= null)
                    {
                            element.findElement(By.xpath(shoppingCartQty)).sendKeys(Keys.BACK_SPACE);
                            element.findElement(By.xpath(shoppingCartQty)).sendKeys(item);
                            element.findElement(By.xpath(shoppingCartQty)).sendKeys(Keys.ENTER);
                            TimeUnit.SECONDS.sleep(3);

                        if (count == 1){
                          //  System.out.println("Hammer: "+ str);
                            //
                        }
                            return true;

                        }

                } catch (NoSuchElementException e) {
                    continue;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }return false;
    }

    /*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // "Checkout Now" & Verify "Choose Your Secure Checkout Method" page
-------------------------------------------------------------------------------------------------------------*/

    public boolean shoppingCartCheckout (String item) {
        try {

            driver.findElement(By.xpath(shoppingCartCheckoutNow)).click();
            TimeUnit.SECONDS.sleep(3);

            if (waitUntilElementDisplayed(shoppingCartSecureCheckoutPage));{
                TimeUnit.SECONDS.sleep(3);
                driver.findElement(By.xpath(shoppingCartSecureCheckoutPage)).equals(item);

                return true;
            }
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

   /*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // Click on View Cart & land on "Shopping Cart" page
-------------------------------------------------------------------------------------------------------------*/

    public boolean secureCheckoutPage (String shoppingCartPage) {
        try {

            driver.findElement(By.xpath(shoppingCartSecureViewCartLink)).click();
            TimeUnit.SECONDS.sleep(3);

            if (waitUntilElementDisplayed(shoppingCartPage));{

                return true;
            }
        }

        catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

 /*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // Remove Galvanized Nail
-------------------------------------------------------------------------------------------------------------*/

    public boolean removeGalNail (String item) {
        try {
                List<WebElement> list;
                list = driver.findElements(By.xpath(shoppingCartItemWrapper));
                int counter = 0;

                for (WebElement element : list) {

                    try {
                        String str = element.findElement(By.xpath(shoppingCartItemDescription)).getText();
                        counter++;

                        if (getElement(element,galNail )!= null)
                        {
                         //   System.out.println("Nail: "+ str);
                            element.findElement(By.xpath(shoppingCartQty)).sendKeys(Keys.BACK_SPACE);
                            element.findElement(By.xpath(shoppingCartQty)).sendKeys(item);
                            element.findElement(By.xpath(shoppingCartQty)).sendKeys(Keys.ENTER);
                            TimeUnit.SECONDS.sleep(3);
                            return true;
                        }

                    } catch (NoSuchElementException e) {
                        continue;

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }return false;
        }


  /*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // Click "Add to List" for Hammer
-------------------------------------------------------------------------------------------------------------*/

    public boolean addHammerToList () {
        try {
            List<WebElement> list;
            list = driver.findElements(By.xpath(shoppingCartItemWrapper));
            int counter = 0;

            for (WebElement element : list) {

                try {
                    element.findElement(By.xpath(shoppingCartItemDescription)).getText();
                    counter++;

                    if (getElement(element,shoppingCartHammer )!= null)
                    {
                        element.findElement(By.xpath(shoppingCartAddToList)).click();
                        TimeUnit.SECONDS.sleep(3);
                        return true;

                    }

                } catch (NoSuchElementException e) {
                    continue;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }return false;
    }

/*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // Add Express Delivery
-------------------------------------------------------------------------------------------------------------*/

    public boolean expressDeliv () {
        try {
            List<WebElement> list;
            list = driver.findElements(By.xpath(shoppingCartItemWrapper));
            int counter = 0;

            for (WebElement element : list) {

                try {
                    element.findElement(By.xpath(shoppingCartItemDescription)).getText();
                    counter++;

                    if (getElement(element,shoppingCartHammer )!= null)
                    {
                        element.findElement(By.xpath(expressDeliveryIndicator)).click();
                        TimeUnit.SECONDS.sleep(10);

                        String str = driver.findElement(By.xpath(expressDeliveryIndicator2)).getText();
                        String str1 = str.substring(str.indexOf("$"));
                        System.out.println("Estimated Express Delivery cost: "+ str1);

                        TimeUnit.SECONDS.sleep(3);

                        return true;

                    }

                } catch (NoSuchElementException e) {
                    continue;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }return false;
    }
/*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // Remove Hammer From List
-------------------------------------------------------------------------------------------------------------*/

    public boolean removeHammer () {
        try {
            List<WebElement> list;
            list = driver.findElements(By.xpath(shoppingCartItemWrapper));
            int counter = 0;

            for (WebElement element : list) {

                try {
                    element.findElement(By.xpath(shoppingCartItemDescription)).getText();
                    counter++;

                    if (getElement(element,shoppingCartHammer )!= null)
                    {
                        element.findElement(By.xpath(removeButton)).click();
                        TimeUnit.SECONDS.sleep(3);
                        return true;

                    }

                } catch (NoSuchElementException e) {
                    continue;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }return false;
    }
/*-------------------------------------------------------------------------------------------------------------
    Shopping Cart Page // Remove Screwdriver From List
-------------------------------------------------------------------------------------------------------------*/

    public boolean removeScrewdriver () {
        try {
            List<WebElement> list;
            list = driver.findElements(By.xpath(shoppingCartItemWrapper));
            int counter = 0;

            for (WebElement element : list) {

                try {
                    //printOut(element);
                    counter++;

                    if (getElement(element,shoppingCartScrewdriver )!= null)
                    {
                        //System.out.println("Screwdriver: "+ str);
                        element.findElement(By.xpath(removeButton)).click();
                        TimeUnit.SECONDS.sleep(3);
                        return true;

                    }

                } catch (NoSuchElementException e) {
                    continue;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }return false;
    }
    /*-------------------------------------------------------------------------------------------------------------
        Print Method... Just b/c I like everything nice and neat. Lol
    -------------------------------------------------------------------------------------------------------------*/
public boolean printOut(){

    try {
        waitUntilElementDisplayed(shoppingCartItemWrapper2);

        List<WebElement> list;
        list = driver.findElements(By.xpath(shoppingCartItemWrapper2));
        int counter = 0;

        if(CollectionUtils.isEmpty(list)) {
            return false;
        }

        for (WebElement element : list) {


            String str = element.findElement(By.xpath(shoppingCartItemDescription)).getText();
            if (str.contains("Hammer")) {
                System.out.println("Hammer: " + str);
            } else if (str.contains("Nail")) {
                System.out.println("Nail: " + str);
            } else {
                System.out.println("Screwdriver: " + str);
            }

        }
    }
    catch(Exception ex) {
        return false;
      }

    return true;
    }


}




