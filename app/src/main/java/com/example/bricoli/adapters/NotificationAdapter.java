package com.example.bricoli.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.example.bricoli.R;
import com.example.bricoli.models.Notification;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdapter extends ArrayAdapter<Notification>  {

    private Context aContext ;
    private List<Notification> notificationList = new ArrayList<>();
    private CardView cardView;

    public NotificationAdapter(@NotNull Context context , ArrayList<Notification> list){
        super(context,0,list);
        aContext = context ;
        notificationList = list ;
    }

    @NotNull
    @Override
    public View getView(int position , @Nullable View convertView , @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(aContext).inflate(R.layout.notification_card, parent, false);
        }

        Notification currentNotification = notificationList.get(position);

        cardView = listItem.findViewById(R.id.notification_card);

        TextView notif_text = (TextView) listItem.findViewById(R.id.notification_card_textView);
        notif_text.setText(currentNotification.getNotif_text());

        String notif_state= currentNotification.getNotif_state() ;

        if (notif_state=="accepted"){
            cardView.setCardBackgroundColor(Color.parseColor("#218838"));
            notif_text.setTextColor(aContext.getResources().getColor(R.color.white));
        }
        else if (notif_state == "normal"){
            cardView.setCardBackgroundColor(Color.parseColor("#FFFBFB"));
            notif_text.setTextColor(aContext.getResources().getColor(R.color.black));
        }
        else {
            cardView.setCardBackgroundColor(Color.parseColor("#DC3545"));
            notif_text.setTextColor(aContext.getResources().getColor(R.color.white));
        }
        return listItem;
    }
}
