package co.intentservice.chatui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Message view interface, we have created this so people can define their own views with
 * extra methods for extensibility purposes
 *
 * Created by James Lendrem
 */

public abstract class MessageView extends FrameLayout {

    public abstract void setMessage(String message);
    public abstract void setTimestamp(String timestamp);
    public abstract void setBackground(int background);
    public abstract void setElevation(float elevation);

    public MessageView(Context context) {

        super(context);

    }

    public MessageView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

}
