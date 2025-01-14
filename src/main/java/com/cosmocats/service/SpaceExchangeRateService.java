package com.cosmocats.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SpaceExchangeRateService {

    private final RestTemplate restTemplate = new RestTemplate();

    public SpaceExchangeRateResponse getExchangeRate() {
        String url = "http://localhost:9999/space-exchange-rate";
        return restTemplate.getForObject(url, SpaceExchangeRateResponse.class);
    }
}

