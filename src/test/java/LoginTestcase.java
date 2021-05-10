import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wanbi.HomePage;
import org.wanbi.LoginPage;

/**
 * @Author: Alina
 * @Date: 2021/5/8 18:31
 */
public class LoginTestcase extends BaseTestcase {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeClass
    public void beforeMethod() throws InterruptedException {
        System.out.println("*************开始登录页面的校验*************");
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);
        // 退出
        System.out.println("Logout!!!");
        homePage.logoutAction();
    }

    /**
     * 常规登录验证：正确的用户名、密码
     * @throws InterruptedException
     */
    @Test(priority = 1, alwaysRun = true, groups = {"checklist-login"}, description = "Login-1:正常登录验证")
    public void loginSuc() throws InterruptedException {
        loginPage.idUserNameInput.clear();
        loginPage.idUserNameInput.sendKeys("admin");
        loginPage.idPasswordInput.clear();
        loginPage.idPasswordInput.sendKeys("123456");
        loginPage.cssLoginBtn.click();
        assert loginPage.nickname.getText().contains("您好，");
        assert loginPage.nickname.getText().contains("ccui");
        Thread.sleep(1000);
        // 退出
        System.out.printf("Logout!!!!!!");
        homePage.logoutAction();
    }
}
