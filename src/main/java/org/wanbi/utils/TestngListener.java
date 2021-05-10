package org.wanbi.utils;

import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.testng.*;

/**
 * @Author: Alina
 * @Date: 2021/5/8 18:24
 */
public class TestngListener extends TestListenerAdapter {
    public static WebDriver driver;
    Properties prop = new Properties();
    InputStream input = null;

    @Override
    public void onTestFailure(ITestResult tr) {

        super.onTestFailure(tr);
        try {
            takeScreenShot(tr);
            showScreenShot(tr);
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    /**
     * 显示失败截图
     *
     * @param result
     */
    public void showScreenShot(ITestResult result) {
        try {
            // 打印当前目录
//            System.out.println("当前目录是：" + new File(".").getAbsolutePath());
            input = new FileInputStream("./classes/prop.txt");
            // 加载properties文件
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePath = "";
        if (prop.getProperty("env").equals("26")) {
            filePath = prop.getProperty("failSnapshot");
        } else if (prop.getProperty("env").equals("test")) {
            // 工作目录对应项目的截图路径
            File file = new File("snapshot");
            filePath = file.getAbsolutePath();
        }
//        Reporter.log("截图路径为："+filePath);
        // 输出失败用例截图
        Reporter.log("<img src='" + filePath + "/" + result.getName() + ".png' hight='1920' width='1080'/>");
        // 显示大图
        Reporter.log("<a href='" + filePath + "/" + result.getName() + ".png'>显示大图</a>");
    }

    /**
     * 获取用例失败截图
     *
     * @param tr
     * @throws IOException
     */
    public void takeScreenShot(ITestResult tr) throws IOException {
        SimpleDateFormat smf = new SimpleDateFormat("MMddHHmmss");
        String curTime = smf.format(new Date());
//		String fileName = tr.getName()+"_"+curTime+".png";
        String fileName = tr.getName() + ".png";
        // 调用getScreenshotAs（）实现截图
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // 将截图保存到指定目录
        System.out.println("复制 " + srcFile + " 文件到指定目录");
//        FileUtils.copyFile(srcFile, new File("snapshot/" + fileName));
    }
}
