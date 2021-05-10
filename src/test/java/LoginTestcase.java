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
        Thread.sleep(1000);
        // 退出
        System.out.printf("Logout!!!!!!");
        homePage.logoutAction();
    }

    /**
     * 用户名错误
     */
    @Test(priority = 2, alwaysRun = true, groups = {"checklist-login"}, description = "Login-2:用户名错误提示信息验证")
    public void loginWithWrongUsername() {
        loginPage.idUserNameInput.clear();
        loginPage.idUserNameInput.sendKeys("admin1");
        loginPage.idPasswordInput.clear();
        loginPage.idPasswordInput.sendKeys("123456");
        loginPage.cssLoginBtn.click();
        assert loginPage.cssAlertMes.getText().equals("账户或密码错误");
    }

    /**
     * 密码错误
     */
    @Test(priority = 3, alwaysRun = true, groups = {"checklist-login"}, description = "Login-3:密码错误提示信息验证")
    public void loginWithWrongPassword() {
        driver.navigate().refresh();
        loginPage.idUserNameInput.sendKeys("admin");
        loginPage.idPasswordInput.sendKeys("12345");
        loginPage.cssLoginBtn.click();
        assert loginPage.cssAlertMes.getText().equals("账户或密码错误");
    }

    /**
     * 用户名缺失
     */
    @Test(priority = 4, alwaysRun = true, groups = {"checklist-login"}, description = "Login-4:用户名缺失提示信息验证")
    public void loginWithoutUsername() {
        driver.navigate().refresh();
        loginPage.idUserNameInput.sendKeys("");
        loginPage.idPasswordInput.sendKeys("123456");
        loginPage.cssLoginBtn.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert loginPage.cssAlertKongUsername.getText().equals("请输入用户名!");
    }

    /**
     * 用户名、密码均缺失
     */
    @Test(priority = 5, alwaysRun = true, groups = {"checklist-login"}, description = "Login-5:密码缺失提示信息验证")
    public void loginWithoutPassword() {
        driver.navigate().refresh();
        loginPage.idUserNameInput.sendKeys("");
        loginPage.idPasswordInput.sendKeys("");
        loginPage.cssLoginBtn.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert loginPage.cssAlertKongPassword.getText().equals("请输入密码！");
    }

}
