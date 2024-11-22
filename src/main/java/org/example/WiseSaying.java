package org.example;

public class WiseSaying {
    int number;
    String wise_saying;
    String author;


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getWise_saying() {
        return wise_saying;
    }

    public void setWise_saying(String wise_saying) {
        this.wise_saying = wise_saying;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public WiseSaying(int number, String wise_saying, String author) {
        this.number = number;
        this.wise_saying = wise_saying;
        this.author = author;
    }

    @Override
    public String toString() {
        return number + " / " + author + " / " + wise_saying;
    }
}
