package com.example.zhanghehe.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.zhanghehe.myapplication.jobscheduler.JobSchedulerActivity
import kotlinx.android.synthetic.main.activity_launcher.*

class LauncherActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        btnUnitTest.setOnClickListener {
            startActivity(Intent(this@LauncherActivity,MockitoUseActivity::class.java))
        }

        btnJobScheduler.setOnClickListener{
            startActivity(Intent(this@LauncherActivity,JobSchedulerActivity::class.java))
        }



    }


}

