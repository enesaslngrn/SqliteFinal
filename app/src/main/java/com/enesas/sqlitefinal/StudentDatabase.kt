package com.enesas.sqlitefinal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDatabase : RoomDatabase(){

    abstract fun studentDAO() : StudentDAO

    companion object{ //Singleton yapısı ile yazmalıyız. İdeal'i bu.

        private var instance : StudentDatabase? = null // Bir instance oluşturacağız ve eğer bu instance null ise yani henüz database oluşturulmadıysa database'imizi oluşturacağız.

        fun getStudentDatabase (context: Context) : StudentDatabase? {

            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentDatabase::class.java,
                    "studentdatabase"
                ).allowMainThreadQueries().build() // burada allowMainThreadQueries diyerek çakışmaları önledik.
            }
            return instance
        }
    }
}