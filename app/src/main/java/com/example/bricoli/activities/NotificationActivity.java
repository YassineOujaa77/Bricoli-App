package com.example.bricoli.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.bricoli.R;
import com.example.bricoli.adapters.NotificationAdapter;
import com.example.bricoli.databinding.ActivityNotificationBinding;
import com.example.bricoli.models.Notification;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private ActivityNotificationBinding binding;

    private ListView listView;
    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_notification);;

        listView = (ListView) findViewById(R.id.notifications_listview);
        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.add(new Notification("accepted", "Your request was accepted ....."));
        notificationList.add(new Notification("normal", "You have received a bid from ..."));
        notificationList.add(new Notification("refused", "Your request was refused ....."));
        notificationList.add(new Notification("normal", "You have received a bid from ..."));
        notificationList.add(new Notification("accepted", "Your request was accepted ....."));

        notificationAdapter = new NotificationAdapter(this,notificationList);
        listView.setAdapter(notificationAdapter);
    }
}