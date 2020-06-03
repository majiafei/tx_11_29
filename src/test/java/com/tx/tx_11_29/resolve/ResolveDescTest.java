package com.tx.tx_11_29.resolve;

import com.google.common.collect.Lists;
import org.junit.platform.commons.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName: ResolveDescTest
 * @Auther: admin
 * @Date: 2020/6/3 09:25
 * @Description:
 */
public class ResolveDescTest {
    public static void main(String[] args) {
        String desc = "dd> <p> <strong>Description:</strong></p> <br/> <ul> <li> Keep pool table clean not only improves its appearance, also extends felt lifespan</li> <li> To keep dust and dirt off your billiard pool table and rails</li> <li> Made of qualified PVC bristles and wooden handle</li> <li> The bristles won't fall out during use</li> <li> Soft PVC bristles won't scratch the billiard's surface and also can be used to clean other home staff</li> <li> There is a hole in handle for hanging/hook storage</li> </ul> <br/> <br/> <p> <strong>Specifications:</strong></p> <br/> <ul> <li> Material: PVC + Wooden</li> <li> Billiard Rail Brush: 9inch</li> <li> Billiard Corner Brush: 8.5inch</li> </ul> <br/> <br/> <p> <strong>Package Included:</strong></p> <br/> <ul> <li> 1 * Billiard Rail Brush</li> <li> 1 * Billiard Corner Brush</li> </ul> <br/> <br/> <p> <strong>Note:</strong></p> <br/> <ul> <li> 1.Due to the manual measurement and different measurement methods, the actual size could be slightly different.</li> <li> 2.Please be reminded that due to lighting effects and monitor's brightness/contrast settings etc, the color tone of the website's photo and the actual item could be slightly different.</li> </ul><div><img src=\"https://my-live-01.slatic.net/p/6d58cfa66927678b9d266312dc1fc6a6.jpg\"/><img src=\"https://my-live-01.slatic.net/p/8249b46eb225a1ea7238d5a3d1e452b1.jpg\"/><img src=\"https://my-live-01.slatic.net/p/77364a3d2fc672b40f67467acbcd1d79.jpg\"/><img src=\"https://my-live-01.slatic.net/p/90adb6bebb7ccb65f844a326b8aca741.jpg\"/><img src=\"https://my-live-01.slatic.net/p/e7b080f962f0d1a763fe7e32d42dd716.jpg\"/><img src=\"https://my-live-01.slatic.net/p/7ba89c49ff4fade82356b11721498324.jpg\"/><img src=\"https://my-live-01.slatic.net/p/2deb4e23a3ebc3ca182ea86d088b9fd6.jpg\"/><img src=\"https://my-live-01.slatic.net/p/14995c7433a188416709eb9707b9fd5c.jpg\"/><img src=\"https://my-live-01.slatic.net/p/ba7f0366c6eedbaca28a8c67289c8abc.jpg\"/><img src=\"https://my-live-01.slatic.net/p/6f5a3b2a906eb01272b37ef040afeb80.jpg\"/></div>";
        List<String> html = Lists.newArrayList();
        List<String> text = Lists.newArrayList();
        StringBuilder htmlStr = new StringBuilder();
        StringBuilder textStr = new StringBuilder();
        boolean isHtml = true;
        boolean isEnd = true;
        boolean isStrEnd = false;
        for (int i = 0; i < desc.toCharArray().length; i++) {
            char c = desc.charAt(i);
            if (Objects.equals(String.valueOf(c), "<") || (isHtml && !Objects.equals(String.valueOf(c), ">"))) {
                isHtml = true;
                htmlStr.append(c);
                isEnd = false;
                isStrEnd = true;
            } else if (Objects.equals(String.valueOf(c), ">")) {
                isHtml = false;
                htmlStr.append(c);
                isEnd = true;
                isStrEnd = false;
            } else if (!isHtml) {
                isEnd = false;
                textStr.append(c);
                isStrEnd = false;
            }

             if (isEnd) {
                 html.add(htmlStr.toString());
                 htmlStr.delete(0, htmlStr.toString().length());
             }
             if (isStrEnd) {
                 if (!Objects.equals(textStr.toString(), "")) {
                     html.add("?");
                     text.add(textStr.toString());
                     textStr.delete(0, textStr.toString().length());
                 }
             }
        }
        System.out.println(text);
        int i = 0;
        for (String str : html) {
            if (str.equals("?")) {
                i++;
            }
        }
        System.out.println(i);

        StringBuilder sb = new StringBuilder();
        int m = 0;
        for (int j = 0; j < html.size(); j++) {
            if (Objects.equals(html.get(j), "?")) {
                if (m < text.size()) {
                    sb.append(text.get(m));
                    m++;
                }
            } else {
                sb.append(html.get(j));
            }

        }

        System.out.println(sb.equals(desc));
        System.out.println(sb.length());
        System.out.println(desc.length());
    }
}
