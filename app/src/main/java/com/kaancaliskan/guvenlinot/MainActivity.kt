package com.kaancaliskan.guvenlinot

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.main_activity.*

/**
 * This activity saves note and encode/decode note.
 * @author Hakkı Kaan Çalışkan
 */
class MainActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))

        if(!check_for_intent){
            //For restrict accessing without password check.
            Toast.makeText(this,getString(R.string.restrict_access),Toast.LENGTH_LONG).show()
            finish()
        }

        val decodedNote=Hash.decode(LocalData.with(this).read(getString(R.string.encoded_note)))
        note_EditText.setText(decodedNote)

        note_save_button.setOnClickListener{
            val newNote=note_EditText.text.toString()
            val encodedNote= Hash.encode(newNote)
            LocalData.with(this).write(getString(R.string.encoded_note),encodedNote)
            Snackbar.make(note_save_button, getString(R.string.saved),Snackbar.LENGTH_LONG).show()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val intent = Intent(applicationContext, Settings::class.java)
            startActivity(intent)
            true
        }
        R.id.action_about ->{
            val intent = Intent(applicationContext, About::class.java)
            startActivity(intent)
            true
        }else -> {
            super.onOptionsItemSelected(item)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}