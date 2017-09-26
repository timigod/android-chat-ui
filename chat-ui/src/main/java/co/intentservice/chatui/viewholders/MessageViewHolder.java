package co.intentservice.chatui.viewholders;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import co.intentservice.chatui.R;

/**
 * View Holder for the Chat UI. Interfaces with the Received and Sent views and sets them up
 * with any messages required.
 *
 * Created by James Lendrem, utilises code by Timi
 */

public class MessageViewHolder {

    public final int STATUS_SENT = 0;
    public final int STATUS_RECEIVED = 1;

    View row;
    Context context;
    CardView bubble;
    TextView messageTextView;
    TextView timestampTextView;
    private int bubbleBackgroundRcv, bubbleBackgroundSend;

    public MessageViewHolder(View convertView, int bubbleBackgroundRcv, int bubbleBackgroundSend) {
        row = convertView;
        bubble = (CardView) convertView.findViewById(R.id.bubble);
        context = row.getContext();
        this.bubbleBackgroundSend = bubbleBackgroundSend;
        this.bubbleBackgroundRcv = bubbleBackgroundRcv;
    }

    public TextView getMessageTextView() {
        if (messageTextView == null) {
            messageTextView = (TextView) row.findViewById(R.id.message_text_view);
        }
        return messageTextView;
    }

    public TextView getTimestampTextView() {
        if (timestampTextView == null) {
            timestampTextView = (TextView) row.findViewById(R.id.timestamp_text_view);
        }

        return timestampTextView;
    }

    public CardView getChatBubble() {
        if (bubble == null) {
            bubble = (CardView) row.findViewById(R.id.bubble);
        }

        return bubble;
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

        bubble.setCardBackgroundColor(background);
    }

}
