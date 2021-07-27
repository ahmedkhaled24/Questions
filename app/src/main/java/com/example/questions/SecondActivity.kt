package com.example.questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.Window
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import java.lang.Exception
import java.util.*

class SecondActivity : AppCompatActivity() {

    var val_question:Array<String>?=null
    var val_answer:Array<String>?=null
    var index :Int? = null
    var default_text:String = "Press on the eye to show the answer"
    var TtoS:TextToSpeech?=null
    var result:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_second)

        TtoS = TextToSpeech(this,object : TextToSpeech.OnInitListener{
            override fun onInit(status: Int) {
                result = TtoS!!.setLanguage(Locale.ENGLISH)
            }

        })

        try {
            index=0
            val_question = resources.getStringArray(R.array.ques)
            val_answer = resources.getStringArray(R.array.answs)
            tx_question.text="${val_question!![index!!]}"
            num_xx.text="${index!!+1}"
            num_yy.text="/${val_question!!.size}"
        }
        catch (e:Exception){
            Toast.makeText(this,"$e",Toast.LENGTH_SHORT).show()
        }



    }



    fun showAction(v: View) {
        when(v.id){
            R.id.btn_back -> {
                try {
                    tx_show_answer.text=default_text
                    index= index!! -1
                    tx_question.text = "${val_question!![index!!]}"
                    num_xx.text= "${index!!+1}"
                } catch (ex:Exception){
                    Toast.makeText(this,"There are no previous questions",Toast.LENGTH_SHORT).show()
                }
            }



            R.id.btn_answer -> {
                tx_show_answer.text = val_answer!![index!!]
            }



            R.id.btn_next -> {

                try {
                    tx_show_answer.text=default_text
                    index= index!! +1
                    tx_question.text = "${val_question!![index!!]}"
                    num_xx.text= "${index!!+1}"
                } catch (ex:Exception){
                    index = val_question!!.size - val_question!!.size
                    tx_question.text="${val_question!![index!!]}"
                    num_xx.text="${index!!+1}"
                }
            }



            R.id.btn_voice -> {
                if(result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA){
                    Toast.makeText(this,"Not Voice",Toast.LENGTH_SHORT).show()
                }
                else{
                    TtoS!!.speak(tx_question.text,TextToSpeech.QUEUE_FLUSH,null,null)
                }
            }
        }
    }
}
