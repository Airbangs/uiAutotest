package org.wanbi.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

/**
 * @Author: Alina
 * @Date: 2021/5/19 15:38
 */
public class InitParams {

    /**
     * 生成1〜v-1之间的随机数
     * @param v
     * @return
     */
    public static int randomValue(int v) {
        Random random = new Random();
        int n = 0;
        for (int i=1;i<v;i++) {
            n = random.nextInt(v-1) + 1;
        }
        return n;
    }

    /**
     * 判断某个元素是否存在
     * @param driver
     * @param by
     * @return
     */
    public static boolean isJudgeEle(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            System.out.println("想要查找的元素不存在。");
            return false;
        }
    }
}
