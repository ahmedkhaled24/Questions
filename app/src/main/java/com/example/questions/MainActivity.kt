package com.example.questions

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.net.URI

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)

        btn_questions.setOnClickListener {
            var i = Intent(this,SecondActivity::class.java)
            startActivity(i)
        }

        btn_rateApp.setOnClickListener {
          try {
              var uri = Uri.parse("http://play.google.com/store/apps")

              var i = Intent(Intent.ACTION_VIEW)
              i.data = uri
              startActivity(i)
          } catch (ex:Exception){
              Toast.makeText(this,"$ex",Toast.LENGTH_SHORT).show()
          }
        }
    }
}
