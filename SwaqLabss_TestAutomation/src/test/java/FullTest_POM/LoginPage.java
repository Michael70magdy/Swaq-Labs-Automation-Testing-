package FullTest_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage  {
    WebDriver driver;

    // Locators
    private By userNameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessageContainer = By.cssSelector("div.error-message-container.error");
    private By swaqLabTitle = By.xpath("/html/body/div/div/div/div[1]/div[1]/div[2]/div");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUserName(String userName) {
        driver.findElement(userNameField).sendKeys(userName);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isUserNameFieldDisplayed() {
        return driver.findElement(userNameField).isDisplayed();
    }

    public boolean isPasswordFieldDisplayed() {
        return driver.findElement(passwordField).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public boolean isSwaqLabTitleDisplayed() {
        return driver.findElement(swaqLabTitle).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessageContainer).getText();
    }

    public void clearFields() {
        driver.findElement(userNameField).clear();
        driver.findElement(passwordField).clear();
    }
}
