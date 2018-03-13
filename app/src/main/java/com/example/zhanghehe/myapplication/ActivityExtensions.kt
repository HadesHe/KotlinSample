package com.example.zhanghehe.myapplication

import android.app.Activity
import android.widget.Toast

/**
 * Created by zhanghehe on 2018/3/13.
 */
fun Activity.showToast(text:String){
    Toast.makeText(this,text,Toast.LENGTH_LONG).show()
}