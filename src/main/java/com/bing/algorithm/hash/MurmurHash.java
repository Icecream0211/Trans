package com.bing.algorithm.hash;

import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * murmur哈希算法
 * MurmurHash算法：高运算性能，低碰撞率，由Austin Appleby创建于2008年，
 * 现已应用到Hadoop、libstdc++、nginx、libmemcached等开源系统。2011年Appleby被Google雇佣，
 * 随后Google推出其变种的CityHash算法。官方只提供了C语言的实现版本。
 * <p>
 * Java界中Redis，Memcached，Cassandra，HBase，Lucene都用它。
 * <p>
 * 在Java的实现，Guava的Hashing类里有，上面提到的Jedis，Cassandra里都有Util类。
 * <p>
 * 但存在的问题是由于Java的数据类型long与C语言中无符号长整型uint64_t有区别，
 * 导致Java输出版本存在负数，针对这个问题进行了修改；另外需要注意的是中文不同编码（UTF-8或GBK）会导致输出结果的不同，
 * 使用中需要统一编码。
 * <p>
 * <p>
 * 一致性Hash的一种算法 高效低碰撞率
 *
 * 当前的版本是MurmurHash3，[8][9] 能够产生出32-bit或128-bit哈希值。
 *
 * @Author Bing
 * @Date 2020/2/24 09:58
 **/
public class MurmurHash {
    /**
     * murmur hash算法实现
     */
    public static Long hash(byte[] key) {

        ByteBuffer buf = ByteBuffer.wrap(key);
        int seed = 0x1234ABCD;

        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();

            k *= m;
            k ^= k >>> r;
            k *= m;

            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }

    public static Long hash(String key) {
        return hash(key.getBytes());
    }


    /**
     * Long转换成无符号长整型（C中数据类型）
     */
    public static BigDecimal readUnsignedLong(long value) {
        if (value >= 0) {
            return new BigDecimal(value);
        }
        long lowValue = value & 0x7fffffffffffffffL;
        return BigDecimal.valueOf(lowValue).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1));
    }

    /**
     * 返回无符号murmur hash值
     */
    public static BigDecimal hashUnsigned(String key) {
        return readUnsignedLong(hash(key));
    }

    public static BigDecimal hashUnsigned(byte[] key) {
        return readUnsignedLong(hash(key));
    }
}
