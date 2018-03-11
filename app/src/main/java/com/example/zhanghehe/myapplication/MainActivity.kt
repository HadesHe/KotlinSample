package com.example.zhanghehe.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var nameText: EditText
    private lateinit var emailText: EditText

    private lateinit var dobPicker: DatePicker

    private var emailValidator: EmailValidator = EmailValidator()

    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameText=findViewById(R.id.userNameInput)
        emailText=findViewById(R.id.emailInput)


        dobPicker=findViewById(R.id.dateOfBirthInput)

        emailText.addTextChangedListener(emailValidator)

        val sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this)
        sharedPreferencesHelper=SharedPreferencesHelper(sharedPreferences)

        populateUI()


    }

    private fun populateUI() {
        val sharedPreferenceEntry=sharedPreferencesHelper.getPersonalInfo()
        nameText.setText(sharedPreferenceEntry.name)

        val dateOfBirth=sharedPreferenceEntry.dateOfBirth
        dobPicker.init(dateOfBirth.get(Calendar.YEAR),
                dateOfBirth.get(Calendar.MONTH),
                dateOfBirth.get(Calendar.DAY_OF_MONTH),
                null
                )
        emailText.setText(sharedPreferenceEntry.email)
    }

    fun onSaveClick(view:View){

        if(emailValidator.isValid.not()){
            emailText.error="Invalid email"
            return
        }

        val name=nameText.text.toString()
        val dateOfBirth=Calendar.getInstance()
        dateOfBirth.set(dobPicker.year,dobPicker.month,dobPicker.dayOfMonth)

        val email=emailText.text.toString()
        val sharedPreferenceEntry=SharedPreferenceEntry(name,dateOfBirth,email)

        val isSuccess=sharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry)
        if (isSuccess) {
            Toast.makeText(this,"Personal Information Saved",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Personal Information Failed",Toast.LENGTH_LONG).show()

        }

    }

    fun onRevertClick(view:View){
        populateUI()
        Toast.makeText(this,"Personl Infomation reverted",Toast.LENGTH_LONG).show()
    }
}
