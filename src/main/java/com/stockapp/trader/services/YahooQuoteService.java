package com.stockapp.trader.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by rajaniy on 8/17/16.
 */
@Component
public class YahooQuoteService {
    @Autowired
    private RestTemplate restTemplate;

    public String currentPrice(String symbol) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString("https://query.yahooapis.com/v1/public/yql");
        uriComponentsBuilder.queryParam("q", "select * from yahoo.finance.quotes where symbol = '" + symbol + "'");
        uriComponentsBuilder.queryParam("format", "json");
        uriComponentsBuilder.queryParam("diagnostics", "true");
        uriComponentsBuilder.queryParam("env", "store://datatables.org/alltableswithkeys");
        uriComponentsBuilder.queryParam("callback", "json");
        HttpEntity<String> response = restTemplate.getForEntity(uriComponentsBuilder.build().toUri(), String.class);
        return response.getBody();
    }
}
