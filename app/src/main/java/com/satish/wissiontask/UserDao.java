package com.satish.wissiontask;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Update
    void updateUser(User user);

    @Delete
    void delete(User user);


    @Query("DELETE FROM user_table ")
    void deleteAllUsers();


    @Query("SELECT * FROM user_table ORDER BY id")
    LiveData<List<User>> getAllUsers();
}
