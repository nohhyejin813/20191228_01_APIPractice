package com.tjeit.a20191228_01_apipractice.utils

import android.content.Context

class ContextUtil {
    companion object{

        val prefName = "APIPracticePreference"
        val USER_TOKEN = "USER_TOKEN"

        fun setUserToken(context: Context, inputToken:String){
            val pref =context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

            pref.edit().putString(USER_TOKEN, inputToken).apply()
        }

        fun getUserToken(context: Context):String{
            val pref =context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getString(USER_TOKEN,"")!!
        }

    }
}