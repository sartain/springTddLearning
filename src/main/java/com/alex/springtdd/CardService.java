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

    public void reportCardAsLost(int index) {
        Card reportedCard = CardStore.getCardFromList(index);
        CardStore.updateCardStatus(index, "LOST");
        CardStore.addCard(new Card(reportedCard.getName(), "1230123012301230", "333333", "NEW"));
    }

    public void reportCardAsStolen(int index) {
        Card reportedCard = CardStore.getCardFromList(index);
        CardStore.updateCardStatus(index, "STLC");
        CardStore.addCard(new Card(reportedCard.getName(), "1230123012301230", "333333", "NEW"));
    }

    public void reportCardAsDamaged(int index) {
        Card reportedCard = CardStore.getCardFromList(index);
        CardStore.updateCardStatus(index, "DMGD");
        CardStore.addCard(new Card(reportedCard.getName(), reportedCard.getCardNumber(), "333333", "NEW"));
    }
}
