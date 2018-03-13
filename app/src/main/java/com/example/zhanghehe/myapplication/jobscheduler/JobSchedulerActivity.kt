package com.example.zhanghehe.myapplication.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Messenger
import android.os.PersistableBundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.zhanghehe.myapplication.R
import com.example.zhanghehe.myapplication.showToast

import kotlinx.android.synthetic.main.activity_job_scheduler.*
import java.util.concurrent.TimeUnit

class JobSchedulerActivity : AppCompatActivity() {

    private var jobId=0

    private lateinit var serviceComponent: ComponentName


    private lateinit var handler: IncomingMessageHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_scheduler)

        handler=IncomingMessageHandler(this)
        serviceComponent= ComponentName(this@JobSchedulerActivity,MyJobService::class.java)


        cancel_button.setOnClickListener {
            cancelAllJobs()
        }

        finished_button.setOnClickListener {
            finishJob()
        }

        schedule_button.setOnClickListener{
            scheduleJob()

        }
    }

    override fun onStart() {
        super.onStart()
        val startServiceIntent=Intent(this,MyJobService::class.java)
        val messengerIncoming=Messenger(handler)
        startServiceIntent.putExtra(MESSENGER_INTENT_KEY,messengerIncoming)
        startService(startServiceIntent)
    }

    override fun onStop() {
        stopService(Intent(this,MyJobService::class.java))
        super.onStop()
    }



    private fun scheduleJob() {
        val builder=JobInfo.Builder(jobId++,serviceComponent)

        val delay=delay_time.text.toString()
        if (delay.isNotEmpty()) {
            builder.setMinimumLatency(delay.toLong()*TimeUnit.SECONDS.toMillis(1))
        }

        val dealine=deadline_time.text.toString()
        if (dealine.isNotEmpty()) {
            builder.setOverrideDeadline(dealine.toLong()*TimeUnit.SECONDS.toMillis(1))
        }

        if(checkbox_unmetered.isChecked){
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
        }else if(checkbox_any.isChecked){
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        }

        val extra=PersistableBundle()
        var workDuration=duration_time.text.toString()
        if(workDuration.isEmpty()) workDuration="1"
        extra.putLong(WORK_DURATION_KEY,workDuration.toLong()*TimeUnit.SECONDS.toMillis(1))

        builder.run {
            setRequiresDeviceIdle(checkbox_idle.isChecked)
            setRequiresCharging(checkbox_charging.isChecked)
            setExtras(extra)
        }
        Log.d(TAG, "Scheduling job")

        (getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler).schedule(builder.build())


    }

    private fun finishJob() {
        val jobScheduler=getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler

        val allPendingJobs=jobScheduler.allPendingJobs

        if ((allPendingJobs.size > 0)) {
            val id= allPendingJobs.first().id
            jobScheduler.cancel(id)
            showToast("cancel last job"+id)
        }else{
            showToast("No job to cancel")

        }

    }

    private fun cancelAllJobs() {
        (getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler).cancelAll()
        showToast("All jobs cancelled")
    }

    companion object {
        private val TAG="JobSchedulerActivity"
    }

}
