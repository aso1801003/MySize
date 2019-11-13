package jp.ac.asojuku.s.mysize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.Settings.Global.putString
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    //リモート反映練習
    override fun onResume(){
        super.onResume()
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editNeck = pref.getString("NECK","")
        val editSleeve = pref.getString("SLEEVE","")
        val editWaist = pref.getString("WAIST","")
        val editInseam = pref.getString("INSEAM","")

        neck.setText(editNeck)
        sleeve.setText(editSleeve)
        waist.setText(editWaist)
        inseam.setText(editInseam)

        save.setOnClickListener{onSaveTapped()}

        heightButton.setOnClickListener{
            val intent = Intent(this,HeightActivity::class.java)
            this.startActivity(intent)
        }

    }
    private fun onSaveTapped(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            putString("NECK",neck.text.toString())
            putString("SLEEVE",sleeve.text.toString())
            putString("WAIST",waist.text.toString())
            putString("INSEAM",inseam.text.toString())

        }
    }
}
