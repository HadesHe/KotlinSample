package com.example.zhanghehe.myapplication

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val applyResult="123".apply {
            println(substring(2)) }

        println(applyResult)

        val alsoResult=123.also { println(it+456) }

        println(alsoResult)


        val letResult=123.let { println(it+456) }
        println(letResult)


    }








}


