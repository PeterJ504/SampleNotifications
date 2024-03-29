package pja_dma.samplenotifications;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification();
            }
        });


    }

    public void createNotification() {

        // create 3 items to start new activity
        Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
        Intent actionIntent = new Intent(MainActivity.this,ActionActivity.class);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(getApplicationContext()) ;
        taskStackBuilder.addParentStack(NotificationActivity.class);
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(123,
                PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent actionPendingIntent = PendingIntent.getActivity(this,505,actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setContentTitle("Content title");
        nBuilder.setContentText("This is the text");
        nBuilder.setSmallIcon(R.drawable.ic_stat_name);
        nBuilder.setContentIntent(pendingIntent);

        // Add sound, vibration, and lights.
        nBuilder.setDefaults(Notification.DEFAULT_SOUND);
        nBuilder.setVibrate(new long[] {50, 100, 500, 100}); // off/on/off/on -  in milliseconds
        nBuilder.setLights(Color.BLUE,500,500);  // off/on -  in milliseconds

        // Add button on Notification
        nBuilder.addAction(R.drawable.ic_open,"Open",actionPendingIntent);


        Notification notification = nBuilder.build();
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        nm.notify(504, notification);  // use this id (504) in notificationActivity
        // show notification

    }
}