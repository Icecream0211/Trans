package com.bing.stringhash;

public class StringHashAlgorithms {
    /**
     * java自带哈希算法
     * 字符串每一位字符的 哈希值*31+字符i的ASCII码
     *
     * @param str
     * @return
     */
    public static int javaSelf(String str) {
        int h = 0;
        int off = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            h = 31 * h + str.charAt(off++);
        }
        return h;
    }

    /**
     * 加法哈希
     * @param key
     * @param prime  质数
     * @return
     */

    public static int addHash(String key, int prime) {

        int hash, i;
        for (hash = key.length(), i = 0; i < key.length(); i++)
            hash += key.charAt(i);
        return (hash % prime);
    }
}
