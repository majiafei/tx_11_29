package com.tx.tx_11_29.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;

/**
 * @ClassName: test
 * @Auther: admin
 * @Date: 2020/6/17 09:51
 * @Description:
 */
public class test {
/*    public static void main(String[] args) {
        int y,m,d,h,mi,s;
        Calendar cal = Calendar.getInstance();
        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DATE);

        String today = y + "-" + m + "-" + d;
        System.out.println(today);
    }*/

    public static String graphicsGeneration(List<List<String>> allValue, String titles, String[] headers ) throws Exception {
        int rows = 0;
        int maxfont = 0;
        if (allValue != null && allValue.size() > 0) {
            rows += (2+allValue.size());
        }
        for (List<String> strings : allValue) {
            maxfont = strings.get(0).length()>maxfont?strings.get(0).length():maxfont;
        }
        // 实际数据行数+标题+备注
        int numwidth = 50;
        int totalrow = 1+rows;
        int namewidth = maxfont * 22;
        int otherwidth = 80;

        int imageWidth = numwidth + namewidth  + otherwidth*(headers.length-2) + 20;
        int imageHeight = totalrow * 30 + 20;
        int rowheight = 30;
        int startHeight = 10;
        int startWidth = 10;

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        //画表头背景
        graphics.setColor(new Color(150,0,0));
        if (allValue != null && allValue.size() > 0) {
            graphics.fillRect(startWidth + 1, startHeight +  rowheight + 1, imageWidth - startWidth - 6, rowheight - 1);
        }
        //画前三名背景
        int redstartH = 2;
        graphics.setColor(new Color(190,25,0));
        for (int temp = 0; temp < allValue.size(); temp++) {
            List strings = allValue.get(temp);
            if (strings != null) {
                graphics.fillRect(startWidth + 1,startHeight + redstartH*rowheight +1 , imageWidth - startWidth - 6,rowheight - 1);
            }
            redstartH++;
            if(temp==2)break;
        }
        // 画横线
        for (int j = 0; j < totalrow - 1; j++) {
            graphics.setColor(Color.gray);
            graphics.drawLine(startWidth, startHeight + (j + 1) * rowheight, imageWidth - 5,
                    startHeight + (j + 1) * rowheight);
        }
        int rightLine;
        // 画竖线
        graphics.setColor(Color.gray);
        if (allValue != null && allValue.size() > 0) {
            for (int k = 0; k < headers.length+1; k++) {
                rightLine = getRightMargin(k,startWidth, namewidth,otherwidth,imageWidth);
                graphics.drawLine(rightLine, startHeight + rowheight, rightLine,
                        startHeight + (allValue.size()+2)*rowheight);
            }
        }
        // 设置字体，准备写入文字
        Font font = new Font("宋体", Font.BOLD, 20);
        graphics.setFont(font);
        graphics.setColor(Color.black);
        // 写标题
        if (allValue != null && allValue.size() > 0) {
            graphics.drawString(titles, imageWidth / 3 + startWidth+30, startHeight + rowheight - 10);
        }
        // 写入表头
        int startH = 2;
        graphics.setColor(Color.WHITE);
        font = new Font("宋体", Font.BOLD, 20);
        graphics.setFont(font);
        if (allValue != null && allValue.size() > 0) {
            for (int m = 0; m < headers.length; m++) {
                int strWidth = graphics.getFontMetrics().stringWidth(headers[m]);
                rightLine = getRightMargin(m,startWidth, namewidth,otherwidth,imageWidth);
                if(m==0)
                    rightLine = rightLine + (numwidth-strWidth)/2;
                else if(m==1)
                    rightLine = rightLine + (namewidth-strWidth)/2;
                else
                    rightLine = rightLine + (otherwidth-strWidth)/2;
                graphics.drawString(headers[m], rightLine,
                        startHeight + startH*rowheight  - 10);
            }
        }
        // 写入内容
        startH = 3;
        graphics.setColor(Color.white);
        graphics.setFont(new Font("宋体", Font.BOLD, 20));
        if (allValue != null && allValue.size() > 0) {
            for (int n = 0; n < allValue.size(); n++) {
                if (n == 3) {
                    graphics.setColor(Color.black);
                    graphics.setFont(new Font("宋体", Font.PLAIN, 20));
                }
                List<String> arr = allValue.get(n);
                for (int l = 0; l < arr.size() + 1; l++) {
                    rightLine = getRightMargin(l, startWidth, namewidth, otherwidth, imageWidth) + 5;
                    if (l == 0) {
                        int strWidth = graphics.getFontMetrics().stringWidth(String.valueOf(n + 1));
                        graphics.drawString(String.valueOf(n + 1), rightLine + (numwidth - strWidth) / 2 - 5,
                                startHeight + rowheight * (n + startH) - 10);
                    } else {
                        int strWidth = graphics.getFontMetrics().stringWidth(arr.get(l - 1));
                        if (l == 1)
                            graphics.drawString(arr.get(l - 1), rightLine,
                                    startHeight + rowheight * (n + startH) - 10);
                        else
                            graphics.drawString(arr.get(l - 1), rightLine + (otherwidth - strWidth) / 2 - 5,
                                    startHeight + rowheight * (n + startH) - 10);
                    }
                }
            }
        }

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Stroke s = new BasicStroke(imageWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        graphics.setStroke(s);

        graphics.drawImage(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH), 0, 0, null);
        String path = "D://1.jpg";
        ImageIO.write(image, "jpg", new File(path));
        return path;
    }

    /**
     * 获取竖线和文字的水平位置
     * @param k
     * @param startWidth
     * @param namewidth
     * @param otherwidth
     * @param imageWidth
     * @return
     */
    private static int getRightMargin(int k, int startWidth, int namewidth,int otherwidth, int imageWidth) {
        int rightLine = 0;
        if (k == 0) {
            rightLine = startWidth;
        } else if (k == 1) {
            rightLine = startWidth + 50;
        } else if (k == 2) {
            rightLine = startWidth + 50 + namewidth;
        } else if (k >= 3 &&k<9) {
            rightLine = startWidth + +50 + namewidth + (k - 2) * otherwidth;
        } else if (k == 9)
            rightLine = imageWidth - 5;
        return rightLine;
    }
    public static void initChartData(){
        List<List<String>> contentArray1 = new ArrayList<>();

        for(int i = 0;i<10;i++){
            contentArray1.add(Arrays.asList(new String[]{"谭宇","300","5.00","1.00","1234","1234","1234","100"}));
        }

        String[] headTitles = new String[]{"序号","名字","成绩","击键","码长","退格","回改","选重","错字"};

        String titles = "跟打成绩";
        try {
            graphicsGeneration(contentArray1,titles,headTitles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        initChartData();
    }
}
