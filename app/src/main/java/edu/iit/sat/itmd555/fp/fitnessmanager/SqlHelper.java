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

    // DistanceActivity Table Columns names
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
                "firstname TEXT NOT NULL, " +
                "lastname TEXT NOT NULL, " +
                "age INTEGER NOT NULL, " +
                "height TEXT NOT NULL, " +
                "weight TEXT NOT NULL, " +
                "email TEXT NOT NULL, " +
                "gender TEXT NOT NULL, " +
                "metrics TEXT NOT NULL);";

        String CREATE_GOALS_TABLE = "CREATE TABLE goals ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "targetName TEXT NOT NULL, " +
                "targetNumber INTEGER NOT NULL, " +
                "idUser INTEGER NOT NULL, " +
                "FOREIGN KEY(" + KEY_FK_ID_USER_FOR_GOAL + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID_USER + "));";


        String CREATE_ACTIVITIES_TABLE = "CREATE TABLE activities ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "duration TEXT NOT NULL, " +
                "date TEXT NOT NULL, " +
                "feedback TEXT, " +
                "idUser INTEGER NOT NULL," +
                "FOREIGN KEY (" + KEY_FK_ID_USER_FOR_ACTIVITY + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID_USER + "));";

        String CREATE_DISTANCE_ACTIVITIES_TABLE = "CREATE TABLE distanceActivities ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "distanceKM REAL, " +
                "distanceMiles REAL, " +
                "idActivity INTEGER NOT NULL," +
                "FOREIGN KEY (" + KEY_FK_ID_ACTIVITY_FOR_DISTANCE + ") REFERENCES " + TABLE_ACTIVITIES + "(" + KEY_ID_ACTIVITY + "));";

        String CREATE_WORKOUT_ACTIVITIES_TABLE = "CREATE TABLE workoutActivities ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "repetitionType TEXT, " +
                "repetitionNumber NUMBER, " +
                "idActivity INTEGER NOT NULL," +
                "FOREIGN KEY (" + KEY_FK_ID_ACTIVITY_FOR_WORKOUT + ") REFERENCES " + TABLE_ACTIVITIES + "(" + KEY_ID_ACTIVITY + "));";

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
    public void addUser(User user) {
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
    public User getUser(int id) {
        User retrievedUser = new User();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_ID_USER + " = " + id + ");";

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
                KEY_ID_USER + " = ?", // selections
                new String[]{String.valueOf(user.getId())}); //selection args
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
                KEY_ID_USER + " = ?",
                new String[]{String.valueOf(user.getId())});

        // 3. close
        db.close();

        Log.d("deleting user ", user.toString());
    }

    // ------------ CRUD on GOAL ------------- //
    // Add a single goal
    public void addGoal(Goal goal) {
        Log.d("adding goal", goal.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID_GOAL, goal.getId()); // get id
        values.put(KEY_FK_ID_USER_FOR_GOAL, goal.getIdUser()); // get id user
        values.put(KEY_TARGET_NAME, goal.getTargetName()); // get target name
        values.put(KEY_TARGET_NUMBER, goal.getTargetNumber()); // get target number

        // 3. insert
        db.insert(TABLE_ACTIVITIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get a single goal
    public Goal getGoal(int id) {
        Goal retrievedGoal = new Goal();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_GOALS + " WHERE " + KEY_ID_GOAL + " = " + id + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedGoal.setId(Integer.parseInt(cursor.getString(0)));
            retrievedGoal.setIdUser(Integer.parseInt(cursor.getString(1)));
            retrievedGoal.setTargetName(cursor.getString(2));
            retrievedGoal.setTargetNumber(Integer.parseInt(cursor.getString(3)));
        }
        Log.d("Retrieved goal", retrievedGoal.toString());
        return retrievedGoal; // return the activity
    }

    // Updating single goal
    public int updateGoal(Goal goal, String newTargetName, int newTargetNumber) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("targetName", newTargetName); // target name
        values.put("targetNumber", newTargetNumber); // target number

        // 3. updating row
        int i = db.update(TABLE_GOALS, //table
                values, // column/value
                KEY_ID_GOAL + " = ?", // selections
                new String[]{String.valueOf(goal.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating goal ", goal.toString());
        return i;

    }

    // Deleting single goal
    public void deleteGoal(Goal goal) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_GOALS,
                KEY_ID_GOAL + " = ?",
                new String[]{String.valueOf(goal.getId())});

        // 3. close
        db.close();

        Log.d("deleting goal ", goal.toString());
    }


    // ------------ CRUD on ACTIVITY ------------- //
    // Add a single activity
    public void addActivity(Activity activity) {
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
    public Activity getActivity(int id) {
        Activity retrievedActivity = new Activity();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_ID_ACTIVITY + " = " + id + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
            retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(1)));
            retrievedActivity.setDuration(cursor.getString(2));
            retrievedActivity.setDate(cursor.getString(3));
            retrievedActivity.setFeedback(cursor.getString(4));
            retrievedActivity.setType(cursor.getString(5));
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
                KEY_ID_ACTIVITY + " = ?", // selections
                new String[]{String.valueOf(activity.getId())}); //selection args
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
                KEY_ID_ACTIVITY + " = ?",
                new String[]{String.valueOf(activity.getId())});

        // 3. close
        db.close();

        Log.d("deleting activity ", activity.toString());
    }

    // ------------ CRUD on DISTANCEACTIVITY ------------- //
    // Add a single distance activity
    public void addActivityDistance(ActivityDistance activityDistance) {
        Log.d("adding distanceactivity", activityDistance.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID_DISTANCE_ACTIVITY, activityDistance.getId()); // get id
        values.put(KEY_FK_ID_ACTIVITY_FOR_DISTANCE, activityDistance.getIdActivity()); // get id activity
        values.put(KEY_DISTANCE_MILES, activityDistance.getDistanceMiles()); // get distance miles
        values.put(KEY_DISTANCE_KM, activityDistance.getDistanceKms()); // get distance kms

        // 3. insert
        db.insert(TABLE_DISTANCE_ACTIVITIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get a single distance activity
    public ActivityDistance getActivityDistance(int id) {
        ActivityDistance retrievedDistanceActivity = new ActivityDistance();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_DISTANCE_ACTIVITIES + " WHERE " + KEY_ID_DISTANCE_ACTIVITY + " = " + id + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedDistanceActivity.setId(Integer.parseInt(cursor.getString(0)));
            retrievedDistanceActivity.setIdActivity(Integer.parseInt(cursor.getString(1)));
            retrievedDistanceActivity.setDistanceMiles(cursor.getString(2));
            retrievedDistanceActivity.setDistanceKms(cursor.getString(3));
        }
        Log.d("Retrieved distactivity", retrievedDistanceActivity.toString());
        return retrievedDistanceActivity; // return the activity
    }

    // Updating single distance activity
    public int updateDistanceActivity(ActivityDistance activityDistance, String newDistanceMiles, String newDistanceKms) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("distanceMiles", newDistanceMiles); // distance miles
        values.put("distanceKms", newDistanceKms); // distance kms

        // 3. updating row
        int i = db.update(TABLE_DISTANCE_ACTIVITIES, //table
                values, // column/value
                KEY_ID_DISTANCE_ACTIVITY + " = ?", // selections
                new String[]{String.valueOf(activityDistance.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating act dist ", activityDistance.toString());
        return i;

    }

    // Deleting single distance activity
    public void deleteDistanceActivity(ActivityDistance activityDistance) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_DISTANCE_ACTIVITIES,
                KEY_ID_DISTANCE_ACTIVITY + " = ?",
                new String[]{String.valueOf(activityDistance.getId())});

        // 3. close
        db.close();

        Log.d("deleting act distance ", activityDistance.toString());
    }

    // ------------ CRUD on WORKOUT ACTIVITY ------------- //
    // Add a single workout activity
    public void addActivityWorkout(ActivityWorkout activityWorkout) {
        Log.d("adding Workoutactivity", activityWorkout.toString());
        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_ID_DISTANCE_ACTIVITY, activityWorkout.getId()); // get id
        values.put(KEY_FK_ID_ACTIVITY_FOR_WORKOUT, activityWorkout.getIdActivity()); // get id activity
        values.put(KEY_REP_TYPE, activityWorkout.getTypeOfRep()); // get repetition type
        values.put(KEY_REP_NUMBER, activityWorkout.getNbOfRep()); // get repetition number

        // 3. insert
        db.insert(TABLE_WORKOUT_ACTIVITIES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/values

        // 4. Close dbase
        db.close();
    }

    // Get a single workout activity
    public ActivityWorkout getActivityWorkout(int id) {
        ActivityWorkout retrievedWorkoutActivity = new ActivityWorkout();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_WORKOUT_ACTIVITIES + " WHERE " + KEY_ID_WORKOUT_ACTIVITY + " = " + id + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            retrievedWorkoutActivity.setId(Integer.parseInt(cursor.getString(0)));
            retrievedWorkoutActivity.setIdActivity(Integer.parseInt(cursor.getString(1)));
            retrievedWorkoutActivity.setTypeOfRep(cursor.getString(2));
            retrievedWorkoutActivity.setNbOfRep(Integer.parseInt(cursor.getString(3)));
        }
        Log.d("Retrieved wkoutactivity", retrievedWorkoutActivity.toString());
        return retrievedWorkoutActivity; // return the activity
    }

    // Updating single workout activity
    public int updateWorkoutActivity(ActivityWorkout activityWorkout, String newTypeOfReps, int newNbOfReps) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("repetitionType", newTypeOfReps); // repetition type
        values.put("repetitionNumber", newNbOfReps); // repetition number

        // 3. updating row
        int i = db.update(TABLE_DISTANCE_ACTIVITIES, //table
                values, // column/value
                KEY_ID_WORKOUT_ACTIVITY + " = ?", // selections
                new String[]{String.valueOf(activityWorkout.getId())}); //selection args
        // 4. close dbase
        db.close();
        Log.d("Updating wkout dist ", activityWorkout.toString());
        return i;

    }

    // Deleting single workout activity
    public void deleteWorkoutActivity(ActivityWorkout activityWorkout) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. delete
        db.delete(TABLE_WORKOUT_ACTIVITIES,
                KEY_ID_WORKOUT_ACTIVITY + " = ?",
                new String[]{String.valueOf(activityWorkout.getId())});

        // 3. close
        db.close();

        Log.d("deleting wkout act ", activityWorkout.toString());
    }


    // ------------ SPECIAL QUERIES ON USER ---------------- //
    public User getUserByEmail(String email) {
        User user = new User();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_EMAIL + " = " + email + ";";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row and add title to list
        String bookTitle = null;
        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setPassword(cursor.getString(1));
            user.setFirstname(cursor.getString(2));
            user.setLastname(cursor.getString(3));
            user.setAge(Integer.parseInt(cursor.getString(4)));
            user.setHeight(cursor.getString(5));
            user.setWeight(cursor.getString(6));
            user.setEmail(cursor.getString(7));
            user.setGender(cursor.getString(8));
            user.setMetrics(Integer.parseInt(cursor.getString(9)));
        }
        Log.d("Retrieved user", user.toString());
        return user; // return the user
    }

    // ------------ SPECIAL QUERIES ON ACTIVITY ---------------- //
    public List<Activity> getAllByUser(User user) {
        List<Activity> activities = new LinkedList<Activity>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_FK_ID_USER_FOR_ACTIVITY + " = " + user.getId() + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Activity retrievedActivity = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            do {
                retrievedActivity = new Activity();
                retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
                retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(1)));
                retrievedActivity.setDuration(cursor.getString(2));
                retrievedActivity.setDate(cursor.getString(3));
                retrievedActivity.setFeedback(cursor.getString(4));
                retrievedActivity.setType(cursor.getString(5));
                // Add activity to activities
                activities.add(retrievedActivity);
            } while (cursor.moveToNext());
        }

        Log.d("getAllActByUser", activities.toString());

        return activities; // return activities by user
    }

    public List<Activity> getAllByType(String type) {
        List<Activity> activities = new LinkedList<Activity>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_TYPE + " = " + type + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Activity retrievedActivity = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            do {
                retrievedActivity = new Activity();
                retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
                retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(1)));
                retrievedActivity.setDuration(cursor.getString(2));
                retrievedActivity.setDate(cursor.getString(3));
                retrievedActivity.setFeedback(cursor.getString(4));
                retrievedActivity.setType(cursor.getString(5));
                // Add activity to activities
                activities.add(retrievedActivity);
            } while (cursor.moveToNext());
        }

        Log.d("getAllActByUser", activities.toString());
        return activities; // return activities by type
    }

    public List<Activity> getAllByDate(String date) {
        List<Activity> activities = new LinkedList<Activity>();

        // 1. build the query
        String query = "SELECT * FROM " + TABLE_ACTIVITIES + " WHERE " + KEY_DATE + " = " + date + ");";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Activity retrievedActivity = null;

        // 3. assign the result to the variable to return
        if (cursor.moveToFirst()) {
            do {
                retrievedActivity = new Activity();
                retrievedActivity.setId(Integer.parseInt(cursor.getString(0)));
                retrievedActivity.setIdUser(Integer.parseInt(cursor.getString(1)));
                retrievedActivity.setDuration(cursor.getString(2));
                retrievedActivity.setDate(cursor.getString(3));
                retrievedActivity.setFeedback(cursor.getString(4));
                retrievedActivity.setType(cursor.getString(5));
                // Add activity to activities
                activities.add(retrievedActivity);
            } while (cursor.moveToNext());
        }

        Log.d("getAllActByUser", activities.toString());
        return activities; // return activities by date
    }

    // ------------ SPECIAL QUERIES ON GOAL ---------------- //
    public List<Goal> getAllGoalsByUser(User user) {
        List<Goal> userGoals = new LinkedList<Goal>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_GOALS + " WHERE " + KEY_FK_ID_USER_FOR_GOAL + " = " + user.getId();

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build goal and add it to list
        Goal goal = null;
        if (cursor.moveToFirst()) {
            do {
                goal = new Goal();
                goal.setId(Integer.parseInt(cursor.getString(0)));
                goal.setIdUser(Integer.parseInt(cursor.getString(1)));
                goal.setTargetName(cursor.getString(2));
                goal.setTargetNumber(Integer.parseInt(cursor.getString(3)));

                // Add goal to user's goals
                userGoals.add(goal);
            } while (cursor.moveToNext());
        }

        Log.d("getAllGoalsByUser", userGoals.toString());

        return userGoals; // return user's goals
    }

    // ------------ SPECIAL QUERIES ON WORKOUT ACTIVITY ---------------- //
    public List<ActivityWorkout> getWorkoutsByActivity(Activity activity) {
        List<ActivityWorkout> workouts = new LinkedList<ActivityWorkout>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_WORKOUT_ACTIVITIES + " WHERE " + KEY_FK_ID_ACTIVITY_FOR_WORKOUT + " = " + activity.getId();

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build workout and add it to list
        ActivityWorkout workout = null;
        if (cursor.moveToFirst()) {
            do {
                workout = new ActivityWorkout();
                workout.setId(Integer.parseInt(cursor.getString(0)));
                workout.setIdActivity(Integer.parseInt(cursor.getString(1)));
                workout.setTypeOfRep(cursor.getString(2));
                workout.setNbOfRep(Integer.parseInt(cursor.getString(3)));

                // Add goal to workouts's activity
                workouts.add(workout);
            } while (cursor.moveToNext());
        }

        Log.d("getWorkoutsByActivity", workouts.toString());

        return workouts; // return workouts's activity
    }

    // ------------ SPECIAL QUERIES ON DISTANCE ACTIVITY---------------- //
    public List<ActivityDistance> getDistancesByActivity(Activity activity) {
        List<ActivityDistance> distances = new LinkedList<ActivityDistance>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_DISTANCE_ACTIVITIES + " WHERE " + KEY_FK_ID_ACTIVITY_FOR_DISTANCE + " = " + activity.getId();

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build distance and add it to list
        ActivityDistance distance = null;
        if (cursor.moveToFirst()) {
            do {
                distance = new ActivityDistance();
                distance.setId(Integer.parseInt(cursor.getString(0)));
                distance.setIdActivity(Integer.parseInt(cursor.getString(1)));
                distance.setDistanceMiles(cursor.getString(2));
                distance.setDistanceKms(cursor.getString(3));

                // Add distance to distances's activity
                distances.add(distance);
            } while (cursor.moveToNext());
        }

        Log.d("getDistancesByActivity", distances.toString());

        return distances; // return distances's activity
    }

}

