package com.example.mh_onsopt_assignment.feature.signin

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mh_onsopt_assignment.feature.HomeActivity
import com.example.mh_onsopt_assignment.R
import com.example.mh_onsopt_assignment.common.toast
import com.example.mh_onsopt_assignment.feature.signup.SignUpActivity
import com.example.mh_onsopt_assignment.network.SoptServiceImpl
import com.example.mh_onsopt_assignment.preference.SignInPreference
import com.example.mh_onsopt_assignment.vo.RequestSignInData
import com.example.mh_onsopt_assignment.vo.ResponseSignData
import kotlinx.android.synthetic.main.activity_sign_in.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

            postSignIn(id,password)
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

    private fun postSignIn(email:String, password:String){
        val call : Call<ResponseSignData> = SoptServiceImpl.service.postSignIn(
            RequestSignInData(email = email,password = password)
        )
        call.enqueue(object : Callback<ResponseSignData> {
            override fun onFailure(call: Call<ResponseSignData>, t: Throwable) {
                // 통신 실패 로직
                Log.d("명","실패")
            }
            override fun onResponse(
                call: Call<ResponseSignData>,
                response: Response<ResponseSignData>
            ) {
                response.takeIf { it.isSuccessful}
                    ?.body()
                    ?.let { it ->
                        SignInPreference.setId(this@SignInActivity,email)
                        SignInPreference.setPassword(this@SignInActivity,password)

                        val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } ?: showError(response.errorBody())
            }
        })
    }

    private fun showError(error : ResponseBody?){
        val e = error ?: return
        val ob = JSONObject(e.string())
        toast(ob.getString("message"))
    }
}