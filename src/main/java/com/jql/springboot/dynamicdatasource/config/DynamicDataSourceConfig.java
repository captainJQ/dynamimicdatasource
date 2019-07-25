package com.jql.springboot.dynamicdatasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan(basePackages = "com.jql.springboot.dynamicdatasource.mapper")
public class DynamicDataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix = "dynamic-datasource.beijing")
    public DataSource getBeiJing(){
         return DruidDataSourceBuilder.create().build();
    }
    @Bean
    @ConfigurationProperties(prefix = "dynamic-datasource.shenzhen")
    public DataSource getShenZhen(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 核心配置动态路由
     *
     * @return
     */
    @Bean
    public DataSource  dynamicDataSource(){
        DynamicRoutingDataSource dataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        // 设置默认数据源
        dataSource.setDefaultTargetDataSource(getBeiJing());

        // 配置数据源
        dataSourceMap.put(AreaAndKey.BEIJING, getBeiJing());
        dataSourceMap.put(AreaAndKey.SHENZHEN, getShenZhen());
        dataSource.setTargetDataSources(dataSourceMap);
        return dataSource;
    }
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        // 此处设置为了解决找不到mapper文件的问题
        // sqlSessionFactoryBean.setMapperLocations(new
        // PathMatchingResourcePatternResolver().getResources("classpath:mapping/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /**
     * 事务管理
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }

}
