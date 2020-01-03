package com.satish.wissiontask.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.satish.wissiontask.User;
import com.satish.wissiontask.UserDao;
import com.satish.wissiontask.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;


    public UserRepository(Application application) {
        UserDatabase userDatabase = UserDatabase.getInstance(application);

        userDao = userDatabase.userDao();
        allUsers = userDao.getAllUsers();
    }

    public void insert(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);

    }

    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);

    }

    public void deleteAllUsers() {
        new DeleteAllUserAsyncTask(userDao).execute();

    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }


    public static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);

            return null;
        }
    }


    public static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users[0]);

            return null;
        }
    }


    public static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);

            return null;
        }
    }



    public static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private DeleteAllUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAllUsers();

            return null;
        }
    }


}
