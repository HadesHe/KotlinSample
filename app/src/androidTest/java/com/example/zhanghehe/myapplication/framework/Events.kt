package com.example.zhanghehe.myapplication.framework

import android.support.annotation.IdRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId

/**
 * Created by zhanghehe on 2018/3/19.
 */
class Events{
    fun onClickView(@IdRes viewId:Int){
        onView(withId(viewId)).perform(click())
    }
}