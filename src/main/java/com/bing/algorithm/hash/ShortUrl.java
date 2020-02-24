package com.bing.algorithm.hash;

import com.google.common.hash.Hashing;

import java.math.BigInteger;

public class ShortUrl {

    private static final String ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int BASE = ALPHABETS.length();

    public String shortenUrl(String url) {
        long murmur32 = Hashing.murmur3_32().hashUnencodedChars(url).padToLong();
        String encoded, value;
        encoded = encode(murmur32++);
        return encoded;
    }

    private String encode(long oct) {
        BigInteger octLong = BigInteger.valueOf(oct);
        StringBuilder builder = new StringBuilder(6);
        while (!octLong.equals(BigInteger.ZERO)) {
            BigInteger[] divideAndReminder = octLong.divideAndRemainder(BigInteger.valueOf(BASE));
            builder.append(ALPHABETS.charAt(divideAndReminder[1].intValue()));
            octLong = divideAndReminder[0];
        }
        return builder.reverse().toString();
    }
}