package com.stockapp.trader.config;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.stockapp.trader.config.SpringExtension.SpringExtProvider;

/**
 * Created by rajaniy on 8/17/16.
 */
@Configuration
public class AppConfiguration {
    // the application context is needed to initialize the Akka Spring Extension
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * Actor system singleton for this application.
     */
    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("AkkaJavaSpring");
        // initialize the application context in the Akka Spring Extension
        SpringExtProvider.get(system).initialize(applicationContext);
        return system;
    }
}
