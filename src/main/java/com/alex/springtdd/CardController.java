package com.alex.springtdd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CardController {

    CardService service = new CardService();

    @GetMapping("/card")
    public Mono<Card> getCardFromList(@RequestParam(required = true) int index) {
        return Mono.just(service.getCardGivenIndex(index));
    }

    @GetMapping("/cards")
    public Flux<Card> getCardFromList() {
        return Flux.fromIterable(service.getAllCards());
    }
}
