package org.wanbi;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @Author: Alina
 * @Date: 2021/5/8 16:22
 */
public class LoginPage {
    @FindBy(how = How.CSS, using = "")
    public WebElement demo;

    /**
     * 登录元素
     */
    @FindBy(id = "username")
    public WebElement idUserNameInput;
    @FindBy(id = "password")
    public WebElement idPasswordInput;
    @FindBy(css = "button.ant-btn-lg")
    public WebElement cssLoginBtn;
    @FindBy(css = "span.content___1L20T>span")
    public WebElement nickname;  // 右上角"您好，cui"
    @FindBy(how = How.CSS, using = "div.ant-alert-message")
    public WebElement cssAlertMes;   //提示：账户或密码错误
    @FindBy(how = How.CSS, using = "form>div:nth-child(2)>div:nth-child(2) div.ant-form-item-explain div")
    public WebElement cssAlertKongUsername;   //提示：请输入用户名!
    @FindBy(how = How.CSS, using = "form>div:nth-child(2)>div:nth-child(3) div.ant-form-item-explain")
    public WebElement cssAlertKongPassword;   //提示：请输入密码！

    /**
     * This method will take two arguments ( Username nd Password)
     * 登录
     * @param sUserName
     * @param sPassword
     * @throws InterruptedException
     */
    public void logInAction(String sUserName, String sPassword) throws InterruptedException{
        System.out.println("Logging ............................");
        idUserNameInput.clear();
        idUserNameInput.sendKeys(sUserName);
        idPasswordInput.clear();
        idPasswordInput.sendKeys(sPassword);
        Thread.sleep(1000);
        cssLoginBtn.click();
        assert nickname.getText().contains("您好，");
        assert nickname.getText().contains("ccui");
    }


}
