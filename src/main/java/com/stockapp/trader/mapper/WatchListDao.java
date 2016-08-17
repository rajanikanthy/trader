package com.stockapp.trader.mapper;

import com.stockapp.trader.domain.WatchList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by rajaniy on 8/16/16.
 */
@Component
public class WatchListDao {
    @Autowired
    private SqlSession sqlSession;

    public Collection<WatchList> findAllWatchLists() {
        return this.sqlSession.selectList("findAllWatchLists");
    }

    public void createWatchList(WatchList watchList) {
        this.sqlSession.insert("createWatchList", watchList);
    }

    public Collection<WatchList> findAllExpiredWatchLists() {
        return this.sqlSession.selectList("findAllExpiredWatchLists");
    }
}
