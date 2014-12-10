package org.kde.kdeconnect.UserInterface;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.kde.kdeconnect.BackgroundService;
import org.kde.kdeconnect.Device;
import org.kde.kdeconnect.Plugins.MprisPlugin.MprisPlugin;

/**
 * Created by Da-Jin on 12/8/2014.
 */
public class NotificationReturnSlot extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String action = (String) getIntent().getStringExtra("DO");
        final String deviceId =  getIntent().getStringExtra("ID");
        Log.i("NotificationReturnSlot", action+deviceId);
        if (action.equals("play")) {
            Log.i("NotificationReturnSlot", "Playpause");
            BackgroundService.RunCommand(NotificationReturnSlot.this, new BackgroundService.InstanceCallback() {
                @Override
                public void onServiceStart(BackgroundService service) {
                    Device device = service.getDevice(deviceId);
                    MprisPlugin mpris = (MprisPlugin) device.getPlugin("plugin_mpris");
                    if (mpris == null) return;
                    mpris.sendAction("PlayPause");
                }
            });
        } else if (action.equals("next")) {
            Log.i("NotificationReturnSlot", "stopNotification");
            BackgroundService.RunCommand(NotificationReturnSlot.this, new BackgroundService.InstanceCallback() {
                @Override
                public void onServiceStart(BackgroundService service) {
                    Device device = service.getDevice(deviceId);
                    MprisPlugin mpris = (MprisPlugin) device.getPlugin("plugin_mpris");
                    if (mpris == null) return;
                    mpris.sendAction("Next");
                }
            });
        }else if(action.equals("prev")){
            BackgroundService.RunCommand(NotificationReturnSlot.this, new BackgroundService.InstanceCallback() {
                @Override
                public void onServiceStart(BackgroundService service) {
                    Device device = service.getDevice(deviceId);
                    MprisPlugin mpris = (MprisPlugin) device.getPlugin("plugin_mpris");
                    if (mpris == null) return;
                    mpris.sendAction("Previous");
                }
            });
        }
        finish();
    }
}
