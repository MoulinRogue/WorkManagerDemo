package com.example.workmanagerdemo;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;


public class MyWorkerClass extends Worker {
    public MyWorkerClass( @NonNull Context context,  @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {
        Context applicationContext = getApplicationContext();
        String TAG = "MyWorkerClass";
        String msg  = getInputData().getString("MSG");
        // Notification not obligatory but useful
        WorkerUtils.makeStatusNotification("Worker class initiated: " + msg, applicationContext);
        try {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(Constants.DELAY_TIME_MILLIS);
            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
            //include data with return
            Data outputData = new Data.Builder()
                    .putString("OUT_MSG", "Work complete")
                    .build();
            //notify complete (optional)
            WorkerUtils.makeStatusNotification("Work complete", applicationContext);
            // If there were no errors, return SUCCESS
            return Result.success(outputData);
        } catch (Throwable throwable) {

            // Technically WorkManager will return Result.failure()
            // but it's best to be explicit about it.
            // Thus if there were errors, we're return FAILURE
            Log.e(TAG, "Error doing background task", throwable);
            return Result.failure();
        }
    }

}
