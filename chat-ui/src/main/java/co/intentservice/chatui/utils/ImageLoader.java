package co.intentservice.chatui.utils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class ImageLoader {

    public static void load(String url, SimpleDraweeView simpleDraweeView){

        if(simpleDraweeView == null || url == null){
            return;
        }

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(url)
                .setAutoPlayAnimations(true)
                .build();

        simpleDraweeView.setController(controller);
    }
}
