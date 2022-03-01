package com.alex.springtdd;
import java.util.*;

public class CardStore {

    private static Card sc_1 = new Card("Alex", "1234123412341234", "123456", "NORM");
    private static Card sc_2 = new Card("Bob", "1324132413241324", "654321", "NORM");
    private static Card sc_3 = new Card("Carl", "4321432143214321", "135246", "NORM");
    private static Card sc_4 = new Card("Dion", "4231423142314231", "246135", "NORM");

    private static Card[] cards = {sc_1, sc_2, sc_3, sc_4};
    public static List<Card> cardList = new ArrayList<Card>(Arrays.asList(cards));

    public static Card getCardFromList(int index) {
        return cardList.get(index);
    }

    public static void updateCardStatus(int index, String status) {
        cardList.get(index).setStatus(status);
    }

    public static void addCard(Card c) {
        cardList.add(c);
    }

}
