package com.example.mh_onsopt_assignment.feature.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mh_onsopt_assignment.R
import com.example.mh_onsopt_assignment.common.toast
import com.example.mh_onsopt_assignment.network.SoptServiceImpl
import com.example.mh_onsopt_assignment.vo.RequestSignupData
import com.example.mh_onsopt_assignment.vo.ResponseSignData
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setConfirmClickListener()
    }

    private fun setConfirmClickListener(){
        btn_sign_up_confirm.setOnClickListener {

            val name = edt_sign_up_name?.text.toString()
            val id = edt_sign_up_id?.text.toString()
            val password = edt_sign_up_password?.text.toString()

            // 빈 곳 있는지 체크
            if (name.isEmpty() || id.isEmpty() || password.isEmpty()) {
                toast("빈칸없이 모두 작성해주세요:)")
            }
            else{

                postSignUp(id,password,name)
            }
        }
    }

    private fun postSignUp(email:String, password:String, name:String){
        val call : Call<ResponseSignData> = SoptServiceImpl.service.postSignUp(
            RequestSignupData(email = email,password = password, userName = name)
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
                        val intent = Intent()

                        intent.putExtra("Id", email)
                        intent.putExtra("Pw", password)

                        setResult(Activity.RESULT_OK,intent)
                        finish()
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