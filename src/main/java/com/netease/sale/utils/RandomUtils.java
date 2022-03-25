package com.netease.sale.utils;

import java.util.UUID;

/**
 * @author zpw
 * @Package com.netease.sale.utils
 * @date 2022/3/10 14:46
 */
public class RandomUtils {
    public static String getRandomUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
