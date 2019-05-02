package net.qrolling.springflux.example.reactivespring.controller;

import net.qrolling.springflux.example.reactivespring.model.Purchase;
import net.qrolling.springflux.example.reactivespring.services.CoinBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public class PurchaseHandler {

    @Autowired
    private CoinBaseService coinBaseService;

    public Mono<ServerResponse> listPurchases(ServerRequest serverRequest){
        final Mono<Purchase> purchase =
                coinBaseService.getPurchaseById(serverRequest.pathVariable("id"));
        return  ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(purchase, Purchase.class);
    }

    public Mono<ServerResponse> listAllPurchases(ServerRequest serverRequest){
        final Flux<Purchase> purchaseFlux = coinBaseService.listAllPurchases();
        return  ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(purchaseFlux.collectList(), new ParameterizedTypeReference<List<Purchase>>() {
                });
    }
}
