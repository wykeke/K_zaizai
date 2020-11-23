package com.example.k_zaizai.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.k_zaizai.R
import com.example.k_zaizai.fragment.User
import kotlinx.android.synthetic.main.activity_register.*
import org.litepal.LitePal


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Register_Btn.setOnClickListener {

            val userID = Register_ID.text.toString().trim()
            val userPassword = Register_Password.text.toString().trim()
            val userName =  Register_name.text.toString()
            Log.d("tag","1+$userName")

            if (userID.isNotEmpty() && userPassword.isNotEmpty() && userName.isNotEmpty()){
                val db = User()
                //val db = User(userID.toInt(),userPassword,userName)
                db.userID = userID.toInt()
                db.password = userPassword
                db.name = userName
                db.save()

                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}