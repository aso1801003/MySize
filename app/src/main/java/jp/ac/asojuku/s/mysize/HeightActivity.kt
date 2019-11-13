package jp.ac.asojuku.s.mysize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Spinner
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_height.*

class HeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height)
    }

    override fun onResume() {
        super.onResume()
        spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent:AdapterView<*>?,view:View?,position:Int,id:Long){
                    val spinner = parent as? Spinner
                    val item = spinner?.selectedItem as? String
                    item?.let{
                        if(it.isNotEmpty()) height.text = it
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val heightVal = pref.getInt("HEIGHT",160)
        height.text = heightVal.toString()
        seekBar.progress = heightVal

        seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?,
                                               progress:Int,
                                               fromUser:Boolean) {
                    height.text = progress.toString()
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {}

                override fun onStopTrackingTouch(p0: SeekBar?) {}
            }
        )

        //ラジオボタンのidを受け取って、身長ラベルを上書き
        radioGroup.setOnCheckedChangeListener{
            group,checkedId -> height.text = findViewById<RadioButton>(checkedId).text
        }
    }

    override fun onPause(){
        super.onPause()
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.edit{
            putInt("HEIGHT",height.text.toString().toInt())
        }
    }



}
