package com.alex.springtdd;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    CardService service = new CardService();

    @GetMapping("/cards/{index}")
    public Card getCardFromList(@RequestParam int index) {
        return service.getCardGivenIndex(index);
    }
}
