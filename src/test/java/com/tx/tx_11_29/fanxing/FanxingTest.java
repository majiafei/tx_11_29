package com.tx.tx_11_29.fanxing;

import com.google.common.collect.Lists;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class FanxingTest {

    public static void main(String[] args) {
        //List<String> list = Lists.newArrayList("xxxxx");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 31);

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        // 2020-12-31
        System.out.println(sdf.format(calendar.getTime()));

        // 2019-12-31
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf1.format(calendar.getTime()));

    }

}
