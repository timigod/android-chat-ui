package co.devcenter.android.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.devcenter.android.ChatView;

public class SeperateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seperate);

        ChatView chatView = (ChatView) findViewById(R.id.chat_view);

        chatView.setChatListener(new ChatView.SimpleChatListener());
    }
}
