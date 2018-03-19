package com.example.zhanghehe.myapplication.framework

import android.app.Activity
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*

/**
 * Created by zhanghehe on 2018/3/19.
 */
class Matchers{
    fun <T:Activity> nextOpenActivityIs(clazz:Class<T>){
        intended(IntentMatchers.hasComponent(clazz.name))
    }

    fun viewIsVisibleAndContainsText(@StringRes stringResource:Int){
        onView(withText(stringResource)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
    }

    fun viewContainsText(@IdRes viewId:Int,@StringRes stringResource: Int){
        onView(withId(viewId)).check(matches(withText(stringResource)))
    }
}