package com.example.demo.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.Set;

@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "app.binance")
public class BinanceProperties {

    private Set<String> tickers = new HashSet<>();
}