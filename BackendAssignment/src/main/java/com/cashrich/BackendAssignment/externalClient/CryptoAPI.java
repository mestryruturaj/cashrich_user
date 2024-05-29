package com.cashrich.BackendAssignment.externalClient;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class CryptoAPI {
    private RestTemplate restTemplate;
    private static final String X_CMC_PRO_API_KEY = "X-CMC_PRO_API_KEY";
    private static final String URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=BTC,ETHLTC";

    @Autowired
    public CryptoAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getCoins(Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(X_CMC_PRO_API_KEY.toLowerCase(), headers.get(X_CMC_PRO_API_KEY.toLowerCase()));

        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);
        try {
            ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, String.class);
            return response;
        } catch (HttpClientErrorException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
