package com.company;

public class RentalHistory {
    private int media_id;
    private String rentalDate;
    private int customer_id;
    private String returnDate;

    public RentalHistory() {
    }

    public RentalHistory(int media_id, String rentalDate, int customer_id, String returnDate) {
        this.media_id = media_id;
        this.rentalDate = rentalDate;
        this.customer_id = customer_id;
        this.returnDate = returnDate;
    }

    public int getMedia_id() {
        return media_id;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
