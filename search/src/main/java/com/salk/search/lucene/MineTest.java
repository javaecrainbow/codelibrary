package com.salk.search.lucene;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by salk on 2016/10/10.
 */
public class MineTest {
    public static void main(String[] args) {
        double d=0.001d;
        System.out.println(d);
        DecimalFormat df = new DecimalFormat("0");//格式化设置
        System.out.println(df.format(d));
        NumberFormat instance = NumberFormat.getInstance();
        instance.setGroupingUsed(false);
        instance.setMaximumFractionDigits(4);
        System.out.println(instance.format(d));
    }
}
