package com.wiseSaying;

public class WiseSaying {

    private int number;
    private String wiseSay;
    private String author;

    public WiseSaying(int number, String wiseSay, String author) {
        this.number = number;
        this.wiseSay = wiseSay;
        this.author = author;
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getWiseSay() {
        return wiseSay;
    }

    public void setWiseSay(String wiseSay) {
        this.wiseSay = wiseSay;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return number + " / " + author + " / " + wiseSay;
    }
}
