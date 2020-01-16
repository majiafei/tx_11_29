package com.tx.tx_11_29.examples;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.xwpf.usermodel.*;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @ClassName: Examples
 * @Auther: admin
 * @Date: 2020/1/16 10:44
 * @Description:
 */
public class Examples {

    public static void main(String[] args) {
        XWPFDocument doc = null;
        try {
            doc = new XWPFDocument(POIXMLDocument.openPackage("E:/优考试-试题直接导入.docx"));
            System.out.println(doc.getTables());
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            System.out.println(paragraphs.size());
            String rule = "^[\\d]{1,3}$";//正则表达式，^表示起始，$表示结束
            Pattern pTitle = Pattern.compile(rule);

            List<XWPFPictureData> allPictures = doc.getAllPictures();

            //首先确定第一行为试卷标题。title
            for (int i = 0; i < paragraphs.size();i++) {
                String text = paragraphs.get(i).getParagraphText().trim();//选中行的内容   去空格
                IBody body = paragraphs.get(i).getBody();
                List<XWPFRun> runs = paragraphs.get(i).getRuns();
                for (XWPFRun xwpfRun : runs) {
//                    xwpfRun.get
                    List<XWPFPicture> embeddedPictures = xwpfRun.getEmbeddedPictures();
                    for (XWPFPicture picture : embeddedPictures) {
                        byte[] data = picture.getPictureData().getData();
                    }
                }
                System.out.println(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
