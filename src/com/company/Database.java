package com.company;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;


class Database {

    //used to establish connection to the movie_store_db file using the jdbc driver
    //This method is used by other methods of the Database class to perform CRUD operations to the movie database
    private Connection connect(){
        Connection conn = null;
        String url = "jdbc:sqlite:/Users/keganronholt/IdeaProjects/TheVideoStore/movie_store.db";
        try {
            conn = DriverManager.getConnection(url);

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }


    //receives information from main and uses a prepared sql statement to insert into the database.
    public boolean addCustomer(String fname, String lname, String phone, String address, String city, String state, String zip, String email){
        String sql = "INSERT INTO customers (fname, lname, phone, email, " +
                "address, city, state, zip) VALUES (?,?,?,?,?,?,?,?)";

        //if the connect() works, the prepared statement is built and executed
        try(Connection conn = this.connect()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.setString(6, city);
            pstmt.setString(7, state);
            pstmt.setString(8, zip);
            pstmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

        return true;

    }

    //Receives movie information and uses prepared sql statement to insert into the database
    public boolean addMovie(String title, String description, String rating, String category, String release_date){
        String sql = "INSERT INTO movies (title,description,rating,category,release_date) VALUES (?,?,?,?,?)";

        try(Connection conn = this.connect()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setString(3, rating);
            pstmt.setString(4, category);
            pstmt.setString(5, release_date);
            pstmt.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //executes the insert statement, adding a new row to the actors table
    public boolean addActor(String stageName, String fname, String lname, String birth_date){
        String sql = "INSERT INTO actors (stage_name, fname, lname, birth_date) VALUES (?,?,?,?)";

        try(Connection conn = this.connect()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, stageName);
            pstmt.setString(2, fname);
            pstmt.setString(3, lname);
            pstmt.setString(4, birth_date);
            pstmt.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    //This is called when a new movie and actor are entered, this associates actors to the films they star in
    public void addStar_billings(String movieTitle, String actorName){
        Movie movie = getMovie(movieTitle);
        Actor actor = getActor(actorName);
        String sql = " ";
        if(movie.getMovie_id() > 0 && actor.getActor_id() > 0) {
            sql = "INSERT INTO star_billings (movie_id, actor_id) VALUES (" + movie.getMovie_id() +
                    ", " + actor.getActor_id() + " );";
        }

        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    //returns a single Actor from the database using the stageName to search
    public Actor getActor(String stageName){
        String sql = "SELECT * FROM actors WHERE stage_name = '" + stageName + "'";
        Actor actor = new Actor();

        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                actor.setActor_id(rs.getInt("actor_id"));
                actor.setStage_name(rs.getString("stage_name"));
                actor.setFname(rs.getString("fname"));
                actor.setLname(rs.getString("lname"));
                actor.setBirth_date(rs.getString("birth_date"));

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return actor;
    }

    //returns a single movie from the database using title to search
    public Movie getMovie(String title){
        String sql = "SELECT * FROM movies WHERE UPPER(title) = '" + title.toUpperCase() + "'";
        Movie movie = new Movie();

        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                movie.setMovie_id(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setRating(rs.getString("rating"));
                movie.setCategory(rs.getString("category"));
                movie.setRelease_date(rs.getString("release_date"));

            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return movie;
    }



    //queries the movies table and returns the values which are built into Movie objects and sent back in an arrayList
    public ArrayList<Movie> getMovies(){
        String sql = "SELECT * FROM movies";

        ArrayList<Movie> movies = new ArrayList<>();
        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //looks in the resultSet and sets the values for each field in movie object
            while(rs.next()){
                Movie movie = new Movie();
                movie.setMovie_id(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setRating(rs.getString("rating"));
                movie.setCategory(rs.getString("category"));
                movie.setRelease_date(rs.getString("release_date"));

                movies.add(movie);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return movies;
    }

    //returns all the films by genre
    public ArrayList<Movie> getMoviesByGenre(String genre){
        String sql = "SELECT * FROM movies WHERE UPPER(category) = '" + genre.toUpperCase() + "'";

        ArrayList<Movie> movies = new ArrayList<>();
        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //looks in the resultSet and sets the values for each field in movie object
            while(rs.next()){
                Movie movie = new Movie();
                movie.setMovie_id(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setRating(rs.getString("rating"));
                movie.setCategory(rs.getString("category"));
                movie.setRelease_date(rs.getString("release_date"));

                movies.add(movie);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return movies;
    }

    //This function facilitates returning checked out movies
    public boolean returnMovie(int movie_id, String type, String date){

        //checks if movie is in the unavailable (checked out) list. If it is not, it cannot be returned
        if(this.movieIsAvailable(movie_id, type)){
            return false;
        }

        String sql = "UPDATE rental_history SET return_date = '" + date +
                "' WHERE media_id = (SELECT media_id FROM media WHERE title_id = " + movie_id +
                " AND format = '" + type + "');";

        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Movie returned.");
            return true;

        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Return incomplete.");
        }

        return false;

    }

    //This method facilitates the rental of a movie
    public String rentMovie(int movie_id, String type, int customer_id, String rental_date){

        try(Connection conn = this.connect()){
            int media_id = 0;
            //if the movie is not currently checked out, gets the media_id to insert the new row into rental_history
            if(movieIsAvailable(movie_id, type)) {
                String findMediaNumber = "SELECT media_id FROM media JOIN movies ON(movie_id = title_id) WHERE title_id = " + movie_id +
                        " AND format = '" + type + "';";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(findMediaNumber);
                while (rs.next()) {
                    media_id = rs.getInt("media_id");
                }

                String insertRentalHistory = "INSERT INTO rental_history (media_id, rental_date, customer_id) VALUES (" + media_id +
                        ", '" + rental_date + "' , " + customer_id + ");";

                stmt.execute(insertRentalHistory);
            }
            else{
                System.out.println("Movie is unavailable.");
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return "";
    }


    //This method checks the view unavailable_movies (which stores rental_history rows with not return date (currently checked out)
    public boolean movieIsAvailable(int movieId, String format){
        String sql = "SELECT movie_title FROM unavailable_movies WHERE movie_id = " + movieId +
                " AND format ='"+ format + "';";

        String movieFoundTitle = "";

        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            movieFoundTitle = rs.getString("movie_title");
            if(movieFoundTitle.equals("")){
                return true;
            }
            return false;
        }catch (SQLException e){

        }

        return true;
    }

    //returns the entire rental record for a customer
    public void customerRecord(String fullName){
        String sql = "SELECT movie_id, title, rental_date, return_date, fname ||' '|| lname, format FROM " +
                " rental_history JOIN media USING(media_id) JOIN customers using(customer_id) " +
                "JOIN movies ON(title_id = movie_id) WHERE UPPER(fname ||' '|| lname) = '" + fullName.toUpperCase() + "';";

        try(Connection conn = this.connect()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println("********");
                System.out.println("Movie id: " + rs.getString("movie_id"));
                System.out.println("Title: " + rs.getString("title"));
                System.out.println("Format: " + rs.getString("format"));
                System.out.println("Rental Date: " + rs.getString("rental_date"));
                System.out.println("Return Date: " + rs.getString("return_date"));
                System.out.println("********");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    //prints the view that tracks all currently checked out films
    public void printRentedMovie(){

        String sql = "SELECT * FROM unavailable_movies;";

        try(Connection conn = this.connect()){

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println("**************");
                System.out.println("Movie id: " + rs.getInt("movie_id"));
                System.out.println("Title: " + rs.getString("movie_title"));
                System.out.println("Format: " + rs.getString("format"));
                System.out.println("Rental Date: " + rs.getString("rental_date"));
                System.out.println("Customer name: " + rs.getString("ccustomer_name"));
                System.out.println("**************");

            }

        }catch(SQLException e){

            System.out.println(e.getMessage());
        }
    }

    //allows user to search for films containing a specific actor
    public ArrayList<Movie> getMoviesByActor(String actor){
        String sql = "SELECT * FROM movies JOIN star_billings USING(movie_id) JOIN actors USING(actor_id) WHERE UPPER(stage_name) = '" + actor.toUpperCase() + "'";

        ArrayList<Movie> movies = new ArrayList<>();
        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            //looks in the resultSet and sets the values for each field in movie object
            while(rs.next()){
                Movie movie = new Movie();
                movie.setMovie_id(rs.getInt("movie_id"));
                movie.setTitle(rs.getString("title"));
                movie.setDescription(rs.getString("description"));
                movie.setRating(rs.getString("rating"));
                movie.setCategory(rs.getString("category"));
                movie.setRelease_date(rs.getString("release_date"));

                movies.add(movie);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return movies;
    }

    //returns the information of a customer
    public Customer getCustomer(String fullName, String phone){
        Customer customer = new Customer();
        String sql = "SELECT * FROM customers WHERE '" + fullName + "' LIKE fname ||' '|| lname AND '" + phone + "' = phone";
        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("fname"));
                customer.setLastName(rs.getString("lname"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZip(rs.getString("zip"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return customer;
    }

    //queries the customers table, sets the fields of customer object and adds to arraylist of Customer
    public ArrayList<Customer> getCustomers(){
        String sql = "SELECT * FROM customers";

        ArrayList<Customer> customers = new ArrayList<>();
        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                Customer customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setFirstName(rs.getString("fname"));
                customer.setLastName(rs.getString("lname"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setState(rs.getString("state"));
                customer.setZip(rs.getString("zip"));

                customers.add(customer);
            }

        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return customers;
    }

    public boolean deleteCustomer(String fullName, String phone){
       String sql = "DELETE FROM customers WHERE UPPER(fname ||' '|| lname) = '" + fullName.toUpperCase() + "' AND phone = '" + phone +
               "';";

       try(Connection conn = this.connect()){
           Statement stmt = conn.createStatement();
           stmt.execute(sql);
           System.out.println("Record Deleted");
           return true;

       }catch (SQLException e){
           System.out.println(e.getMessage());
           System.out.println("Problem occurred, not deleted.");
       }
        return false;
    }

    public boolean deleteMovie(String title){
        String sql = "DELETE FROM movies WHERE UPPER(title) = '" + title.toUpperCase() + "';";

        try(Connection conn = this.connect()){
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("Movie deleted.");
            return true;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Error occurred movie not deleted. ");
        }
        return false;
    }



}
