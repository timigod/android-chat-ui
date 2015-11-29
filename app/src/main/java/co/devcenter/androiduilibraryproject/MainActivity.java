package co.devcenter.androiduilibraryproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.devcenter.androiduilibrary.ChatView;
import co.devcenter.androiduilibrary.ChatViewEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChatView chatView = (ChatView) findViewById(R.id.chat_view);
        chatView.setEventListener(new ChatViewEventListener() {
            @Override
            public void userIsTyping() {

            }

            @Override
            public void userHasStoppedTyping() {

            }
        });
    }
}
