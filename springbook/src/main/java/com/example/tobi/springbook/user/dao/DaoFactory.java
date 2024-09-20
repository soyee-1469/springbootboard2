package com.example.tobi.springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao(){
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);

        return new UserDao(connectionMaker()) ;
    }
    @Bean
    public AccountDao accountDao(){
        return new AccountDao(connectionMaker()) ;
    }
    @Bean
    public MessageDao messageDao(){
        return new MessageDao(connectionMaker()) ;
    }
    @Bean
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}
