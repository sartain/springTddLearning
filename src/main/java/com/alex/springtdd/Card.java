package com.alex.springtdd;

public class Card {

    private String status;
    private String personName;
    private String cardNumber;
    private String cardSerno;

    public Card(String personName, String cardNumber, String cardSerno, String status) {
        this.personName = personName;
        this.cardNumber = cardNumber;
        this.cardSerno = cardSerno;
        this.status = status;
    }

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
        return personName;
    }
}
