package net.qrolling.springflux.example.reactivespring.controller;

import net.qrolling.springflux.example.reactivespring.model.Purchase;
import net.qrolling.springflux.example.reactivespring.services.CoinBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/coin/purchase/v1")
public class PurchaseController {

    @Autowired
    private CoinBaseService coinBaseService;

    @PostMapping(value = "/{name}")
    public Mono<Purchase> createPurchase(@PathVariable("name") String name){
        return   coinBaseService.createPurchase(name);
    }
}
