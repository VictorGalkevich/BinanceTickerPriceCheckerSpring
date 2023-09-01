package com.example.demo.Infrastructure;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Slf4j
@Service
public class BinancePriceCache {

    private ConcurrentHashMap<String, BigDecimal> priceCacheMap = new ConcurrentHashMap<>();

    public BigDecimal getPrice(String symbol){
        return priceCacheMap.get(symbol);
    }

}