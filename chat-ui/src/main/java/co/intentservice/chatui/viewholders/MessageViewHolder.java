package co.intentservice.chatui.viewholders;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import co.intentservice.chatui.R;
import co.intentservice.chatui.views.MessageView;

/**
 * View Holder for the Chat UI. Interfaces with the Received and Sent views and sets them up
 * with any messages required.
 *
 * Original Code by Timi
 * Extended by James Lendrem
 */

public class MessageViewHolder {

    public final int STATUS_SENT = 0;
    public final int STATUS_RECEIVED = 1;

    View row;
    Context context;

    private MessageView messageView;
    private int bubbleBackgroundRcv, bubbleBackgroundSend;

    public MessageViewHolder(View convertView, int bubbleBackgroundRcv, int bubbleBackgroundSend) {
        row = convertView;
        context = row.getContext();
        messageView = (MessageView) convertView;
        this.bubbleBackgroundSend = bubbleBackgroundSend;
        this.bubbleBackgroundRcv = bubbleBackgroundRcv;
    }

    public void setMessage(String message) {

        messageView.setMessage(message);

    }

    public void setTimestamp(String timestamp) {

        messageView.setTimestamp(timestamp);

    }

    public void setElevation(float elevation) {

        messageView.setElevation(elevation);

    }

    public void setBackground(int messageType) {

        int background = ContextCompat.getColor(context, R.color.cardview_light_background);

        switch (messageType) {
            case STATUS_RECEIVED:
                background = bubbleBackgroundRcv;
                break;
            case STATUS_SENT:
                background = bubbleBackgroundSend;
                break;
        }

        messageView.setBackgroundColor(background);

    }

}
