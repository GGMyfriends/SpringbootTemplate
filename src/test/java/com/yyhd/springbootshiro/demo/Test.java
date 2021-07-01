package com.yyhd.springbootshiro.demo;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Test {

    public static void main(String[] args) {
        String password = new SimpleHash("SHA-1", "123456", "17777777777", 16).toHex();
        System.out.println(password);
    }

}
