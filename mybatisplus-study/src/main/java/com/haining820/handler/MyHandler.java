package com.haining820.handler;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-13
 * Time: 18:02
 */

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName MyHandler
 */
@Slf4j    // TODO 总结lombok的使用
@Component  // 把处理器加到ioc容器中
public class MyHandler implements MetaObjectHandler {

//    private static Logger LOGGER = LoggerFactory.getLogger(MyHandler.class);

    /**
     * 插入、更新时的填充策略
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 接口中有相关的默认操作方法
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateTime", new Date(), metaObject);


    }
}
