package com.company;

public class Movie {

    private int movie_id;
    private String title, description, rating, category, release_date;

    public Movie() {
    }

    public Movie(int movie_id, String title, String description, String rating, String category, String release_date) {
        this.movie_id = movie_id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.release_date = release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Override
    public String toString() {
        return "**************" + "\n" +
                "movie_id: " + movie_id + "\n" +
                "title: " + title + "\n" +
                "description: " + description + "\n" +
                "rating: " + rating + "\n" +
                "category: " + category + "\n" +
                "release_date: " + release_date + "\n" +
                "************";
    }
}
