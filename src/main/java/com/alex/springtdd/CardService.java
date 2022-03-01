package com.alex.springtdd;

import org.springframework.stereotype.Service;

@Service
public class CardService {

    public CardService(){

    }

    public Card getCardGivenIndex(int index) {
        return CardStore.getCardFromList(index);
    }

    public void freezeCard(int index) {
        CardStore.updateCardStatus(index, "NOAU");
    }

    public void unfreezeCard(int index) {
        CardStore.updateCardStatus(index, "NORM");
    }

    public void reportCardAsLost(int index) {
        reportCard(index, "LOST");
        replaceCard(index, "1230123012301230");
    }

    public void reportCardAsStolen(int index) {
        reportCard(index, "STLC");
        replaceCard(index, "1230123012301230");
    }

    public void reportCardAsDamaged(int index) {
        reportCard(index, "DMGD");
        replaceCard(index);
    }

    public void reportCard(int index, String status) {
        CardStore.updateCardStatus(index, status);
    }

    public void replaceCard(int index) {
        Card reportedCard = CardStore.getCardFromList(index);
        CardStore.addCard(new Card(reportedCard.getName(), reportedCard.getCardNumber(), "333333", "NEW"));
    }

    public void replaceCard(int index, String cardNumber) {
        Card reportedCard = CardStore.getCardFromList(index);
        CardStore.addCard(new Card(reportedCard.getName(), cardNumber, "333333", "NEW"));
    }
}
