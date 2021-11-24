//package com.example.taskmaster;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//@Database(entities = {Task.class}, version = 1)
//public abstract class AppDatabase extends RoomDatabase {
//    public abstract TaskDao taskDao();
//
//    private static AppDatabase db;
//
//    public static AppDatabase getDbInstance(Context context){
//        if(db == null){
//            db= Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,"tasked").allowMainThreadQueries().build();
//        }
//
//return db;
//}
//}
