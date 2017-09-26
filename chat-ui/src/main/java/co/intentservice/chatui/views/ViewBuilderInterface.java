package co.intentservice.chatui.views;

import android.content.Context;
import android.view.View;

/**
 * Interface for the viewbuilder, is used so that people can create their own
 * viewbuilders to create custom views.
 */

public interface ViewBuilderInterface {

    View buildRecvView(Context context);
    View buildSentView(Context context);

}
