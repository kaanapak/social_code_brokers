package com.sample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class Database {
    String url = "jdbc:mysql://localhost:3306/cs_320?serverTimezone=UTC";
    String username = "root";
    String password = "647400";


    Connection connection = DriverManager.getConnection(url, username, password);

    public Database() throws SQLException {
    }
   /* @Bean
    public DataSource MySQLDataSource()
    {
        DriverManagerDataSource source = new DriverManagerDataSource();

        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306");
        source.setUsername("root");
        source.setPassword("databasesystems");

        System.out.println("Database connected.");


        return source;
    } */
  /* @Bean(name = "dataSource")
   public DriverManagerDataSource dataSource() {
       DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
       driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
       driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306");
       driverManagerDataSource.setUsername("root");
       driverManagerDataSource.setPassword("databasesystems");
       return driverManagerDataSource;
   }
    @Autowired
    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource ds) {
        JdbcTemplate jtemp = new JdbcTemplate();
        jtemp.setDataSource(ds);
        return jtemp;
    } */


}

