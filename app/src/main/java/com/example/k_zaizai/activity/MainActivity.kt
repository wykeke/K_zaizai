package com.example.k_zaizai.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.k_zaizai.R
import com.example.k_zaizai.H_Fragment
import com.example.k_zaizai.fragment.First_Fragment
import com.example.k_zaizai.fragment.Me_Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    val mFirst_Fragment = First_Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment,mFirst_Fragment).commit()

        v1.setOnClickListener(this)
        v2.setOnClickListener(this)
        v3.setOnClickListener(this)

    }


    //点击事件
     override fun onClick(view: View){
        val fragmentManager = supportFragmentManager //获取FragmentManager实例
        val transaction = fragmentManager.beginTransaction() //开启一个事物
        var f : Fragment? = null
        when(view?.id) {
            R.id.v1 -> {
                //如果不是记账界面则切换到记账界面，
                // 如果已经是记账界面直接跳到编辑界面
                f = First_Fragment()
        }
            R.id.v2 -> f = H_Fragment()
            R.id.v3 -> f = Me_Fragment()
        }

        //切换
         if (f != null) {
             transaction.replace(R.id.fragment,f)
         }
        transaction.commit() //提交事务
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //接受LoginActivity传送结果
        Log.d("tag","$requestCode $resultCode")
        //if (requestCode == 1){
            val fragmentManager = supportFragmentManager //获取FragmentManager实例
            val transaction = fragmentManager.beginTransaction() //开启一个事物
            transaction.replace(R.id.fragment,Me_Fragment())
        //}
    }
}