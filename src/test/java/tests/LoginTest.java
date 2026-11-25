package tests;

import org.testng.annotations.Listeners;
import utils.TestListener;
import base.BaseTest;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidCredentials() {

        return new Object[][] {
                {"wrong_user", "secret_sauce",
                        "Epic sadface: Username and password do not match any user in this service"},

                {"standard_user", "wrong_password",
                        "Epic sadface: Username and password do not match any user in this service"},

                {"", "secret_sauce",
                        "Epic sadface: Username is required"},

                {"standard_user", "",
                        "Epic sadface: Password is required"},

                {"locked_out_user", "secret_sauce",
                        "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(dataProvider = "invalidLoginData")
    public void verifyInvalidLogin(
            String username,
            String password,
            String expectedError) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(username, password);

        String actualError = loginPage.getErrorMessage();

        Assert.assertEquals(
                actualError,
                expectedError,
                "Error message is incorrect!"
        );
    }
}
