package com.company;


import java.util.ArrayList;
import java.util.Scanner;





public class Main {


    static Scanner s = new Scanner(System.in);
    static Database db = new Database();


    public static void main(String[] args) {

        System.out.println("Welcome to TheVideoStore!");


        boolean quit = false;

        while(!quit){
            printMenu();
            String choice = s.nextLine();
            switch(choice){
                case "1":
                    System.out.println("Enter movie number: ");
                    int movieNumber = s.nextInt();
                    s.nextLine();
                    System.out.println("Enter movie type (DVD/VHS): ");
                    String type = s.nextLine();
                    System.out.println("Enter customers full name: ");
                    String name = s.nextLine();
                    System.out.println("Enter phone number: ");
                    String phone = s.nextLine();
                    System.out.println("Enter date: ");
                    String date = s.nextLine();

                    int customer_id = getCustomerId(name, phone);

                    //ended here - working on getting current date
                    rentMovie(movieNumber, type, customer_id, date);

                    break;

                case "2":
                    System.out.println("Enter movie number: ");
                    int movieNum = s.nextInt();
                    s.nextLine();
                    System.out.println("DVD or VHS? ");
                    type = s.nextLine();
                    System.out.println("Enter date: ");
                    date = s.nextLine();
                    db.returnMovie(movieNum, type, date);
                    break;

                case "3":
                    System.out.println("Enter movie title: ");
                    String title = s.nextLine();
                    printMovieByTitle(title);
                    break;

                case "4":
                    System.out.println("Enter actor name: ");
                    String actorName = s.nextLine();
                    printMoviesByActor(actorName);
                    break;

                case "5":
                    System.out.println("Enter Genre of movie (Action, Animation, Drama): ");
                    String genre = s.nextLine();
                    printMoviesByGenre((genre));

                    break;

                case "6":
                    System.out.println("Please enter customer's full name: ");
                    name = s.nextLine();
                    System.out.println("Please enter customer's phone number: ");
                    phone = s.nextLine();
                    searchCustomer(name, phone);
                    break;

                case "7":
                    newCustomer();
                    break;

                case "8":
                    newMovie();
                    break;

                case "9":
                    db.printRentedMovie();

                break;

                case "10":
                    System.out.println("Enter Customer's full name: ");
                    String fullName = s.nextLine();
                    db.customerRecord(fullName);
                    break;
                case "11":
                    printMovies();
                    break;
                case "12":
                    System.out.println("Enter Customer full name: ");
                    fullName = s.nextLine();
                    System.out.println("Enter Customer's phone number: ");
                    phone = s.nextLine();
                    db.deleteCustomer(fullName, phone);
                    break;

                case "13":
                    System.out.println("Enter movie title to delete: ");
                    title = s.nextLine();
                    db.deleteMovie(title);
                    break;

                case "0":
                    quit = true;
                    break;

            default:
                System.out.println("Invalid choice please try again.");

        }



       }


    }

    public static void printMenu(){
        System.out.println("------Type 0 to exit------");
        System.out.println("1. Rent Movie");
        System.out.println("2. Return Movie");
        System.out.println("3. Look up movie by title");
        System.out.println("4. Look up movies by actor");
        System.out.println("5. Look up movies by Genre");
        System.out.println("6. Look up customer");
        System.out.println("7. New Customer");
        System.out.println("8. Add new movie");
        System.out.println("9. Print checked out movies movies");
        System.out.println("10. Print customer rental record");
        System.out.println("11. Print list of movies");
        System.out.println("12. Delete Customer. ");
        System.out.println("13. Delete movie. ");
        System.out.println("Please enter choice: ");
    }




    //method receives an arrayList of type Customer from the Database class and uses foreach loop to print to screen
    public static void printCustomers(){
        ArrayList<Customer> customerList = db.getCustomers();
        for(Customer c: customerList){
            System.out.println(c);
        }
    }

    public static void rentMovie(int movieID, String type, int customerId, String rentalDate){
        db.rentMovie(movieID, type, customerId, rentalDate);
    }

    public static void printMovieByTitle(String title){
        Movie movie = db.getMovie(title);
        System.out.println(movie);
    }


    public static void printMoviesByActor(String stageName){
        ArrayList<Movie> movieList = db.getMoviesByActor(stageName);
        for(Movie m: movieList){
            System.out.println(m);
        }
    }

    //receives arrayList of type Movie and prints to screen using foreach loop
    public static void printMovies(){
        ArrayList<Movie> movieList = db.getMovies();
        for(Movie m: movieList){
            System.out.println(m);
        }
    }

    public static void printMoviesByGenre(String genre){
        ArrayList<Movie> movieList = db.getMoviesByGenre(genre);
        for(Movie m: movieList){
            System.out.println(m);
        }
    }

    public static int searchCustomer(String name, String phone){

        Customer customer = db.getCustomer(name, phone);
        System.out.println(customer);
        return customer.getCustomerId();


    }

    public static int getCustomerId(String name, String phone){

        Customer customer = db.getCustomer(name, phone);
        return customer.getCustomerId();


    }

    //allows the user to add a new movie to the database if it does not already exist.
    public static void newMovie(){
        System.out.println("Enter movie title: ");
        String title = s.nextLine();
        System.out.println("Enter description: ");
        String description = s.nextLine();
        System.out.println("Enter rating (G/PG/PG-13/R): ");
        String rating = s.nextLine();
        System.out.println("Enter category (Action/Drama/Animation): ");
        String category = s.nextLine();
        System.out.println("Enter release date: ");
        String release_date = s.nextLine();
        System.out.println("Is the format DVD, VHS, or both?: ");
        String type = s.nextLine();

        if(db.addMovie(title,description,rating,category,release_date, type)){
            createStarBillings(title);

        }

    }

    //used by the newMovie method to add actors that starred in the new film entered
    public static void newActor(){
        Actor actor = new Actor();
        System.out.println("Enter stage name: ");
        String stageName = s.nextLine();
        System.out.println("Enter first name: ");
        String fname = s.nextLine();
        System.out.println("Enter last name: ");
        String lname = s.nextLine();
        System.out.println("Enter birthdate: ");
        String birthDate = s.nextLine();
        db.addActor(stageName, fname,lname,birthDate);
    }

    //is called when an actor is added to a movie, this tracks which actors are in which films.
    public static void createStarBillings(String title){
        boolean quit = false;
        while(!quit){
            System.out.println("Add actors that starred in this film.");
            System.out.println("Enter exit to stop entering actor names.");
            System.out.println("Enter actor name: ");
            String stageName = s.nextLine();
            if(stageName.equals("exit")){
                quit = true;

            }
            if (db.getActor(stageName).getFname() == null && !quit){
                System.out.println("Actor not found, please enter information.");
                newActor();
            }
            db.addStar_billings(title, stageName);


        }
    }

    //Method used to enter a new customer into the database
    public static void newCustomer(){
        Customer customer = new Customer();
        System.out.println("-- Enter user information --");
        System.out.println("First Name: ");
        String fname = s.nextLine();
        System.out.println("Last Name: ");
        String lname = s.nextLine();
        System.out.println("Phone: ");
        String phone = s.nextLine();
        System.out.println("Email Address: ");
        String email = s.nextLine();
        System.out.println("Street Address: ");
        String address = s.nextLine();
        System.out.println("City: ");
        String city = s.nextLine();
        System.out.println("State: ");
        String state = s.nextLine();
        System.out.println("Zip code: ");
        String zip = s.nextLine();

        db.addCustomer(fname, lname, phone, email, address, city, state, zip);
    }









}
