package com.enesas.sqlitefinal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student") // Tablo'nun ismi
data class Student (
    @PrimaryKey(autoGenerate = true) // Her oluşturulan nesneye otomatik id veriyor. Aynı normal sqlite gibi.
    val uuid : Int = 0,

    @ColumnInfo(name = "studentName")
    val studentName : String,

    @ColumnInfo(name = "studentGrade")
    val studentGrade : Int

    ) {
}