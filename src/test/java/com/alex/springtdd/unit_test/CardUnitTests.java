package com.alex.springtdd.unit_test;

import com.alex.springtdd.Card;
import com.alex.springtdd.CardService;
import com.alex.springtdd.CardStore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ToDo
 *  -Card freeze sets status to noau
 *  -Card unfreeze sets status to norm
 *  -Card report lost creates a new card set to NEW, existing card set to LOST [new details]
 *  -Card report stolen creates a new card set to NEW, existing card set to STLC [new details]
 *  -Card report damaged creates a new card set to NEW (with existing details)
 */

public class CardUnitTests {

    @BeforeAll
    public static void before(){
        for(Card c : CardStore.cards) {
            c.setStatus("NORM");
        }
    }

    @Test
    public void updateCardStatus() {
        Card c = CardStore.getCardFromList(1);
        assertEquals(c.getStatus(), "NORM");

        CardStore.updateCardStatus(1, "NOAU");

        c = CardStore.getCardFromList(1);
        assertEquals(c.getStatus(), "NOAU");
    }

    @Test
    public void freezeCardUpdatesCardStatus() {
        CardService s = new CardService();
        s.freezeCard(1);
        Card c = CardStore.getCardFromList(1);
        assertEquals(c.getStatus(), "NOAU");
    }

    @Test
    public void unfreezeCardUpdatesCardStatus() {
        CardService s = new CardService();
        s.freezeCard(1);
        Card c = CardStore.getCardFromList(1);
        assertEquals(c.getStatus(), "NOAU");
        s.unfreezeCard(1);
        c = CardStore.getCardFromList(1);
        assertEquals(c.getStatus(), "NORM");
    }

}
