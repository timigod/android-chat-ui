package co.intentservice.chatui.views;

import android.content.Context;
import android.view.View;

/**
 * Builder to create views for the Chat Client that are used to display sent and
 * received messages.
 *
 * Created by James Lendrem
 */

public class ViewBuilder implements ViewBuilderInterface {

    public View buildRecvView(Context context) {

        View view = new ItemRecvView(context);
        return view;

    }

    public View buildSentView(Context context) {

        View view = new ItemSentView(context);
        return view;

    }

}
