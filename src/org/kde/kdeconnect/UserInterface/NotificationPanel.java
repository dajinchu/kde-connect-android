package org.kde.kdeconnect.UserInterface;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import org.kde.kdeconnect.Plugins.MprisPlugin.MprisActivity;
import org.kde.kdeconnect_tp.R;

/**
 * Created by Da-Jin on 12/8/2014.
 */
public class NotificationPanel {

    String deviceId;

    private MprisActivity parent;
    private NotificationManager nManager;
    private NotificationCompat.Builder nBuilder;
    private RemoteViews remoteView;

    public NotificationPanel(MprisActivity parent, String deviceId) {
        // TODO Auto-generated constructor stub
        this.parent = parent;
        this.deviceId = deviceId;
        nBuilder = new NotificationCompat.Builder(parent)
                .setContentTitle("Parking Meter")
                .setSmallIcon(R.drawable.icon)
                .setOngoing(true);

        remoteView = new RemoteViews(parent.getPackageName(), R.layout.notification_layout);

        //set the button listeners
        setListeners(remoteView);
        nBuilder.setContent(remoteView);

        nManager = (NotificationManager) parent.getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(2, nBuilder.build());
    }

    public void setListeners(RemoteViews view){
        Intent playpause = new Intent(parent,NotificationReturnSlot.class);
        playpause.putExtra("DO", "play");
        playpause.putExtra("ID",deviceId);
        //Log.i("Panel", deviceId);
        PendingIntent btn1 = PendingIntent.getActivity(parent, 1, playpause, 0);
        view.setOnClickPendingIntent(R.id.notification_play_pause, btn1);

        Intent next = new Intent(parent, NotificationReturnSlot.class);
        next.putExtra("DO", "next");
        next.putExtra("ID",deviceId);
        PendingIntent btn2 = PendingIntent.getActivity(parent, 2, next, 0);
        view.setOnClickPendingIntent(R.id.notification_next, btn2);

        Intent prev = new Intent(parent, NotificationReturnSlot.class);
        prev.putExtra("DO", "prev");
        prev.putExtra("ID",deviceId);
        PendingIntent btn3 = PendingIntent.getActivity(parent, 3, prev, 0);
        view.setOnClickPendingIntent(R.id.notification_prev, btn3);
    }

    public void notificationCancel() {
        nManager.cancel(2);
    }
}