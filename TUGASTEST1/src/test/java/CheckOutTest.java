import com.juaracoding.CartPage;
import com.juaracoding.CheckOutPage;
import com.juaracoding.LoginPage;
import com.juaracoding.ProduckPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckOutTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\PT-DIKA.DESKTOP-JCSAIMT\\MyTools\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void validCheckoutTest() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Step 2: Add products to the cart
        ProduckPage productPage = new ProduckPage(driver);
        productPage.addFirstProductToCart();
        productPage.addSecondProductToCart();
        productPage.goToCart();

        // Step 3: Proceed to checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        // Step 4: Fill in checkout details
        CheckOutPage checkoutPage = new CheckOutPage(driver);
        checkoutPage.fillDetails("John", "Doe", "12345");
        checkoutPage.continueCheckout();

        // Assert: Verify successful redirection to next step
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"));
    }

    @Test
    public void emptyCheckoutDetailsTest() {
        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Step 2: Tambah Produk ke keranjang
        ProduckPage productPage = new ProduckPage(driver);
        productPage.addFirstProductToCart();
        productPage.addSecondProductToCart();
        productPage.goToCart();

        // Step 3: Proses Checkout
        CartPage cartPage = new CartPage(driver);
        cartPage.proceedToCheckout();

        // Step 4: Biarkan detail pembayaran kosong dan lanjutkan
        CheckOutPage checkoutPage = new CheckOutPage(driver);
        checkoutPage.fillDetails("", "", "");
        checkoutPage.continueCheckout();

        // Verify error message
        Assert.assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required");
    }
}
