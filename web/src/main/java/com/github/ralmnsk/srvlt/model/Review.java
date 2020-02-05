package com.github.ralmnsk.srvlt.model;

public class Review {
    private long id;
    private String review;
    private Person tuitor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Person getTuitor() {
        return tuitor;
    }

    public void setTuitor(Person tuitor) {
        this.tuitor = tuitor;
    }

    public Review() {
    }

    public Review(String review, Person tuitor) {
        this.review = review;
        this.tuitor = tuitor;
    }

    public Review(long id, String review, Person tuitor) {
        this.id = id;
        this.review = review;
        this.tuitor = tuitor;
    }
}
