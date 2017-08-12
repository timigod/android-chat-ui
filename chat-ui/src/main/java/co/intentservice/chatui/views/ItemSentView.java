package co.intentservice.chatui.views;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import co.intentservice.chatui.R;

/**
 * Created by nemo on 12/08/17.
 */

public class ItemSentView extends FrameLayout {

    public ItemSentView(Context context) {

        super(context);
        initializeView(context);

    }

    private void initializeView(Context context) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chat_item_sent, this);

    }

    public static View buildView(Context context) {

        return new ItemSentView(context);

    }

}
