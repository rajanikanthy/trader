package com.stockapp.trader.config;

import com.stockapp.trader.domain.WatchList;
import com.stockapp.trader.services.YahooQuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.concurrent.Executors;

/**
 * Created by rajaniy on 8/17/16.
 */
@Configuration
public class TraderApplicationConfig {

    private static final Logger _logger = LoggerFactory.getLogger(TraderApplicationConfig.class);

    @Autowired
    private YahooQuoteService yahooQuoteService;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IntegrationFlow priceAlerts() {
        return flow -> {
          flow.split(WatchList.class, WatchList::getSymbols)
                  .channel(c -> c.executor(Executors.newCachedThreadPool()))
                  .split(String.class, s -> s.toString().split(","))
                  .transform(Transformers.converter(new Converter<Object, String>() {
                      @Override
                      public String convert(Object source) {
                          return yahooQuoteService.currentPrice(source.toString());
                      }
                  }))
                  .handle(message -> {_logger.info("Received response :{}", message.getPayload().toString().trim());});
        };
    }
}
