package co.intentservice.chatui.views;

import android.content.Context;
import android.view.View;

/**
 * Created by nemo on 12/08/17.
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
