package com.example.zhanghehe.myapplication

import android.content.SharedPreferences
import java.util.*

/**
 * Created by zhanghehe on 2018/3/10.
 */
class SharedPreferencesHelper(private val sharedPreferences:SharedPreferences){


    fun getPersonalInfo():SharedPreferenceEntry{
        val name=sharedPreferences.getString(KEY_NAME,"")
        val dobMillis=sharedPreferences.getLong(KEY_DOB,Calendar.getInstance().timeInMillis)
        val dateOfBirth=Calendar.getInstance().apply {
            timeInMillis=dobMillis
        }
        val email=sharedPreferences.getString(KEY_email,"")
        return SharedPreferenceEntry(name, dateOfBirth, email)

    }

    fun savePersonalInfo(sharedPreferenceEntry: SharedPreferenceEntry): Boolean {
        val editor=sharedPreferences.edit().apply(){
            putString(KEY_NAME,sharedPreferenceEntry.name)
            putLong(KEY_DOB,sharedPreferenceEntry.dateOfBirth.timeInMillis)
            putString(KEY_email,sharedPreferenceEntry.email)
        }
        return editor.commit()

    }

    companion object {
        internal val KEY_NAME="key_name"
        internal val KEY_DOB="key_dob"
        internal val KEY_email="key_email"

    }
}

class SharedPreferenceEntry(val name:String,val dateOfBirth:Calendar,val email:String)
