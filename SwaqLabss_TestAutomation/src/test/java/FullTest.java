import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FullTest {

    public WebDriver Driver ;
    @BeforeTest
    public void SetupPage()
    {
        Driver = new ChromeDriver();
        Driver.get( "https://www.saucedemo.com/");
        Driver.manage().window().maximize();
    }
    @Test(priority = 1)
    public void VerifyElementsPresent() throws InterruptedException {
        WebElement UserNameField = Driver.findElement(By.id("user-name"));
        WebElement PasswordField = Driver.findElement(By.id("password"));
        WebElement LoginBtn = Driver.findElement(By.id("login-button"));
        Thread.sleep(300);
        Assert.assertTrue(UserNameField.isDisplayed(), "Username field is not displayed.");
        Assert.assertTrue(PasswordField.isDisplayed(), "Password field is not displayed.");
        Assert.assertTrue(LoginBtn.isDisplayed(), "Login button is not displayed.");
    }
    @DataProvider(name = "validCredentials")
    public Object[][] validCredentialsProvider() {
        return new Object[][]{
                {"standard_user", "secret_sauce"}
        };
    }
    @Test(dataProvider = "validCredentials" ,priority = 2)
    public void TestValidLogin(String UserName,String Password) throws InterruptedException {
        Thread.sleep(300);
        Driver.findElement(By.id("user-name")).sendKeys(UserName);
        Driver.findElement(By.id("password")).sendKeys(Password);
        Driver.findElement(By.id("login-button")).click();
        Thread.sleep(300);
        WebElement SwaqLabTitle = Driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[2]/div"));
        Assert.assertTrue(SwaqLabTitle.isDisplayed(),"Unsuccessful Login Attempt ");
    }
    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentialsProvider() {
        return new Object[][]{
                {"wrong_user", "wrong_password","Epic sadface: Username and password do not match any user in this service"}
        };
    }
    @Test(dataProvider = "invalidCredentials" ,priority = 3)
    public void TestinValidLogin(String UserName,String Password,String ExpectedMesseage) throws InterruptedException {
        Thread.sleep(300);
        Driver.navigate().back();
        Driver.findElement(By.id("user-name")).sendKeys(UserName);
        Driver.findElement(By.id("password")).sendKeys(Password);
        Driver.findElement(By.id("login-button")).click();
        Thread.sleep(300);
        WebElement ErrorMesseage = Driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div/form/div[3]/h3"));
        Assert.assertTrue(ErrorMesseage.isDisplayed(),"Unsuccessful Login Attempt ");
        Assert.assertEquals(ErrorMesseage.getText(), ExpectedMesseage, "Error message text does not match.");

    }
    @DataProvider(name = "EmptyCredentials")
    public Object[][] EmptyCredentials() {
        return new Object[][]{
                {"", "secret_sauce","Epic sadface: Username is required"},
                {"standard_user", "","Epic sadface: Password is required"},

        };
    }
    @Test(dataProvider = "EmptyCredentials" ,priority = 4)
    public void TestEmptyLogin(String UserName,String Password,String ExpectedMesseage) throws InterruptedException {
        Driver.findElement(By.id("user-name")).clear();
        Driver.findElement(By.id("password")).clear();
        Thread.sleep(300);
        Driver.navigate().refresh();
        Driver.findElement(By.id("user-name")).sendKeys(UserName);
        Driver.findElement(By.id("password")).sendKeys(Password);
        Driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);
        WebElement ErrorUserMesseage = Driver.findElement(By.cssSelector("div.error-message-container.error"));
        Assert.assertEquals(ErrorUserMesseage.getText(), ExpectedMesseage, "Error message text does not match.");
    }

    @AfterTest
    public void TearDown()
    {
        Driver.quit();
    }


}
