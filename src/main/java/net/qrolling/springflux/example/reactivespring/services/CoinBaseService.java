package net.qrolling.springflux.example.reactivespring.services;

import net.qrolling.springflux.example.reactivespring.model.CoinBaseResponse;
import net.qrolling.springflux.example.reactivespring.model.Purchase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CoinBaseService {

    Mono<CoinBaseResponse> getCryptoPrice(String priceName);

    Mono<Purchase> createPurchase (String priceName);

    Mono<Purchase> getPurchaseById(String id);

    Flux<Purchase> listAllPurchases();
}
