package com.example.zhanghehe.myapplication.framework

import android.app.Activity
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.rule.ActivityTestRule
import org.junit.Rule

/**
 * Created by zhanghehe on 2018/3/19.
 */
abstract class AcceptanceTest<T:Activity>(clazz: Class<T>){

    /**
     * This need 'com.android.support.test.espresso:espresso-intents"
     */
    @Rule
    val testRule:ActivityTestRule<T> = IntentsTestRule(clazz)

    val checkTat:Matchers= Matchers()
    val events:Events=Events()
}