package com.salk.j8;

/**
 * Created by salk on 2017/5/15.
 */

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    @Test
    public void test1() {
        Stream<String> stringStream = Stream.of("1", "2", "3");
        long count = stringStream.count();
        System.out.println("count==" + count);
        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        }).forEach(System.out::print);
        Stream.generate(() -> Math.random());
        Stream.generate(Math::random);


    }

    @Test
    public void test2() {
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
    }

    /**
     * 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数；
     * limit: 对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素；
     *skip: 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream；
     *
     */
    @Test
    public void test3() {
        Stream<Integer> integerStream = Stream.of(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is" + integerStream.filter(num -> num != null)
                .distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).sum());

    }

    @Test
    public void test4(){
        Stream<Integer> integerStream = Stream.of(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        List<Integer> numsWithoutNull = integerStream.filter(num -> num != null).
                collect(ArrayList::new,
                        List::add,
                List::addAll
                );
        System.out.println(numsWithoutNull);
        List<Integer> numsWithoutNull2 = integerStream.filter(num -> num != null).
                collect(Collectors.toList());

    }
    @Test
    public void testReduce(){
        Stream<Integer> integerStream1 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("ints sum is:" + integerStream1.reduce(0, (sum, item) -> sum + item));
        Stream<Integer> integerStream2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("ints count is:" + integerStream2.count());
    }
}
