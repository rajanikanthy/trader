package com.stockapp.trader.actors;

import akka.actor.UntypedActor;
import com.stockapp.trader.services.YahooQuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by rajaniy on 8/17/16.
 */
@Scope("prototype")
@Component
public class YahooQuoteActor extends UntypedActor {

    private static final Logger _logger = LoggerFactory.getLogger(YahooQuoteActor.class);

    @Autowired
    private YahooQuoteService yahooQuoteService;

    public static class RequestQuoteMessage {
        private String symbol;

        public RequestQuoteMessage(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    @Override
    public void onReceive(Object o) throws Throwable {
        if (o instanceof RequestQuoteMessage) {
            RequestQuoteMessage requestQuoteMessage = (RequestQuoteMessage) o;
            String response = yahooQuoteService.currentPrice(requestQuoteMessage.getSymbol());
            _logger.info(response);
        } else {
            unhandled(o);
        }
    }
}
