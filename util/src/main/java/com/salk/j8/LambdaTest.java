package com.salk.j8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by salk on 2017/5/14.
 */
public class LambdaTest {

    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();
        names.add("c");
        names.add("b");
        names.add("a");
        Collections.sort(names, (o1, o2) -> o1.compareTo(o2));
        System.out.println(names);

        List<String> names2 = new ArrayList<String>();
        names.add("C");
        names.add("B");
        names.add("A");
        List<String> lowercaseNames = names2.stream().map((String name) -> {
            return name.toLowerCase();
        }).collect(Collectors.toList());

    }
}
