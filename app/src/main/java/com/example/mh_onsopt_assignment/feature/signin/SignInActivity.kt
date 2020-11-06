package com.example.mh_onsopt_assignment.feature.signin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mh_onsopt_assignment.HomeActivity
import com.example.mh_onsopt_assignment.R
import com.example.mh_onsopt_assignment.common.toast
import com.example.mh_onsopt_assignment.feature.signup.SignUpActivity
import com.example.mh_onsopt_assignment.preference.SignInPreference
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    private val REQUEST_CODE_LOGIN_ACTIVITY =1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        autoSignIn()

        setSignUpClickListener()
        setSignInClickListener()
    }

    // 자동로그인
    private fun autoSignIn() {
        val id = SignInPreference.getId(this)

        if (id.isNotEmpty()) {
            val intent = Intent(this, HomeActivity::class.java)
            toast("자동로그인 되었습니다:)")

            startActivity(intent)
        }
    }

    // 회원가입 버튼 클릭
    private fun setSignUpClickListener(){
        btn_sign_up.setOnClickListener {
            val signUpIntent = Intent(this,SignUpActivity::class.java)

            startActivityForResult(signUpIntent,REQUEST_CODE_LOGIN_ACTIVITY)
        }
    }

    // 로그인 버튼 클릭
    private fun setSignInClickListener(){
        btn_sign_in.setOnClickListener {
            val id = edt_sign_in_id?.text.toString()
            val password = edt_sign_in_password?.text.toString()

            // ID와 PW 비었는지 검사
            if (id.isEmpty()) {
                toast("아이디를 입력해주세요:)")
                return@setOnClickListener
            }
            else if(password.isEmpty()){
                toast("비밀번호를 입력해주세요:)")
                return@setOnClickListener
            }

            SignInPreference.setId(this,id)
            SignInPreference.setPassword(this,password)

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_CODE_LOGIN_ACTIVITY){
            if(resultCode== Activity.RESULT_OK){
                val id=data!!.getStringExtra("Id")
                val pw=data!!.getStringExtra("Pw")
                edt_sign_in_id.setText(id)
                edt_sign_in_password.setText(pw)
            }
        }
    }
}