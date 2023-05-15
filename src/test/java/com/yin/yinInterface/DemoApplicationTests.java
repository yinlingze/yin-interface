package com.yin.yinInterface;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() throws InterruptedException {
        while (true){
            Thread.sleep(1000);
            System.out.println(getTimestamp());
        }
    }


    public static String getTimestamp() {
        // TODO
        long time = System.currentTimeMillis();
        String timestamp = String.valueOf(time / 1000);
        return timestamp;
    }


}
