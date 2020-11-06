package com.example.mh_onsopt_assignment.feature.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mh_onsopt_assignment.R
import com.example.mh_onsopt_assignment.common.toast
import kotlinx.android.synthetic.main.activity_sign_up.*

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
                val intent = Intent()

                intent.putExtra("Id", id)
                intent.putExtra("Pw", password)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }
    }

}