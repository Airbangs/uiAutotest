import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.wanbi.HomePage;
import org.wanbi.LoginPage;
import org.wanbi.utils.TestngListener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Alina
 * @Date: 2021/5/8 18:10
 */
public class BaseTestcase extends HomePage {
    private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
//    public static WebDriver driver;
    protected static LoginPage loginPage;

    @BeforeTest(groups = {"checklist","testing"})
    public void initBegin() {
        Properties prop = new Properties();
        //设置报告支持显示截图：关闭escape-output
        System.setProperty(ESCAPE_PROPERTY, "false");

        /**
         * 方法一：固定，设定Chrome启动文件的位置，若未设置取默认安装目录的chrome
         * System.setProperty("webdriver.chrome.driver", initParams.chromedriver);
         * 方法二：不固定，按配置文件来
         * 这里采用方法二
         */
        try {
            // 打印当前目录
//            System.out.println("当前目录是：" + new File(".").getAbsolutePath());

            // 设置目录
            InputStream input = new FileInputStream("src/main/resources/test.properties");
            // 加载properties文件
//            prop.load(input); //直接这么写，如果properties文件中有汉子，则汉字会乱码。因为未设置编码格式。
            prop.load(new InputStreamReader(input,"utf-8"));

//            System.out.println("prop.getProperty(\"chromedriver\")=> "+prop.getProperty("chromedriver"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriver"));
        ChromeOptions options = new ChromeOptions();

        if (prop.getProperty("env").equals("26")){
            System.out.println("linux运行不支持可视化！！！");
            options.setExperimentalOption("useAutomationExtension", false);
            options.addArguments("--headless"); // 浏览器不提供可视化页面. linux下如果系统不支持可视化不加这条会启动失败
            options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        }else if (prop.getProperty("env").equals("test")){
            System.out.println("本地运行支持可视化！！！");
        }

        options.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
        // 把driver赋值给TestngListener，以获取截图
        TestngListener.driver=driver;

        // 获取系统url
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(prop.getProperty("baseUrl"));
        driver.manage().window().maximize();

        loginPage = PageFactory.initElements(driver, LoginPage.class);

        // 初始
        try {
            loginPage.logInAction(prop.getProperty("userName"),prop.getProperty("passWord"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Login Successfully!!!");
    }

    @AfterTest
    public void quit() {
        driver.quit();
    }
}
