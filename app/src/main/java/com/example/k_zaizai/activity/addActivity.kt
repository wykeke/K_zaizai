package com.example.k_zaizai.activity

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import com.example.k_zaizai.R
import com.example.k_zaizai.fragment.Records
import kotlinx.android.synthetic.main.activity_add.*
import org.litepal.LitePal
import java.sql.Date
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class addActivity : AppCompatActivity() {

    var openMode = 0
    var old_content = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        openMode = intent.getIntExtra("mode",-1)
        Log.d("tag","openMode is $openMode")
        if (openMode == 2){
            //打开已存在的笔记
            old_content = intent.getStringExtra("content") //获取原先内容
            editThing.setText(old_content)  //设置到编辑框
            editThing.setSelection(old_content.length) //光标位置
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//    }

    //设定触发事件
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_HOME) return true
        else if (keyCode == KeyEvent.KEYCODE_BACK){
            autoSetMessage()
            setResult(Activity.RESULT_OK,intent)
            finish()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    //判断是新建还是修改
    private fun autoSetMessage(){
        if (openMode == 1){
            if (editThing.text.toString().isEmpty()){
                intent.putExtra("mode",-1) //什么也不做
            }else{
                //新建笔记
                //Log.d("tag","此次操作为新建笔记")
                intent.putExtra("mode",1)
                intent.putExtra("content",editThing.text.toString())
                intent.putExtra("time",dateToStr())
            }
        }else{
            if (editThing.text.toString().equals(old_content))
                intent.putExtra("mode",-1) //什么也不做
            else{
                //修改笔记
                //Log.d("tag","此次操作为修改笔记")
                intent.putExtra("mode",2)
                intent.putExtra("content",editThing.text.toString())
                intent.putExtra("time",dateToStr())
            }
        }
    }

    //时间格式
    private fun dateToStr(): String? {
        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM月dd日 HH:mm")
        return formatter.format(date)
    }
}