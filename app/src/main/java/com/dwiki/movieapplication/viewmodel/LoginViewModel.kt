package com.dwiki.movieapplication.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val sharedPreferences: SharedPreferences):ViewModel() {

    //save username
    fun saveUsernamePreferences(key: String,value: String){
        val editor = sharedPreferences.edit()
        editor.putString(key,value)
        editor.apply()
    }

    //get username
    fun getUsernamePreferences(key: String):String?{
        return sharedPreferences.getString(key,"Username Kosong")
    }
}