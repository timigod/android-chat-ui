package co.intentservice.chatui.viewholders;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import co.intentservice.chatui.R;

/**
 * Created by james.lendrem on 10/08/2017.
 */

public class MessageViewHolder {

        View row;
        CardView bubble;
        TextView messageTextView;
        TextView timestampTextView;

        public MessageViewHolder(View convertView) {
            row = convertView;
            bubble = (CardView) convertView.findViewById(R.id.bubble);
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
