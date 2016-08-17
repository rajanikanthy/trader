package com.stockapp.trader.scheduler;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import com.stockapp.trader.actors.YahooQuoteActor;
import com.stockapp.trader.config.PriceAlert;
import com.stockapp.trader.config.SpringExtension;
import com.stockapp.trader.domain.WatchList;
import com.stockapp.trader.mapper.WatchListDao;
import org.omg.CORBA.TIMEOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import static com.stockapp.trader.config.SpringExtension.SpringExtProvider;

/**
 * Created by rajaniy on 8/16/16.
 */
@Component
public class PriceAlertScheduler {

    private static final Logger _logger = LoggerFactory.getLogger(PriceAlertScheduler.class);

    @Autowired
    private WatchListDao watchListDao;

    @Autowired
    private PriceAlert priceAlert;

    @Autowired
    private ActorSystem actorSystem;

    @Scheduled(fixedDelay = 10000L)
    public void scheduleAlerts() {
        _logger.info("Fetching expired watchlists");
        Collection<WatchList> watchLists = watchListDao.findAllExpiredWatchLists();
        ActorPath path = actorSystem.child("yahooQuoteRequester");
        Future<ActorRef> actorRefFuture = actorSystem.actorSelection(path).resolveOne(Timeout.durationToTimeout(FiniteDuration.apply(2, TimeUnit.SECONDS)));
        ActorRef existingActorRef = null;
        try {
            existingActorRef = Await.result(actorRefFuture, Duration.apply(2, TimeUnit.SECONDS));
        } catch (Exception e) {
            existingActorRef = null;
        }
        ActorRef actorRef = existingActorRef != null ? existingActorRef : actorSystem.actorOf(SpringExtProvider.get(actorSystem).props("yahooQuoteActor"), "yahooQuoteRequester");
        watchLists.stream().forEach( w -> {
           Arrays.stream(w.getSymbols().split(",")).forEach(s -> actorRef.tell(new YahooQuoteActor.RequestQuoteMessage(s.trim()), null));
        });
    }
}
