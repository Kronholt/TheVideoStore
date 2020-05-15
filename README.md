# TheVideoStore

This program tested and improved my knowledge of SQL databases. 
I originally designed and developed a video store database for a college class, but did not learn how to interact with a DB from java.

Work on the Database:
The database was built in oracle sql for my class, but wanted I to work with sqlite3. 
I transferred the knowledge of concepts of built the database from the ground up, using resources like w3schools and sqlitetutorial to learn the syntax of sqlite3. 
I learned how to implement tables, views, and constraints in sqlite3.


Work on the Java program:
Using stackoverflow and similar resources I was able to learn how to execute sql statements in java and interact with the database. 

The biggest challenge was managing how movies were rented and returned (checking that a movie was not already checked out before allowing rental). 
This was achieved by creating a view that only included rental records for customers that had a null value for return date.
The user is unable to rent movies contained in the unavailable_movies view. 

I encountered bugs along the way, but kept these to a minimum and fixed problems quickly with frequent testing and debugging. 
