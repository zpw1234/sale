package com.netease.sale.utils;

import com.netease.sale.entity.User;

/**
 * @author zpw
 * @Package com.zpw.blogapi.utils
 * @date 2022/2/25 10:13
 */
public class UserThreadLocal {
    private UserThreadLocal(){}

    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User User){
        LOCAL.set(User);
    }

    public static User get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
