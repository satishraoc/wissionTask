package com.satish.wissiontask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = User.class, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public static UserDatabase instance;

    public abstract UserDao userDao();

    private static String TAG = "checkddsafd";

    public static synchronized UserDatabase getInstance(Context context) {
        Log.d(TAG, "getInstance: called");
        if (instance == null) {
            Log.d(TAG, "getInstance: created");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d(TAG, "getInstance: callled");
            new populateDbAsyncTask(instance).execute();
        }
    };

        private static class populateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

            populateDbAsyncTask(UserDatabase db) {
            Log.d(TAG, "populateDbAsyncTask: db");
            userDao = db.userDao();
        }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                Log.d(TAG, "onPreExecute: ");}

            @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: add data");
            userDao.insertUser(new User("satish", "satish.innobiz@gmail.com", "7019680334"));
            userDao.insertUser(new User("Boss", "boss@gmail.com", "7019680334"));
            userDao.insertUser(new User("satish", "satish.innobiz@gmail.com", "7019680334"));

            return null;
        }
    }


}
