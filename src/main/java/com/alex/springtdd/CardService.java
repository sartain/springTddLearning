package com.alex.springtdd;

import org.springframework.stereotype.Service;

@Service
public class CardService {

    public CardService(){

    }

    public void freezeCard(int index) {
        CardStore.updateCardStatus(index, "NOAU");
    }

    public void unfreezeCard(int index) {
        CardStore.updateCardStatus(index, "NORM");
    }
}
