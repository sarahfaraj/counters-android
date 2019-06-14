package com.example.http2.counter;

public class Counter {
    //public String url;
    public String title;
    public Integer count;
    private int id;


    public String getTitle() {
        return title;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getId() {
        return id;
    }

    public String getIdString() {
        return getId().toString();
    }

    public String getCountString() {
        return getCount().toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "title='" + title + '\'' +
                ", count=" + count +
                ", id=" + id +
                '}';
    }
}
