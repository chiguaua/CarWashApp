package ru.nsu.carwashapplication.model;

public class Offer {
    int id;
    String img, title, time, price, color, text, btnColor;

    public Offer(int id, String img, String title, String time, String price, String color, String btnColor, String text) {
        this.id = id;
        this.img = img;
        this.title = title;
        this.time = time;
        this.price = price;
        this.color = color;
        this.text = text;
        this.btnColor = btnColor;
    }

    public String getBtnColor() {
        return btnColor;
    }

    public void setBtnColor(String btnColor) {
        this.btnColor = btnColor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
