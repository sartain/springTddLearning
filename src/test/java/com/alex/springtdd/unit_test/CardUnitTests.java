package com.alex.springtdd.unit_test;

import com.alex.springtdd.Card;
import com.alex.springtdd.CardService;
import com.alex.springtdd.CardStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * ToDo
 *  -Card freeze sets status to noau
 *  -Card unfreeze sets status to norm
 *  -Card report lost creates a new card set to NEW, existing card set to LOST [new details]
 *  -Card report stolen creates a new card set to NEW, existing card set to STLC [new details]
 *  -Card report damaged creates a new card set to NEW (with existing details)
 */
public class CardUnitTests {

    /**
     * Remove any new cards created during testing
     * Reset all cards to 'NORM'
     */

    @BeforeEach
    public void before() {
        CardStore.cardList = CardStore.cardList.stream().filter(e -> e.getStatus() != "NEW").collect(Collectors.toList());
        CardStore.cardList.forEach(e -> e.setStatus("NORM"));
    }

    @Test
    public void updateCardStatus() {
        Card c = CardStore.getCardFromList(1);
        assertEquals("NORM", c.getStatus());
        CardStore.updateCardStatus(1, "NOAU");
        c = CardStore.getCardFromList(1);
        assertEquals("NOAU", c.getStatus());
    }

    @Test
    public void freezeCardUpdatesCardStatus() {
        CardService s = new CardService();
        s.freezeCard(1);
        Card c = CardStore.getCardFromList(1);
        assertEquals("NOAU", c.getStatus());
    }

    @Test
    public void unfreezeCardUpdatesCardStatus() {
        CardService s = new CardService();
        s.freezeCard(1);
        Card c = CardStore.getCardFromList(1);
        assertEquals("NOAU", c.getStatus());

        s.unfreezeCard(1);
        c = CardStore.getCardFromList(1);
        assertEquals("NORM", c.getStatus());
    }

    @Test
    public void reportLostUpdatesCardStatusAndReissuesNewCard() {
        CardService s = new CardService();
        Card originalCard = CardStore.getCardFromList(1);
        s.reportCardAsLost(1);
        Card newCard = CardStore.getCardFromList(CardStore.cardList.size() - 1);

        assertNotEquals(newCard.getCardNumber(), originalCard.getCardNumber());
        assertEquals("NEW", newCard.getStatus());
        assertEquals("LOST", originalCard.getStatus());
    }

}
