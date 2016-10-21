package ru.allmoyki.may;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gcm.GCMBaseIntentService;

import ru.allmoyki.may.activity.MainActivity;
import ru.allmoyki.may.preferences.MyPreferences;

public class GCMIntentService extends GCMBaseIntentService {
    @Override
    protected void onRegistered(Context context, String regId) {
        Intent intent = new Intent(MyPreferences.ACTION_ON_REGISTERED);
        intent.putExtra(MyPreferences.FIELD_REGISTRATION_ID, regId);
        context.sendBroadcast(intent);
    }

    @Override
    protected void onUnregistered(Context context, String regId) {
        Log.i("GSM", "onUnregistered: " + regId);
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.e("Log", "PUSH");
        Toast.makeText(context, "PUSH", Toast.LENGTH_LONG).show();
        String msg = "";
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int type = 16;
        String id = "";
        if (intent != null) {
            msg = intent.getStringExtra("message");
            Log.d("Log", "!!!! type:" + intent.getStringExtra("message"));


            Notification notification = prepareNotification(context, msg);

            manager.notify((int) System.currentTimeMillis(), notification);
        }
    }

    private Notification prepareNotification(Context context, String msg) {
        long when = System.currentTimeMillis();
        Notification notification = new Notification(android.R.drawable.ic_dialog_info, msg,
                when);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;


        Intent intent = new Intent(context, MainActivity.class);
        // Set a unique data uri for each notification to make sure the activity
        // gets updated
        intent.setData(Uri.parse(msg));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(PendingIntent.FLAG_UPDATE_CURRENT);


        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                0);
        String title = "Внимание!";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this);
        notification = builder.setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.ic_dialog_info).setTicker("123").setWhen((int) System.currentTimeMillis())
                .setAutoCancel(true).setContentTitle(title)
                .setContentText(msg).build();
//        notification.setLatestEventInfo(context, title, msg, pendingIntent);

        return notification;
    }


    @Override
    protected void onError(Context context, String errorId) {
//        Toast.makeText(context, errorId, Toast.LENGTH_LONG).show();
    }

    // Пришлось переопределить этот метод, а то иначе выкидывает ошибку FATAL EXCEPTION: IntentService[GCMIntentService-DynamicSenderIds-2]
    // информация взята отсюда http://belencruz.com/2013/02/gcm-sender-id-not-set-on-constructor-solution/
    @Override
    protected String[] getSenderIds(Context context) {
        String[] ids = new String[1];
        ids[0] = MyPreferences.SENDER_ID;
        return ids;
    }
}