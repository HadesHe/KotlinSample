package com.example.zhanghehe.myapplication

import com.example.zhanghehe.myapplication.framework.AcceptanceTest
import com.example.zhanghehe.myapplication.ui.HelloUnitTestActivity
import org.junit.Test

/**
 * Created by zhanghehe on 2018/3/19.
 */
class LauncherActivityTest:AcceptanceTest<LauncherActivity>(LauncherActivity::class.java){

    @Test fun shouldOpenHelloUnitTestSceen(){
        events.onClickView(R.id.btnUnitTest)
        checkTat.nextOpenActivityIs(HelloUnitTestActivity::class.java)

    }
}