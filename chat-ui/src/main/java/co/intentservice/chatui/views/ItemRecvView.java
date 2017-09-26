package co.intentservice.chatui.views;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import co.intentservice.chatui.R;

/**
 * View for received chat messages.
 *
 * Created by James Lendrem
 */

public class ItemRecvView extends MessageView {

    private CardView bubble;
    private TextView messageTextView, timestampTextView;

    public void setMessage(String message) {

        if (messageTextView == null) {

            messageTextView = (TextView) findViewById(R.id.message_text_view);

        }

        messageTextView.setText(message);


    }

    public void setTimestamp(String timestamp) {

        if (timestampTextView == null) {

            timestampTextView = (TextView) findViewById(R.id.timestamp_text_view);

        }

        timestampTextView.setText(timestamp);

    }

    public void setBackground(@ColorInt int background) {

        if (bubble == null) {

            this.bubble = (CardView) findViewById(R.id.bubble);

        }

        bubble.setCardBackgroundColor(background);

    }

    public void setElevation(float elevation) {

        if (bubble == null) {

            this.bubble = (CardView) findViewById(R.id.bubble);

        }

        bubble.setCardElevation(elevation);

    }

    public ItemRecvView(Context context) {

        super(context);
        initializeView(context);

    }

    private void initializeView(Context context) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chat_item_rcv, this);

        this.bubble = (CardView) findViewById(R.id.bubble);
        this.messageTextView = (TextView) findViewById(R.id.message_text_view);
        this.timestampTextView = (TextView) findViewById(R.id.timestamp_text_view);

    }

}
