package com.hs.utils;

import com.hs.util.commarkUtil.MarkDownToHTMLUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Hs
 * @Date 2022/6/14 9:08
 */
@SpringBootTest
public class MarkDownToHTMLUtilTest {
    @Test
    void test(){
        System.out.println(MarkDownToHTMLUtil.markdownToHtml("\n" +
                "\n" +
                "**前提条件：**\n" +
                "\n" +
                "1.提前把第二天要做的事情写好，确定时间、地点、具体内容\n" +
                "\n" +
                "2.把手机放在摸不到的地方\n" +
                "\n" +
                "**起床流程：**\n" +
                "\n" +
                "1.刚睁开眼，默数3秒钟，然后迅速起床，铺床\n" +
                "\n" +
                "这样不会一起床就玩手机，并且铺好床也避免了回来睡觉\n" +
                "\n" +
                "2.锻炼10分钟 微微出汗\n" +
                "\n" +
                "- 臀桥  \n" +
                "- 全身脊椎伸展(双手合十举过头顶)\n" +
                "\n" +
                "3.刷牙、洗澡(先洗热水澡，然后冷水)\n" +
                "\n" +
                "4.喝杯水\n" +
                "\n" +
                "5.换上前一天准备好的衣服，到外面感受日光，规划一天的日程\n" +
                "\n" +
                "6.冥想3-10分钟\n" +
                "\n" +
                "然后做一天最困难的事情，日复一日，变成潜意识\n" +
                "\n" +
                "\n" +
                "\n" +
                "everyday：去院子里站/坐一会\n" +
                "\n"));
    }
}
