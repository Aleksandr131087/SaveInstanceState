package com.example.saveinstancestate

import UserViewModel2
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer

class User(val name: String, val age: String){
    override fun toString(): String {
        return "Имя: $name, Возраст: $age"
    }
}

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel2 by viewModels()
    private lateinit var adapter: ArrayAdapter<User>

    private lateinit var toolbarMain: Toolbar
    private lateinit var nameET: EditText
    private lateinit var ageET: EditText
    private lateinit var listviewLV: ListView
    private lateinit var saveBTN: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)
        title = "Каталог пользователей"

        saveBTN = findViewById(R.id.SaveBTN)
        nameET = findViewById(R.id.NameET)
        ageET = findViewById(R.id.AgeET)
        listviewLV = findViewById(R.id.ListviewLV)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        findViewById<ListView>(R.id.ListviewLV).adapter = adapter


        userViewModel.userList.observe(this, Observer { users ->
            adapter.clear()
            adapter.addAll(users)
            adapter.notifyDataSetChanged()
        })

        saveBTN.setOnClickListener{
            if (nameET.text.isNotEmpty() && ageET.text.isNotEmpty()){
                val user = User(nameET.text.toString(), ageET.text.toString().toInt().toString())
                userViewModel.addUser(user)

                adapter.notifyDataSetChanged()
                nameET.text.clear()
                ageET.text.clear()}
            else return@setOnClickListener

        }

        listviewLV.onItemClickListener =
            AdapterView.OnItemClickListener{parent, v, position, id ->
                val userDelete = adapter.getItem(position)
                adapter.remove(userDelete)
                Toast.makeText(this, "Пользователь удален $userDelete", Toast.LENGTH_LONG).show()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_exit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}