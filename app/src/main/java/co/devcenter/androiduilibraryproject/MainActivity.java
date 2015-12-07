package co.devcenter.androiduilibraryproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import co.devcenter.androiduilibrary.ChatView;
import co.devcenter.androiduilibrary.ChatViewEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ChatView chatView = (ChatView) findViewById(R.id.chat_view);
        chatView.setEventListener(new ChatViewEventListener() {
            @Override
            public void userIsTyping() {

            }

            @Override
            public void userHasStoppedTyping() {

            }
        });

        chatView.getSendButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatView.sendMessage();
            }
        });
    }
}
