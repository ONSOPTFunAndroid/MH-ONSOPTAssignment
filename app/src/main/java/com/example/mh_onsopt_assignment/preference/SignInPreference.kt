package com.example.mh_onsopt_assignment.preference

import android.content.Context

object SignInPreference{
    private const val ID_KEY = "id"
    private const val PW_KEY="pw"

    fun getId(context: Context): String{
        val sharedPreferences = context.getSharedPreferences(ID_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(ID_KEY,"")?:""
    }

    fun setId(context: Context, id: String){
        val sharedPreferences = context.getSharedPreferences(ID_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString(ID_KEY,id)
            .apply()
    }

    fun clearId(context: Context){
        val sharedPreferences = context.getSharedPreferences(ID_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .clear()
            .apply()
    }

    fun getPassword(context: Context): String{
        val sharedPreferences = context.getSharedPreferences(PW_KEY,Context.MODE_PRIVATE)
        return sharedPreferences.getString(PW_KEY,"")?:""
    }

    fun setPassword(context: Context, pw: String){
        val sharedPreferences = context.getSharedPreferences(PW_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString(PW_KEY,pw)
            .apply()
    }

    fun clearPassword(context: Context){
        val sharedPreferences = context.getSharedPreferences(PW_KEY,Context.MODE_PRIVATE)
        sharedPreferences
            .edit()
            .clear()
            .apply()
    }
}