package com.company;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;


class Database {

    //used to establish connection to the movie_store_db file using the jdbc driver
    //This method is used by other methods of the Database class to perform CRUD operations to the movie database
    private Connection connect(){
        Connection conn = null;
        String url = "jdbc:sqlite:/Users/keganronholt/movie_store.db";
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
        String sql = "SELECT * FROM movies WHERE title = '" + title + "'";
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

}
