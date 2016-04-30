package edu.iit.sat.itmd555.fp.fitnessmanager.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.IBinder;

import android.support.annotation.Nullable;
import android.util.Log;

import edu.iit.sat.itmd555.fp.fitnessmanager.AActivity;
import edu.iit.sat.itmd555.fp.fitnessmanager.MainTabActivity;
import edu.iit.sat.itmd555.fp.fitnessmanager.R;


/**
 * Created by Simon on 4/30/2016.
 */
public class NotificationService extends Service {

    private int ID_NOTIF = 2;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate(){

        Log.d("Notification: ", "Notification is preparing");

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Create a notification Manager
        NotificationManager mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //Create the intent for the activity launched when the user clicks on the notification
        Intent i = new Intent(this.getApplicationContext(), MainTabActivity.class);

        //The FLAG_UPDATE_CURRENT is to update the previous data sent to the activity.
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);

        //Create the notification
        Notification mNotify = new Notification.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("FitApp ! ")
                .setContentText("Today is a new day !")
                .setSmallIcon(R.drawable.notif_icon)
                .setContentIntent(pIntent)
                .setSound(sound)
                .build();

        Log.d("Notification: ", "Notification sent");

        //Notify the manager that the notification has been sent with a certain ID.
        mNM.notify(ID_NOTIF, mNotify);
        ID_NOTIF++;
        Log.d("Notification: ", "Notification end");
        stopSelf();


    }
}