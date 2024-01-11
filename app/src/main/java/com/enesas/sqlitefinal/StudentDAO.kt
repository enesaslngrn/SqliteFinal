package com.enesas.sqlitefinal

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StudentDAO {
    @Insert
    fun insertAll(vararg student: Student) // Bu fonksiyon ile tüm verileri liste olarak verip insert ediyoruz. vararg -> 1'den fazla Student insert etmek için gerekli.

    @Query("DELETE FROM student")
    fun deleteAll()

    @Query("SELECT * FROM student")
    fun getAll() : List<Student>

    @Query("UPDATE student SET studentGrade = 10 WHERE studentName = 'Enes'")
    fun update()
}