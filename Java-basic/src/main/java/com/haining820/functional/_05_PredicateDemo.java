package com.haining820.functional;

import java.util.function.Predicate;

/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-10-04
 * Time: 16:42
 */
public class _05_PredicateDemo {

    public static void main(String[] args) {
        Predicate<String> p = str -> str.isEmpty();
        System.out.println(p.test(""));     // true
        System.out.println(p.test("a"));    // false
    }

}
