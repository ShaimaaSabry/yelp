//package com.shaimaa.yelp.repository;
//
//import org.jooq.impl.DataSourceConnectionProvider;
//import org.jooq.impl.DefaultConfiguration;
//import org.jooq.impl.DefaultDSLContext;
//import org.jooq.impl.DefaultExecuteListenerProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
//
//import javax.sql.DataSource;
//
//
//@Configuration
//class JooqConfiguration {
//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    DefaultDSLContext dsl() {
//        return new DefaultDSLContext(configuration());
//    }
//
//    DefaultConfiguration configuration() {
//        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
//        jooqConfiguration.set(connectionProvider());
////        jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTransformer()));
//
//        return jooqConfiguration;
//    }
//
//    @Bean
//    DataSourceConnectionProvider connectionProvider() {
//        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
//    }
//}
