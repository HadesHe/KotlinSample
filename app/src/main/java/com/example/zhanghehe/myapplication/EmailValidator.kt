package com.example.zhanghehe.myapplication

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern

/**
 * Created by zhanghehe on 2018/3/10.
 */
class EmailValidator:TextWatcher{

    internal var isValid: Boolean=false

    override fun afterTextChanged(s: Editable?) {
        isValid=isValidEmail(s)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }


    companion object {

        private val EMAIL_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        )

        fun isValidEmail(email:CharSequence?):Boolean{
            return email!=null&& EMAIL_PATTERN.matcher(email).matches()
        }

    }

}