package com.example.zhanghehe.myapplication.jobscheduler

import android.content.Context
import android.os.Handler
import android.os.Message
import android.support.v4.content.ContextCompat.getColor
import android.view.View
import android.widget.TextView
import com.example.zhanghehe.myapplication.R
import kotlinx.android.synthetic.main.activity_job_scheduler.view.*
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

/**
 * Created by zhanghehe on 2018/3/13.
 */
internal class IncomingMessageHandler(activity:JobSchedulerActivity): Handler() {

    private val weakActivity:WeakReference<JobSchedulerActivity> = WeakReference(activity)

    override fun handleMessage(msg: Message) {

        val mainActivity=weakActivity.get()?:return
        val showStartView=mainActivity.findViewById<View>(R.id.onstart_textview)
        val showStopView=mainActivity.findViewById<View>(R.id.onstop_textview)

        when (msg.what) {
            MSG_COLOR_START -> {
                showStartView.setBackgroundColor(getColor(mainActivity,R.color.start_received))
                updateParamsTextView(msg.obj,"started")
                sendMessageDelayed(Message.obtain(this, MSG_UNCOLOR_START),
                        TimeUnit.SECONDS.toMillis(1))
            }
            MSG_COLOR_STOP->{
                showStopView.setBackgroundColor(getColor(mainActivity,R.color.stop_received))
                updateParamsTextView(msg.obj,"stopped")
                sendMessageDelayed(obtainMessage(MSG_UNCOLOR_STOP),TimeUnit.SECONDS.toMillis(1))

            }
            MSG_UNCOLOR_START->{
                uncolorButtonAndClearText(showStartView,mainActivity)


            }
            MSG_UNCOLOR_STOP->{
                uncolorButtonAndClearText(showStopView,mainActivity)

            }
        }
    }

    private fun uncolorButtonAndClearText(showStopView: View, mainActivity: JobSchedulerActivity) {
        showStopView.setBackgroundColor(getColor(mainActivity,R.color.none_received))
        updateParamsTextView()
    }

    private fun updateParamsTextView(jobId:Any?=null,action:String=""){
        val mainActivity=weakActivity.get()?:return
        val paramTextView=mainActivity.findViewById<TextView>(R.id.task_params)

        if(jobId== null){
            paramTextView.text=""
            return
        }
        paramTextView.text= mainActivity.getString(R.string.job_status, jobId.toString(), action)
    }


}