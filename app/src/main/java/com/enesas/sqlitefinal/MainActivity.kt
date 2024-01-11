package com.enesas.sqlitefinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.enesas.sqlitefinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        var view = binding.root
        setContentView(view)



        roomSqlite()
    }

    fun basitSqlite(){
        try {

            val veritabani = this.openOrCreateDatabase("Veritabani", MODE_PRIVATE,null)
            veritabani.execSQL("CREATE TABLE IF NOT EXISTS veritabani (id INTEGER PRIMARY KEY, isim VARCHAR, soyisim VARCHAR, pozisyon VARCHAR, yas INT, maas INT)")

            /*

            veritabani.execSQL("INSERT INTO veritabani (isim, soyisim, pozisyon, yas, maas) VALUES ('Enes', 'Aslangoren', 'Yazilimci', 26, 20000)")
            veritabani.execSQL("INSERT INTO veritabani (isim, soyisim, pozisyon, yas, maas) VALUES ('Elifnaz', 'Leblebici', 'Psikolog', 25, 50000)")

             */

            //veritabani.execSQL("DELETE FROM veritabani")
            //veritabani.execSQL("DELETE FROM veritabani WHERE isim = 'Enes'")

            val cursor = veritabani.rawQuery("SELECT * FROM veritabani", null)

            //val cursor = veritabani.rawQuery("SELECT * FROM veritabani WHERE isim = 'Enes'", null)

            //val cursor = veritabani.rawQuery("SELECT * FROM veritabani WHERE pozisyon LIKE 'P%'",null)

            //veritabani.execSQL("UPDATE veritabani SET maas = 40000 WHERE isim = 'Elifnaz'")



            val columnId = cursor.getColumnIndex("id")
            val columnIsim = cursor.getColumnIndex("isim")
            val columnSoyisim = cursor.getColumnIndex("soyisim")
            val columnPozisyon = cursor.getColumnIndex("pozisyon")
            val columnYas = cursor.getColumnIndex("yas")
            val columnMaas = cursor.getColumnIndex("maas")



            while (cursor.moveToNext()){
                println("id: ${cursor.getInt(columnId)}, isim: ${cursor.getString(columnIsim)}, soyisim: ${cursor.getString(columnSoyisim)}, " +
                        "pozisyon: ${cursor.getString(columnPozisyon)}, yas: ${cursor.getInt(columnYas)}, maas: ${cursor.getInt(columnMaas)}")
            }

            cursor.close()


        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun roomSqlite(){

        // Entity -> Database'deki tabloları temsil eder.
        // Data Access Objects DAO -> Database işlemlerini yapan interface. (SELECT, DELETE, UPDATE, QUERY felan gibi işlemler)
        // Database -> Tüm işlemleri de en tepeden bu sınıf ile yönetiyoruz.

        val studentDatabase : StudentDatabase? = StudentDatabase.getStudentDatabase(this) // StudentDatabase? sınıfına gidip getStudentDatabase fonksiyonunu çağırdık.

        binding.buttonInsert.setOnClickListener {
            val studentName = binding.editTextName.text.toString()
            val studentGrade = binding.editTextGrade.text.toString().toInt()

            val student = Student(0,studentName,studentGrade)

            studentDatabase?.studentDAO()?.insertAll(student)
        }
        binding.buttonGet.setOnClickListener {

            val allStudents : ArrayList<Student> = studentDatabase?.studentDAO()?.getAll() as ArrayList<Student>
            var result = ""
            allStudents.forEach {
                result += "ID: " + it.uuid.toString() + " " + it.studentName + "->" + it.studentGrade + "\n"
            }

            binding.textViewShow.text = result

        }
        binding.buttonDelete.setOnClickListener {
            studentDatabase?.studentDAO()?.deleteAll()
        }


        binding.buttonUpdate.setOnClickListener {
            studentDatabase?.studentDAO()?.update()
        }


    }

}