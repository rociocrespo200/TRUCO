package com.tallerwebi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    //Datos y configuracion del motor con el que voy a matchear
    //
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        //se debe buscar el connection string para el motor que se elija
        dataSource.setUrl("jdbc:mysql://localhost:3306/data");
        dataSource.setUsername("root");
        dataSource.setPassword("46521541");
        return dataSource;
    }

    //el sectionFactory es la sesion que voy a tener para manejar o fabricar conexiones a la bd
    //sin esta sesion no me puedo comunicar con la bd
    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        //en donde voy a ir a buscar mis @Entity
        sessionFactory.setPackagesToScan("com.tallerwebi.dominio");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    //cómo se van a manejar las transacciones?

    @Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory(dataSource()).getObject());
    }


    private Properties hibernateProperties() {
        Properties properties = new Properties();
        //esto hay que cambiarlo dependiendo del motor que usemos
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        //en modo prd esto debe quedar en false (los dos). Sale por consola  lo que hibernate hace por mi
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        //qué va a hacer hibernate con el esquema de bd. (en prd debe quedar en none en donde dice create)
        //en create Hibernate crea por mi las tablas en la bd, con update las updatea si es necesario
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
}
