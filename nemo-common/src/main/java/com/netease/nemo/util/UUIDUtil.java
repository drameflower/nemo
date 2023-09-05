package com.netease.nemo.util;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class UUIDUtil {

    public static String getUUID() {
        String uuidStr = UUID.randomUUID().toString();
        return uuidStr.replace("-", "");
    }

    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    public static long createRndInteger(int n) {
        long max = (long) Math.pow(2, n) - 1;
        long min = (long) Math.pow(2, n - 1);
        return (long) (min + Math.random() * (max - min + 1));
    }

    public static long generateRandomNumber(int n) {
        long min = (long) Math.pow(10, n - 1);
        return ThreadLocalRandom.current().nextLong(min, min * 10);
    }

    public static String getRandomString(int length) {
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String randomLowerLetterAndNumber(int length) {
        String str = "abcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomNumberString(int length) {
        String str = "abcdef0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static String getRandomNumber(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
