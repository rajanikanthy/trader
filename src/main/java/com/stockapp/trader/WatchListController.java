package com.stockapp.trader;

import com.stockapp.trader.domain.WatchList;
import com.stockapp.trader.mapper.WatchListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rajaniy on 8/16/16.
 */
@RestController
@RequestMapping("/watchlist")
public class WatchListController {

    @Autowired
    private WatchListDao watchListDao;

    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<WatchList> watchLists() {
       return watchListDao.findAllWatchLists();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createWatchList(@RequestBody WatchList watchList) {
        watchListDao.createWatchList(watchList);
    }

}
