package com.utils;

/**
 * Created by dqf on 2015/8/17.
 */
import java.security.SecureRandom;
import java.util.UUID;

public class Identities {
    private static SecureRandom random = new SecureRandom();

    public Identities() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static String uuid2() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    public static String randomLongAsString() {
        return String.valueOf(Math.abs(random.nextLong()));
    }

    public static String randomBase62(int length) {
        byte[] randomBytes = new byte[length];
        random.nextBytes(randomBytes);
        return Encodes.encodeBase62(randomBytes);
    }

    public static long randomLong(int length) {
        String random = String.valueOf(randomLong()).substring(0, length);
        System.out.println(random + " " + random.length());
        return Long.valueOf(random).longValue();
    }
}
