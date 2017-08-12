package co.intentservice.chatui.views;

import android.content.Context;
import android.view.View;

/**
 * Created by nemo on 12/08/17.
 */

public interface ViewBuilderInterface {

    View buildRecvView(Context context);
    View buildSentView(Context context);

}
