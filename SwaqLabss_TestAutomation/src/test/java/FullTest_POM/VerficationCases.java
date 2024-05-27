package FullTest_POM;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class VerficationCases extends BaseCases{
    @Test(priority = 1)
    public void verifyElementsPresent() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(300);
        Assert.assertTrue(loginPage.isUserNameFieldDisplayed(), "Username field is not displayed.");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field is not displayed.");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed.");
    }

    @DataProvider(name = "validCredentials")
    public Object[][] validCredentialsProvider() {
        return new Object[][]{
                {"standard_user", "secret_sauce"}
        };
    }

    @Test(dataProvider = "validCredentials", priority = 2)
    public void testValidLogin(String userName, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(300);
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Thread.sleep(300);
        Assert.assertTrue(loginPage.isSwaqLabTitleDisplayed(), "Unsuccessful Login Attempt");
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentialsProvider() {
        return new Object[][]{
                {"wrong_user", "wrong_password", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "invalidCredentials", priority = 3)
    public void testInvalidLogin(String userName, String password, String expectedMessage) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        Thread.sleep(300);
        driver.navigate().back();
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Thread.sleep(300);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Error message text does not match.");
    }

    @DataProvider(name = "emptyCredentials")
    public Object[][] emptyCredentialsProvider() {
        return new Object[][]{
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}
        };
    }

    @Test(dataProvider = "emptyCredentials", priority = 4)
    public void testEmptyLogin(String userName, String password, String expectedMessage) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearFields();
        Thread.sleep(300);
        driver.navigate().refresh();
        loginPage.enterUserName(userName);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Thread.sleep(3000);
        Assert.assertEquals(loginPage.getErrorMessage(), expectedMessage, "Error message text does not match.");
    }
}
