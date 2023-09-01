package com.example.demo.Endpoint;

import com.example.demo.Domain.BinancePriceFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/binance/price")
public class BinancePriceController {

    private final BinancePriceFacade binancePriceFacade;

    @GetMapping("/{symbol}")
    public ResponseEntity<BigDecimal> getActualPrice(@PathVariable String symbol) {
        return ResponseEntity.of(binancePriceFacade.getActualPrice(symbol));
    }


}