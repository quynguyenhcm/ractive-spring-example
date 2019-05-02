package net.qrolling.springflux.example.reactivespring.services;

import net.qrolling.springflux.example.reactivespring.model.CoinBaseResponse;
import net.qrolling.springflux.example.reactivespring.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class CoinBaseServiceImpl implements CoinBaseService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private ReactiveMongoOperations a;

    @Override
    public Mono<CoinBaseResponse> getCryptoPrice(String priceName) {
        return webClient.get()
                .uri("https://api.coinbase.com/v2/prices/{crypto}/buy}",priceName)
                .exchange()
                .flatMap(clientResponse -> clientResponse.bodyToMono(CoinBaseResponse.class));
    }

    @Override
    public Mono<Purchase> createPurchase(String priceName) {
        return getCryptoPrice(priceName).flatMap((price-> a.save(
                new Purchase(priceName, price.getData().getAmount(), LocalDateTime.now())
        )));
    }

    @Override
    public Mono<Purchase> getPurchaseById(String id) {
        return a.findById(id, Purchase.class);
    }

    @Override
    public Flux<Purchase> listAllPurchases() {
        return a.findAll(Purchase.class);
    }


}
