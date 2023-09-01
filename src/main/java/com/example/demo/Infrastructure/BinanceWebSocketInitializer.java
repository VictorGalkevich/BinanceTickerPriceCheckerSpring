package com.example.demo.Infrastructure;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.example.demo.Config.BinanceProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class BinanceWebSocketInitializer {

    private final BinancePriceCache binancePriceCache;
    private final BinanceProperties binanceProperties;

    @Bean
    public BinanceApiWebSocketClient getBinanceWebsocketClient(){
        BinanceApiWebSocketClient binanceClient = BinanceApiClientFactory.newInstance().newWebSocketClient();
        startAggTradesEventStreaming(binanceClient);
        return binanceClient;
    }

    public void startAggTradesEventStreaming(BinanceApiWebSocketClient binanceClient) {
        log.info("Websocket binance client starting");
        binanceProperties.getTickers().forEach(ticker -> startClient(binanceClient, ticker));
        log.info("Websocket binance client is live");
    }

    private void startClient(BinanceApiWebSocketClient binanceClient, String ticker) {
        log.info("Starting client for ticker {}", ticker);
        binanceClient.onAggTradeEvent(ticker.toLowerCase(), this::updatePriceCache);
    }

    private void updatePriceCache(AggTradeEvent event) {
        binancePriceCache.getPriceCacheMap().put(event.getSymbol(), new BigDecimal(event.getPrice()));
        log.info("Ticker {} price {}", event.getSymbol(), event.getPrice());
    }
}