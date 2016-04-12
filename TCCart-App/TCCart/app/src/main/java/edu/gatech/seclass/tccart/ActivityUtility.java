package edu.gatech.seclass.tccart;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Kevin Hall on 4/5/2016.
 * Package: edu.gatech.seclass.tccart
 */
public class ActivityUtility {

    private Handler hand = new Handler();
    private Activity act;
    private Queue<AlertDialog> simple_dialog = new LinkedList<AlertDialog>();
    private AlertDialog next_dialog;
    private long current_millisecond_time = 0;

    ActivityUtility(Activity a)
    {
        act = a;
    }

    public Runnable finishActivity = new Runnable() {
        @Override
        public void run() {
            if(act != null)act.finish();
        }
    };

    public void emulateRunningActivity(String title, String message, long milliseconds, AlertDialog nxt_dialog)
    {
        // Success dialog
        current_millisecond_time += milliseconds;
        next_dialog = nxt_dialog;
        AlertDialog d = new AlertDialog.Builder(act).create();

        d.setCancelable(false);
        d.setTitle(title);
        d.setMessage(message);
        if(simple_dialog.isEmpty())d.show();
        simple_dialog.add(d);
        hand.postDelayed(dummyActivity, current_millisecond_time);
    }

    public void emulateRunningActivity(String title, String message, long milliseconds)
    {
        emulateRunningActivity(title, message, milliseconds, null);
    }

    private Runnable dummyActivity = new Runnable() {
        @Override
        public void run() {
            if(simple_dialog != null)
            {
                simple_dialog.remove().dismiss();
                if(!simple_dialog.isEmpty())simple_dialog.element().show();
                else
                {
                    if(next_dialog != null)next_dialog.show();
                    current_millisecond_time = 0;
                }
            }
        }
    };

    public Handler getHandler()
    {
        return hand;
    }

    public long getCurrentMillisecondTime()
    {
        return current_millisecond_time;
    }
}
