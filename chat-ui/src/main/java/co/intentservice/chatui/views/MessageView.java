package co.intentservice.chatui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * MessageView is used to help support custom views without defining a custom viewholder,
 * any view that implements this interface is guaranteed to work with the chat-ui.
 *
 * Created by James Lendrem
 */

public abstract class MessageView extends FrameLayout {

    /**
     * Method to set the messages text in the view so it can be displayed on the screen.
     * @param message   The message that you want to be displayed.
     */
    public abstract void setMessage(String message);

    /**
     * Method to set the timestamp that the message was received or sent on the screen.
     * @param timestamp The timestamp that you want to be displayed.
     */
    public abstract void setTimestamp(String timestamp);

    /**
     * Method to set the background color that you want to use in your message.
     * @param background The background that you want to be displayed.
     */
    public abstract void setBackground(int background);

    /**
     * Method to set the elevation of the view.
     * @param elevation The elevation that you want the view to be displayed at.
     */
    public abstract void setElevation(float elevation);

    /**
     * Constructs a new message view.
     * @param context
     */
    public MessageView(Context context) {

        super(context);

    }

    /**
     * Constructs a new message view with attributes, this constructor is used when we create a
     * message view using XML.
     * @param context
     * @param attrs
     */
    public MessageView(Context context, AttributeSet attrs) {

        super(context, attrs);

    }

}
