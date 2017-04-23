package com.salk.search.lucene;

import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by salk on 2016/10/23.
 */
public class TypeMergeTest {
    public static void main(String[] args) {
        int type0=0;
        int type1=1;
        int type2=2;
        long time1=System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            //testRun(type0,type1,type2);
            //testObj();
        }
        long time2=System.currentTimeMillis();
        System.out.println((time2-time1));

        int[] cases=new int[]{1<<type0,1<<type1,1<<type2,(1<<type0)+(1<<type1),(1<<type0)+(1<<type2),(1<<type1)+(1<<type2),(1<<type0)+(1<<type1)+(1<<type2)};
        for(int i:cases){
            String s = Integer.toBinaryString(i);
            s = StringUtils.reverse(s);
            System.out.println(s);
            for(int j=0;j<s.length();j++){
                char c=s.charAt(j);
                if(c=='1'){
                    System.out.println(j);
                }
            }
            System.out.println("==========");

        }


    }

    public static void testObj(){
        Type type=new Type();
        type.setType1(1);
        type.setType2(2);
        type.setType3(3);

    }

    public static Set<Integer> testRun(int type0,int type1,int type2){
        int result= (1<<type0)+(1<<type1)+(1<<type2);
        Set<Integer> sets=new HashSet<>();
        String s = Integer.toBinaryString(result);
        s = StringUtils.reverse(s);
        for(int j=0;j<s.length();j++){
            char c=s.charAt(j);
            if(c=='1'){
                sets.add(j);
            }
        }
        return sets;
    }
}
class Type{
    private int type1;
    private int type2;
    private int type3;

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public int getType3() {
        return type3;
    }

    public void setType3(int type3) {
        this.type3 = type3;
    }
}
