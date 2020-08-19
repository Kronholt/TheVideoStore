# TheVideoStore

This program tested and grew my knowledge of SQL databases and Java. 
I originally designed and developed a video store database in oracle SQL for a college class. I took the knowledge gained from class to rebuild the database in sqlite3. I then added a frontend program in Java to interact with the movie database. 

Work on the Database:

The database was built in oracle sql for my class, but wanted I to work with sqlite3. 
I transferred the knowledge of concepts of built the database from the ground up, using resources like w3schools and sqlitetutorial to learn the syntax of sqlite3. 
I implemented tables, views, and constraints in sqlite3.


Work on the Java program:

Using stackoverflow and similar resources I learned how to execute prepared sql statements in java to interact with the database. 

I implemented a menu system and methods to send/receive information to/from the database class so that CRUD operations could be performed. 

The biggest challenge was managing how movies were rented and returned (checking that a movie was not already checked out before allowing rental). 
This was achieved by creating a view that only included rental records for customers that had a null value for return date.
The user is unable to rent movies contained in the unavailable_movies view. 

I encountered bugs along the way, but kept these to a minimum and fixed problems quickly with frequent testing and debugging. 
