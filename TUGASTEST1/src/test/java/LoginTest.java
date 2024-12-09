import com.juaracoding.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
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
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void invalidUsernameTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid_user", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(), "Username dan Password tidak sinkron");
    }

    @Test
    public void invalidPasswordTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "1234556");
        Assert.assertEquals(loginPage.getErrorMessage(), "Username dan Password tidak sinkron");
    }
}
