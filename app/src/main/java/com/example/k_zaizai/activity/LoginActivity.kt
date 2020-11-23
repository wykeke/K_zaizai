package com.example.k_zaizai.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.k_zaizai.R
import com.example.k_zaizai.fragment.User
import kotlinx.android.synthetic.main.activity_login.*
import org.litepal.LitePal


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //跳转到注册页面
        LRegister_Btn.setOnClickListener {
            val intent = Intent(this,RegisterActivity::class.java)
            startActivity(intent)
        }
        Login_Btn.setOnClickListener {
            //获取到输入框的id和password
            val userID = Login_ID.text.toString().trim()
            val userPassword = Login_Password.text.toString().trim()

            if (!TextUtils.isEmpty(userID) && !TextUtils.isEmpty(userPassword)){
                var match = false
                val users = LitePal.select("userID","password","name","ID").find(User::class.java)

                for (user in users){
                    if (user.userID == userID.toInt() && user.password == userPassword){
                        match = true
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show()

                        //跳转到MainActivity，并用Bundle类传输数据
                        val intent = Intent(this,MainActivity::class.java)
                        //返回键不再返回该activity
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        val bundle = Bundle()
                        bundle.putString("user", "ID：" + user.userID.toString())
                        bundle.putString("name",user.name)
                        bundle.putInt("ID",user.ID)
                        //Log.d("ttt", user.ID.toString()+"测试")
                        intent.putExtras(bundle)

                        startActivityForResult(intent,1)
                        finish() //销毁此Activity
                        break
                    }
                }
                if (!match){
                    Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}