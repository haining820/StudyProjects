package com.haining820.config;
/**
 * Created with IntelliJ IDEA
 * Description:
 * User: hn.yu
 * Date: 2022-08-13
 * Time: 22:20
 */

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MybatisConfig
 * @Description: TODO
 */

@Configuration  // 配置类
@EnableTransactionManagement    // 开启事务
@MapperScan("com.haining820.mapper")
public class MybatisPlusConfig {

    // 注册乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        /**
         * 设置请求的页面大于最大页后操作，true调回到首页，false继续请求，默认false
         * paginationInterceptor，setOverflow(false)
         * 设置最大单页限制数量，默认500条，-1不受限制
         * paginationInterceptor，setLimit(500);
         * 开启count的join优化，只针对部分Left join
         */
/*        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return paginationInterceptor;*/
        return new PaginationInterceptor();

    }

}
