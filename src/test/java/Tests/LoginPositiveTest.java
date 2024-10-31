package Tests;
import Pages.AccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import Tests.ObjectModels.LoginModel;
import Utils.AllureTestListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
@Listeners({AllureTestListener.class})
@Epic("Smoke Tests")
@Feature("Login Positive Tests")
public class LoginPositiveTest extends BaseTest {
    @DataProvider(name = "jsonDataProvider")
    public Iterator<Object[]> jsonDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        File f = new File("src\\test\\resources\\data\\loginPositiveTestData.json");
        LoginModel[] lms = objectMapper.readValue(f, LoginModel[].class);

        for (LoginModel lm : lms)
            dp.add(new Object[]{lm});

        return dp.iterator();
    }
    private void printData(LoginModel lm) {
        System.out.println(lm);
    }
    @Test(
            dataProvider = "jsonDataProvider",
            description = "Login positive test with JSON data provider",
            groups = {"Smoke"}
    )
    public void loginPositiveWithJsonDataProviderTest(LoginModel lm) {
        printData(lm);
        driver.get(baseUrl);

        HomePage homePage = new HomePage(driver);
        homePage.verifyPageURL();
        homePage.goToLoginPage();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.verifyPageURL();
        Assert.assertTrue(loginPage.getCheckboxNameSelector().isDisplayed());
        loginPage.login(lm.getAccount().getEmail(),lm.getAccount().getPassword());
        System.out.println(loginPage.getLoginSuccessSelectorText());
        Assert.assertEquals(loginPage.getLoginSuccessSelectorText(),"Contul meu");

        AccountPage accountPage = new AccountPage(driver);
        accountPage.clickLogoutLink();

    }

}
