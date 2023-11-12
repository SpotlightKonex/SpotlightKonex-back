package com.spotlightkonex.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @PersistenceContext  // EntityManager 인스턴스를 주입하는데 사용
    private EntityManager em;
}