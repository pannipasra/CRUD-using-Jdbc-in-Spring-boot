package com.example.demoCRUD_JDBC_3_1.POJO;

public class Book {
    private long id;
    private String title;
    private String auther;
    private int price;

    public Book() {
    }

    public Book(String title, String auther, int price) {
        this.title = title;
        this.auther = auther;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", auther='" + auther + '\'' +
                ", price=" + price +
                '}';
    }
}
