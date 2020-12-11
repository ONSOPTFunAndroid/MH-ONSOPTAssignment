package com.example.mh_onsopt_assignment.network

import com.example.mh_onsopt_assignment.vo.RequestSignInData
import com.example.mh_onsopt_assignment.vo.RequestSignupData
import com.example.mh_onsopt_assignment.vo.ResponseSignData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SoptService {

    @Headers("Content-Type: application/json")
    @POST("users/signup")
    fun postSignUp(
        @Body body : RequestSignupData
    ) : Call<ResponseSignData>

    @Headers("Content-Type: application/json")
    @POST("users/signin")
    fun postSignIn(
        @Body body : RequestSignInData
    ) : Call<ResponseSignData>

}