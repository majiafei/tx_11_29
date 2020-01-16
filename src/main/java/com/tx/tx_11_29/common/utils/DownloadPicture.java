package com.tx.tx_11_29.common.utils;

import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;

/**
 * @ClassName: DownloadPicture
 * @Auther: admin
 * @Date: 2020/1/16 11:40
 * @Description:
 */
public class DownloadPicture {

    public static void main(String[] args) {
/*        URL url = null;
        try {
            url = new URL("https://ww2.sinaimg.cn/orj360/6defa4caly1gaxfecouszj20u0160dmk.jpg");
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:1.jpg"));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            fileOutputStream.write(output.toByteArray());
            dataInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

// 二:
        URL url = null;
        try {
            url = new URL("https://ww2.sinaimg.cn/orj360/6defa4caly1gaxfecouszj20u0160dmk.jpg");
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(new File("D:1.jpg"));

            FileCopyUtils.copy(dataInputStream, fileOutputStream);

            dataInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
