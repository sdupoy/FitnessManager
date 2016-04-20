package edu.iit.sat.itmd555.fp.fitnessmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Simon on 4/19/2016.
 */
public class SqlHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "FitnessManagerDB";

    // Users table name
    private static final String TABLE_USERS = "users";
    private static final String TABLE_ACTIVITIES = "activities";
    private static final String TABLE_DISTANCE_ACTIVITIES = "distanceActivities";
    private static final String TABLE_WORKOUT_ACTIVITIES = "workoutActivities";
    private static final String TABLE_GOALS = "goals";

    // Users Table Columns names
    private static final String KEY_ID_USER = "id";
    private static final String KEY_PSWD = "password";
    private static final String KEY_FIRSTNAME = "firstname";
    private static final String KEY_LASTNAME = "lastname";
    private static final String KEY_AGE = "age";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_WEIGHT = "weight";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_METRICS_CHOICE = "metrics";

    // Goals Table Columns names
    private static final String KEY_ID_GOAL = "id";
    private static final String KEY_FK_ID_USER_FOR_GOAL = "idUser";
    private static final String KEY_TARGET_NAME = "targetName";
    private static final String KEY_TARGET_NUMBER = "targetNumber";

    // Activities Table Columns names
    private static final String KEY_ID_ACTIVITY = "id";
    private static final String KEY_FK_ID_USER_FOR_ACTIVITY = "idUser";
    private static final String KEY_DURATION = "duration";
    private static final String KEY_DATE = "date";
    private static final String KEY_FEEDBACK = "feedback";
    private static final String KEY_TYPE = "type";

    // Distance activity Table Columns names
    private static final String KEY_ID_DISTANCE_ACTIVITY = "id";
    private static final String KEY_DISTANCE_KM = "distanceKms";
    private static final String KEY_DISTANCE_MILES = "distanceMiles";
    private static final String KEY_FK_ID_ACTIVITY_FOR_DISTANCE = "idActivity"; // This is the same as in ActivityWorkout but it's clearer defined this way

    // ActivityWorkout activity Table Columns names
    private static final String KEY_ID_WORKOUT_ACTIVITY = "id";
    private static final String KEY_REP_TYPE = "repetitionType";
    private static final String KEY_REP_NUMBER = "repetitionNumber";
    private static final String KEY_FK_ID_ACTIVITY_FOR_WORKOUT = "idActivity";

    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create users table
        String CREATE_USERS_TABLE = "CREATE TABLE users ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "password TEXT NOT NULL, " +
                "firstname TEXT NOT NULL, "+
                "lastname TEXT NOT NULL, "+
                "age INTEGER NOT NULL, "+
                "height TEXT NOT NULL, "+
                "weight TEXT NOT NULL, "+
                "email TEXT NOT NULL, "+
                "gender TEXT NOT NULL, "+
                "metrics TEXT NOT NULL);";

        String CREATE_GOALS_TABLE = "CREATE TABLE goals ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "targetName TEXT NOT NULL, " +
                "targetNumber INTEGER NOT NULL, " +
                "idUser INTEGER NOT NULL, " +
                "FOREIGN KEY("+ KEY_FK_ID_USER_FOR_GOAL +") REFERENCES "+ TABLE_USERS +"("+KEY_ID_USER+"));";


        String CREATE_ACTIVITIES_TABLE = "CREATE TABLE activities ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "duration TEXT NOT NULL, "+
                "date TEXT NOT NULL, "+
                "feedback TEXT, "+
                "idUser INTEGER NOT NULL," +
                "FOREIGN KEY ("+ KEY_FK_ID_USER_FOR_ACTIVITY +") REFERENCES "+ TABLE_USERS + "("+KEY_ID_USER+"));";

        String CREATE_DISTANCE_ACTIVITIES_TABLE = "CREATE TABLE distanceActivities ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "distanceKM REAL, "+
                "distanceMiles REAL, "+
                "idActivity INTEGER NOT NULL," +
                "FOREIGN KEY ("+ KEY_FK_ID_ACTIVITY_FOR_DISTANCE +") REFERENCES "+ TABLE_ACTIVITIES + "("+KEY_ID_ACTIVITY+"));";

        String CREATE_WORKOUT_ACTIVITIES_TABLE = "CREATE TABLE workoutActivities ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "repetitionType TEXT, "+
                "repetitionNumber NUMBER, "+
                "idActivity INTEGER NOT NULL," +
                "FOREIGN KEY ("+ KEY_FK_ID_ACTIVITY_FOR_WORKOUT +") REFERENCES "+ TABLE_ACTIVITIES + "("+KEY_ID_ACTIVITY+"));";

        // create users table
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_GOALS_TABLE);
        db.execSQL(CREATE_ACTIVITIES_TABLE);
        db.execSQL(CREATE_DISTANCE_ACTIVITIES_TABLE);
        db.execSQL(CREATE_WORKOUT_ACTIVITIES_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if existed
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS goals");
        db.execSQL("DROP TABLE IF EXISTS activities");
        db.execSQL("DROP TABLE IF EXISTS distanceActivities");
        db.execSQL("DROP TABLE IF EXISTS workoutActivities");

        // create fresh tables
        this.onCreate(db);
    }

    // ------------ CRUD on USER ------------- //
    // Add a single user
    public void addUser(User user){
        Log.d("adding user", user.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID_USER, user.getId()); // get id
        values.put(KEY_PSWD, user.getPassword()); // get password
        values.put(KEY_FIRSTNAME, user.getFirstname()); // get firstname
        values.put(KEY_LASTNAME, user.getLastname()); // get lastname
        values.put(KEY_AGE, user.getAge()); // get age
        values.put(KEY_HEIGHT, user.getHeight()); // get height
        values.put(KEY_WEIGHT, user.getWeight()); // get weight
        values.put(KEY_EMAIL, user.getEmail()); // get email
        values.put(KEY_GENDER, user.getGender()); // get gender
        values.put(KEY_METRICS_CHOICE, user.getMetrics()); // get metrics choice

        // 3. insert
        db.insert(TABLE_USERS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }



    // Get a single user
    public User getUser(int id){
        User retrievedUser = new User();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_ID_USER + " = " + id +");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
                retrievedUser.setId(Integer.parseInt(cursor.getString(0)));
                retrievedUser.setPassword(cursor.getString(1));
                retrievedUser.setFirstname(cursor.getString(2));
                retrievedUser.setLastname(cursor.getString(3));
                retrievedUser.setAge(Integer.parseInt(cursor.getString(4)));
                retrievedUser.setHeight(cursor.getString(5));
                retrievedUser.setWeight(cursor.getString(6));
                retrievedUser.setEmail(cursor.getString(7));
                retrievedUser.setGender(cursor.getString(8));
                retrievedUser.setMetrics(Integer.parseInt(cursor.getString(9)));
        }
        Log.d("Retrieved user", retrievedUser.toString());
        return retrievedUser; // return the user
    }



    // Updating single user
    public int updateUser(User user, String newFirstName, String newLastName, int newAge, String newHeight, String newWeight, String newEmail, String newGender, String newMetricsChoice) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("firstName", newFirstName); // firstName
        values.put("lastName", newLastName); // lastName
        values.put("age", newAge); // age
        values.put("height", newHeight); // height
        values.put("weight", newWeight); // weight
        values.put("email", newEmail); // email
        values.put("gender", newGender); // gender
        values.put("metrics", newMetricsChoice); // metrics

        // 3. updating row
        int i = db.update(TABLE_USERS, //table
                values, // column/value
                KEY_ID_USER+" = ?", // selections
                new String[] { String.valueOf(user.getId()) }); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating user ", user.toString());
        return i;

    }

    // Deleting single user
    public void deleteUser(User user) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_USERS,
                KEY_ID_USER+" = ?",
                new String[] { String.valueOf(user.getId()) });

        // 3. close
        db.close();

        Log.d("deleting user ", user.toString());
    }


    // ------------ CRUD on ACTIVITY ------------- //
    // Add a single activity
    public void addActivity(Activity activity){
        Log.d("adding activity", activity.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID_ACTIVITY, activity.getId()); // get id
        values.put(KEY_FK_ID_USER_FOR_ACTIVITY, activity.getIdUser()); // get id user
        values.put(KEY_DURATION, activity.getDuration()); // get duration
        values.put(KEY_DATE, activity.getDate()); // get date
        values.put(KEY_FEEDBACK, activity.getFeedback()); // get feedback
        values.put(KEY_TYPE, activity.getType()); // get type

        // 3. insert
        db.insert(TABLE_ACTIVITIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }



    // Get a single activity
    public Activity getActivity(int id){
        Activity retrievedActivity = new Activity();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_ID_ACTIVITY + " = " + id +");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
            retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(1)));
            retrievedActivity.setDuration(cursor.getString(2));
            retrievedActivity.setDate(cursor.getString(3));
            retrievedActivity.setFeedback(cursor.getString(3));
            retrievedActivity.setType(cursor.getString(3));
        }
        Log.d("Retrieved activity", retrievedActivity.toString());
        return retrievedActivity; // return the activity
    }



    // Updating single activity
    public int updateActivity(Activity activity, String newDuration, String newDate, String newFeedback, String newType) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("duration", newDuration); // duration
        values.put("date", newDate); // date
        values.put("feedback", newFeedback); // feedback
        values.put("type", newType); // type

        // 3. updating row
        int i = db.update(TABLE_ACTIVITIES, //table
                values, // column/value
                KEY_ID_ACTIVITY+" = ?", // selections
                new String[] { String.valueOf(activity.getId()) }); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating activity ", activity.toString());
        return i;

    }

    // Deleting single activity
    public void deleteActivity(Activity activity) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_ACTIVITIES,
                KEY_ID_ACTIVITY+" = ?",
                new String[] { String.valueOf(activity.getId()) });

        // 3. close
        db.close();

        Log.d("deleting activity ", activity.toString());
    }


    /*CRUD operations (create "add", read "get", update, delete) */
/*
    public void addBook(Book book){
        Log.d("addBook", book.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, book.getTitle()); // get title
        values.put(KEY_AUTHOR, book.getAuthor()); // get author
        values.put(KEY_RATING, book.getRating()); // get rating
        values.put(KEY_COVER, book.getCover()); // get cover

        // 3. insert
        db.insert(TABLE_BOOKS, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get All Books
    public List<Book> getAllBooks() {
        List<Book> books = new LinkedList<Book>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_BOOKS;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Book book = null;
        if (cursor.moveToFirst()) {
            do {
                book = new Book();
                book.setId(Integer.parseInt(cursor.getString(0)));
                book.setTitle(cursor.getString(1));
                book.setAuthor(cursor.getString(2));
                book.setRating(cursor.getString(3));
                book.setCover(cursor.getString(4));
                // Add book to books
                books.add(book);
            } while (cursor.moveToNext());
        }

        Log.d("getAllBooks()", books.toString());

        return books; // return books
    }

    // Updating single book
    public int updateBook(Book book, String newTitle, String newAuthor) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("title", newTitle); // title
        values.put("author", newAuthor); // author

        // 3. updating row
        int i = db.update(TABLE_BOOKS, //table
                values, // column/value
                KEY_ID+" = ?", // selections
                new String[] { String.valueOf(book.getId()) }); //selection args
        // 4. close dbase
        db.close();
        Log.d("Simon Dupoy: UpdateBook", book.toString());
        return i;

    }

    // Deleting single book
    public void deleteBook(Book book) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_BOOKS,
                KEY_ID+" = ?",
                new String[] { String.valueOf(book.getId()) });

        // 3. close
        db.close();

        Log.d("Simon Dupoy: deleteBook", book.toString());
    }

    public int getIds(Book book)
    {
        String selectQuery = "SELECT id FROM books";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        Log.d("Number of entries:", String.valueOf(total));
        return total;
    }

    // Get the number of records in database
    public int getNumberOfRecords(){
        String selectQuery = "SELECT id FROM books";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor c = database.rawQuery(selectQuery, null);
        c.moveToFirst();
        int total = c.getCount();
        Log.d("Number of records:", String.valueOf(total));
        return total;
    }

    // Get the highest ranked title(s)
    public List<String> getHighestRankedTitles(){
        List<String> highestTitles = new LinkedList<String>();

        // 1. build the query
        String query = "SELECT " + KEY_TITLE + " FROM " + TABLE_BOOKS + " WHERE " + KEY_RATING + " = (SELECT MAX(" + KEY_RATING + ") FROM " + TABLE_BOOKS +");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row and add the title to list
        String highTitle = null;
        if (cursor.moveToFirst()) {
            do {
                highTitle = new String();
                highTitle = cursor.getString(0);
                highestTitles.add(highTitle);
            } while (cursor.moveToNext());
        }

        Log.d("getHighestRankedTitle()", highestTitles.toString());

        return highestTitles; // return highest book titles
    }

    public List<String> getLowestRankedTitles(){
        List<String> lowestTitles = new LinkedList<String>();

        // 1. build the query
        String query = "SELECT " + KEY_TITLE + " FROM " + TABLE_BOOKS + " WHERE " + KEY_RATING + "= (SELECT MIN("+KEY_RATING+") FROM " + TABLE_BOOKS +");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row and add the title to list
        String lowTitle = null;
        if (cursor.moveToFirst()) {
            do {
                lowTitle = new String();
                lowTitle = cursor.getString(0);
                lowestTitles.add(lowTitle);
            } while (cursor.moveToNext());
        }

        Log.d("getLowestRankedTitles()", lowestTitles.toString());

        return lowestTitles; // return lowest book titles
    }

    public List<String> getSpecificTitles(){
        List<String> bookTitles = new LinkedList<String>();

        // 1. build the query
        String titleToRetrieve = "Android 4";
        String query = "SELECT " + KEY_TITLE + " FROM " + TABLE_BOOKS + " WHERE " + KEY_TITLE + " LIKE '%"+ titleToRetrieve +"%';";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row and add title to list
        String bookTitle = null;
        if (cursor.moveToFirst()) {
            do {
                bookTitle = new String();
                bookTitle = cursor.getString(0);
                bookTitles.add(bookTitle);
            } while (cursor.moveToNext());
        }

        Log.d("getSpecificTitles()", bookTitles.toString());

        return bookTitles; // return specific book titles
    }
*/
}

