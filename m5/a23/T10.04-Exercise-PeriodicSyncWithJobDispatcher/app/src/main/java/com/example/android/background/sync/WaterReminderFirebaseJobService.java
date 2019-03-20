package com.example.android.background.sync;

import android.content.Context;
import android.os.AsyncTask;

import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.RetryStrategy;

// done (3) WaterReminderFirebaseJobService should extend from JobService
public class WaterReminderFirebaseJobService extends JobService {

    private AsyncTask mBackgroundTask;


    // done (4) Override onStartJob

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        // done (5) By default, jobs are executed on the main thread, so make an anonymous class extending
        //  AsyncTask called mBackgroundTask.
        // Here's where we make an AsyncTask so that this is no longer on the main thread
        mBackgroundTask = new AsyncTask() {

            // done (6) Override doInBackground
            @Override
            protected Object doInBackground(Object[] params) {
                // done (7) Use ReminderTasks to execute the new charging reminder task you made, use
                // this service as the context (WaterReminderFirebaseJobService.this) and return null
                // when finished.
                Context context = WaterReminderFirebaseJobService.this;
                ReminderTasks.executeTask(context, ReminderTasks.ACTION_CHARGING_REMINDER);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                // done (8) Override onPostExecute and call jobFinished. Pass the job parameters
                // and false to jobFinished. This will inform the JobManager that your job is done
                // and that you do not want to reschedule the job.

                jobFinished(jobParameters, false);
            }
        };

        // done (9) Execute the AsyncTask
        mBackgroundTask.execute();
        // done (10) Return true
        return true;
    }

    // done (11) Override onStopJob

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        // done (12) If mBackgroundTask is valid, cancel it
        // done (13) Return true to signify the job should be retried
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);
        return true;
    }


}