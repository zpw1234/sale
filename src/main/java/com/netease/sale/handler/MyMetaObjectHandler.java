package com.netease.sale.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author zpw
 * @Package com.netease.sale.handler
 * @date 2022/3/7 17:14
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.hasGetter("createTime")){
            log.info("----------------------");
            this.setFieldValByName("create_time", new Date(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter("updateTime")) {
            log.info("-------+++++++++-----");
            this.setFieldValByName("update_time", new Date(), metaObject);
        }
    }


}
