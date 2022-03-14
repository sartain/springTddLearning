package com.alex.springtdd;

import java.util.Objects;

public class Card {

    private String status;
    private String name;
    private String cardNumber;
    private String cardSerno;

    //For deserialization
    public Card() {}

    public Card(String name, String cardNumber, String cardSerno, String status) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cardSerno = cardSerno;
        this.status = status;
    }

    public String getCardSerno() { return cardSerno; }

    public void setCardSerno(String cardSerno) { this.cardSerno = cardSerno;}

    public void setCardNumber(String cardNumber) {this.cardNumber = cardNumber;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Card)) {
            return false;
        }
        Card c = (Card) o;
        return Objects.equals(c.cardNumber, this.cardNumber);
    }
}
