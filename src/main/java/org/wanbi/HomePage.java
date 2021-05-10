package org.wanbi;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @Author: Alina
 * @Date: 2021/5/8 16:15
 */
public class HomePage {
    public static WebDriver driver;

//    public HomePage() {
//        driver = new ChromeDriver();
//        PageFactory.initElements(driver,this);
//    }

    /**
     * 示例
      */
    @FindBy(how = How.CSS, using = "")
    public WebElement demo;

    @FindBy(css = "span.action___LP4_P>span.name___WfKAK")
    public WebElement lnkLogout;
    @FindBy(how = How.CSS, using = "div.title___3ww2k")
    public WebElement cssWelcome;

    /**
     * 登出
     */
    public void logoutAction() {
        // 显示等待: 退出元素在页面上可用和可被点击
//        System.out.printf("判断元素是否隐藏" + lnkLogout.isDisplayed());
//        WebDriverWait wait = new WebDriverWait(driver,10);
//        wait.until(ExpectedConditions.elementToBeClickable(By.className("span.action___LP4_P")));
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("span.action___LP4_P")));
//        lnkLogout.click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", lnkLogout);
        assert cssWelcome.getText().equals("欢迎登录");
    }

}
