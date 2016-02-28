package co.devcenter.android.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.devcenter.android.ChatView;

public class NoButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_button);

        ChatView chatView = (ChatView) findViewById(R.id.chat_view);

        chatView.setChatListener(new ChatView.ChatListener() {
            @Override
            public void userIsTyping() {

                // do something while user is typing
            }

            @Override
            public void userHasStoppedTyping() {

                // do something when user has stopped typing
            }

            @Override
            public void onMessageReceived(String message, long timestamp) {

                // do something when chat view receives a message
            }

            @Override
            public boolean sendMessage(String message, long timestamp) {

                // do something when the user hits the send button
                return true;
            }
        });
    }
}
