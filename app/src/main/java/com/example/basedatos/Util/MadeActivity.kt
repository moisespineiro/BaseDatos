package com.example.basedatos.Util

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.basedatos.Data.StudentsDb
import com.example.basedatos.Data.StudentsDb.Companion.listStringIDS
import com.example.basedatos.R
import kotlinx.android.synthetic.main.activity_made.*

class MadeActivity : AppCompatActivity() {

    val studentsDb = StudentsDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_made)

        if( studentsDb.studentsGetAllString().size >0){
            val miAdaptador = ArrayAdapter<String> (this@MadeActivity, android.R.layout.simple_list_item_1,studentsDb.studentsGetAllString())

            ltvEstudiantes.adapter=miAdaptador

            ltvEstudiantes.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, position: Int, id: Long ->
                val idStudent =  listStringIDS[id.toInt()]
                Toast.makeText(this@MadeActivity,"Seleccionaste el $idStudent ",Toast.LENGTH_SHORT).show()

                val intent = Intent(this@MadeActivity, DetailActivity::class.java)
                intent.putExtra("ID", idStudent.trim())
                startActivity(intent)
                studentsDb.studentsGetAll()
            }
        }
    }
}