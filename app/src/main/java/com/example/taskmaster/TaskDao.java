package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task")
    List<Task> getAll();
    @Insert
    void insertTask(Task... tasks);
    @Delete
    void delete(Task task);

//try to get elemnt by d
//    @Query("SELECT * FROM task WHERE id=:id")
//     Task getTask(int id);
}
