package co.devcenter.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import co.devcenter.android.ChatView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChatView chatView = (ChatView) findViewById(R.id.chat_view);
        chatView.setChatListener(new ChatView.ChatListener() {
            @Override
            public void userIsTyping() {

            }

            @Override
            public void userHasStoppedTyping() {

            }

            @Override
            public void onMessageReceived(String message, long timestamp) {

            }

            @Override
            public boolean sendMessage(String message, long timestamp) {
                return true;
            }
        });
    }
}
