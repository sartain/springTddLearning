package com.alex.springtdd;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Flux<Card> getCards() {
        return Flux.fromIterable(service.getAllCards());
    }

    @PostMapping("/cards/freeze")
    public Mono<HttpStatus> freezeCard(@RequestParam(required = true) int index) {
        try{
            service.freezeCard(index);
            return Mono.just(HttpStatus.OK);
        }
        catch(Exception e) {
            return Mono.just(HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
