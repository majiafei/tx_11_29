package com.tx.tx_11_29.common.utils;

import com.google.common.collect.Lists;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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

    public static String graphicsGeneration(List<List<String>> allValue, String titles, String[] headers) throws Exception {
        int rows = 0;
        int maxfont = 0;
        if (allValue != null && allValue.size() > 0) {
            rows += (2 + allValue.size());
        }
        for (List<String> strings : allValue) {
            maxfont = strings.get(0).length() > maxfont ? strings.get(0).length() : maxfont;
        }
        // 实际数据行数+标题+备注
        int numwidth = 50;
        int totalrow = 1 + rows;
        int namewidth = maxfont * 22;
        int otherwidth = 80;

        int imageWidth = numwidth + namewidth + otherwidth * (headers.length - 2) + 20;
        int imageHeight = totalrow * 30 + 20;
        int rowheight = 30;
        int startHeight = 10;
        int startWidth = 10;

        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        //画表头背景
        graphics.setColor(new Color(150, 0, 0));
        if (allValue != null && allValue.size() > 0) {
            graphics.fillRect(startWidth + 1, startHeight + rowheight + 1, imageWidth - startWidth - 6, rowheight - 1);
        }
        //画前三名背景
        int redstartH = 2;
        graphics.setColor(new Color(190, 25, 0));
        for (int temp = 0; temp < allValue.size(); temp++) {
            List strings = allValue.get(temp);
            if (strings != null) {
                graphics.fillRect(startWidth + 1, startHeight + redstartH * rowheight + 1, imageWidth - startWidth - 6, rowheight - 1);
            }
            redstartH++;
            if (temp == 2) break;
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
            for (int k = 0; k < headers.length + 1; k++) {
                rightLine = getRightMargin(k, startWidth, namewidth, otherwidth, imageWidth);
                graphics.drawLine(rightLine, startHeight + rowheight, rightLine,
                        startHeight + (allValue.size() + 2) * rowheight);
            }
        }
        // 设置字体，准备写入文字
        Font font = new Font("宋体", Font.BOLD, 20);
        graphics.setFont(font);
        graphics.setColor(Color.black);
        // 写标题
        if (allValue != null && allValue.size() > 0) {
            graphics.drawString(titles, imageWidth / 3 + startWidth + 30, startHeight + rowheight - 10);
        }
        // 写入表头
        int startH = 2;
        graphics.setColor(Color.WHITE);
        font = new Font("宋体", Font.BOLD, 20);
        graphics.setFont(font);
        if (allValue != null && allValue.size() > 0) {
            for (int m = 0; m < headers.length; m++) {
                int strWidth = graphics.getFontMetrics().stringWidth(headers[m]);
                rightLine = getRightMargin(m, startWidth, namewidth, otherwidth, imageWidth);
                if (m == 0)
                    rightLine = rightLine + (numwidth - strWidth) / 2;
                else if (m == 1)
                    rightLine = rightLine + (namewidth - strWidth) / 2;
                else
                    rightLine = rightLine + (otherwidth - strWidth) / 2;
                graphics.drawString(headers[m], rightLine,
                        startHeight + startH * rowheight - 10);
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
     *
     * @param k
     * @param startWidth
     * @param namewidth
     * @param otherwidth
     * @param imageWidth
     * @return
     */
    private static int getRightMargin(int k, int startWidth, int namewidth, int otherwidth, int imageWidth) {
        int rightLine = 0;
        if (k == 0) {
            rightLine = startWidth;
        } else if (k == 1) {
            rightLine = startWidth + 50;
        } else if (k == 2) {
            rightLine = startWidth + 50 + namewidth;
        } else if (k >= 3 && k < 9) {
            rightLine = startWidth + +50 + namewidth + (k - 2) * otherwidth;
        } else if (k == 9)
            rightLine = imageWidth - 5;
        return rightLine;
    }

    public static void initChartData() {
        List<List<String>> contentArray1 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            contentArray1.add(Arrays.asList(new String[]{"谭宇", "300", "5.00", "1.00", "1234", "1234", "1234", "100"}));
        }

        String[] headTitles = new String[]{"序号", "名字", "成绩", "击键", "码长", "退格", "回改", "选重", "错字"};

        String titles = "跟打成绩";
        try {
            graphicsGeneration(contentArray1, titles, headTitles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

    }

    /**
     * 可以换行
     */
    public static void draw2() {
        BufferedImage bufferImage = null;
        FileOutputStream output = null;

        String info = "客户姓名:唐伯虎;身份证号:12345678906666666;某某合同编号:张店哈哈哈哈高抵字2015年第02-30在参数StringFormat标志位 !";
        int w = 500;//画布宽度
        int h = 300;//画布高度
        try {
            bufferImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferImage.createGraphics();

            Font font = new Font("微软雅黑", Font.PLAIN, 30);
            FontRenderContext frc = g.getFontRenderContext();
            g.getFontRenderContext();
            Rectangle2D stringBounds = font.getStringBounds(info, frc);
            double fontWidth = stringBounds.getWidth();
            System.out.println("不换行时文本宽度 : " + fontWidth);

            List<String> line_list = line(fontWidth, w, info, font, frc);

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, w, h);//填充整个屏幕
            g.setFont(font);
            g.setColor(Color.RED);

            for (int i = 0; i < line_list.size(); i++) {
                String line_str = line_list.get(i);
                g.drawString(line_str, 0, (i + 2) * 35);//35为每行的高度
            }

            g.drawString("Graphics2D.drawString换行示例", 0, 35);
            g.dispose();

            output = new FileOutputStream("D:/1.jpg");//输入文件到E盘根目录
            System.out.println("文件 D:/1.jpg 已经生成");
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(output);
            en.encode(bufferImage);

            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("end/:-)");
        }
    }

    private static List<String> line(double fontWidth, int w, String info, Font font, FontRenderContext frc) {
        List<String> line_list = Lists.newArrayList();
        if (fontWidth <= w) {
            line_list.add(info);
        } else {
            int text_width = w;//输出文本宽度,这里就以画布宽度作为文本宽度测试
            double bs = fontWidth / text_width;//文本长度是文本框长度的倍数
            int line_char_count = (int) Math.ceil(info.length() / bs);//每行大概字数
            int begin_index = 0;
            while (begin_index < info.length()) {
                int end_index = begin_index + line_char_count;
                if (end_index >= info.length()) {
                    end_index = info.length();
                }
                String line_str = info.substring(begin_index, end_index);
                Rectangle2D tempStringBounds = font.getStringBounds(line_str, frc);
                int tzcs = 0;//调整次数
                int tzzs = 1;//调整字数,临时文本的字符串长度大于要求的文本宽度时,每次减少临时文本的字数,然后重新测量文本宽度
                while (tempStringBounds.getWidth() > text_width) {
                    line_str = line_str.substring(0, line_str.length() - tzzs);//每次向前 tzzs 个字符重新计算(待优化)
                    tempStringBounds = font.getStringBounds(line_str, frc);
                    tzcs++;
                }
                System.out.println("调整" + (end_index - begin_index - line_str.length()) + "个数字,调整了" + tzcs + "次,当前行宽度:" + tempStringBounds.getWidth());
                line_list.add(line_str);
                begin_index = begin_index + line_str.length();
            }
        }

        return line_list;
    }


    public static void draw(int imageWidth, int imageHeight) throws IOException {
        BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, imageWidth, imageHeight);

        graphics.setColor(Color.BLACK);
        int fontSize = 30;
        Font font = new Font("宋体", Font.BOLD, fontSize);
        graphics.setFont(font);

        // 买家
        String text = "Todsf455555555555555555555555555555555555555555555555555555555555555555555555555";
        List<String> list = dealRows(text, fontSize);
        for (int i = 0; i < list.size(); i++) {
            graphics.drawString(list.get(i), 1100, 400 + i * fontSize);
        }
        graphics.drawImage(image.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH), 0, 0, null);
        String path = "1.jpg";
        ImageIO.write(image, "jpg", new File(path));
    }

    public static List<String> dealRows(String text, int font) {
//		String target = "[0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ -[]()<>&=:@{}~!#$%^/.]";
        text = text.trim();
        StringBuffer sb = new StringBuffer();
        List<String> list = new ArrayList<String>();
        int tmp = 0;
        int row = 0;
        int fontSize = font * 2;
        int fontStrSize = 0;
        for (char c : text.toCharArray()) {
            tmp = /*isChinese(c) ? 2 :*/ 1;
            fontStrSize = fontStrSize + tmp;
            sb.append(c);
            if (fontSize <= fontStrSize) {
                fontStrSize = 0;
                list.add(sb.toString());
                sb = new StringBuffer();
                row++;
            }
        }
        if (row == 0 || fontStrSize < fontSize) {
            list.add(sb.toString());
        }
        return list;
    }
}
