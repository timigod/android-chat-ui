package co.intentservice.chatui.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import co.intentservice.chatui.R;

/**
 * Created by nemo on 12/08/17.
 */

public class ItemRecvView extends FrameLayout {

    public ItemRecvView(Context context) {

        super(context);
        initializeView(context);

    }

    private void initializeView(Context context) {

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chat_item_rcv, this);

    }

}
