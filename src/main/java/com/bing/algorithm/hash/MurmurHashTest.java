package com.bing.algorithm.hash;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MurmurHashTest {

    private static final Logger logger = LoggerFactory.getLogger(MurmurHashTest.class);

    @Test
    public void test() throws IOException {
        assertThat(MurmurHash.hashUnsigned("chenshuo").toString()).isEqualTo("5016608279269930526");
        assertThat(MurmurHash.hashUnsigned("shaoguoqing").toString()).isEqualTo("17121371936686143062");
        assertThat(MurmurHash.hashUnsigned("baozenghui").toString()).isEqualTo("5451996895512824982");
        assertThat(MurmurHash.hashUnsigned("05ff62ff6f7749ffff43ffff6673ff65").toString()).isEqualTo("10652549470333968609");
        assertThat(MurmurHash.hashUnsigned("hahahaha").toString()).isEqualTo("15134676900169534748");
        assertThat(MurmurHash.hashUnsigned("hahah1369531321aha5466sfdfaerttedddd56da").toString()).isEqualTo("6439159232526071817");
        assertThat(MurmurHash.hashUnsigned("测试汉字").toString()).isEqualTo("1146745369200541601");
        assertThat(MurmurHash.hashUnsigned("1234566大大21".getBytes("GBK")).toString()).isEqualTo("10129762727109086067");
        assertThat(MurmurHash.hashUnsigned("12345665哦4哦3我的妈呀21".getBytes("GBK")).toString()).isEqualTo("5141842319936259217");
    }

    @Test
    public void shotUrlTest(){
        ShortUrl shortUrl = new ShortUrl();
        String shortHash = shortUrl.shortenUrl("http://www.baidu.com/q=kskkdkdk?schoolNo=848484&tradeNo=94484");
        System.out.println(shortHash);
    }

}
