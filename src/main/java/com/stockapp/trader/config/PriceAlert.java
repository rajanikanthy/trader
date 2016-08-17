package com.stockapp.trader.config;

import com.stockapp.trader.domain.WatchList;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.Collection;

/**
 * Created by rajaniy on 8/17/16.
 */
@MessagingGateway
public interface PriceAlert {
    @Gateway(requestChannel = "priceAlerts.input")
    void evaluate(WatchList watchList);
}
