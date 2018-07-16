package co.intentservice.chatui.sample;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri uri = Uri.parse("https://abs.twimg.com/sticky/default_profile_images/default_profile_400x400.png");

        ChatView chatView = (ChatView) findViewById(R.id.chat_view);
        chatView.addMessage(new ChatMessage("Message received with profile pic", System.currentTimeMillis(), ChatMessage.Type.RECEIVED,uri));
        chatView.addMessage(new ChatMessage("Message received", System.currentTimeMillis(), ChatMessage.Type.RECEIVED));
        chatView.addMessage(new ChatMessage("A message with a sender name",
                System.currentTimeMillis(), ChatMessage.Type.RECEIVED, "Ryan Java"));
        chatView.addMessage(new ChatMessage("A message with a sender name and profile pic",
                System.currentTimeMillis(), ChatMessage.Type.RECEIVED, "Ryan Java",uri));
        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override
            public boolean sendMessage(ChatMessage chatMessage) {
                return true;
            }
        });

        chatView.setTypingListener(new ChatView.TypingListener() {
            @Override
            public void userStartedTyping() {

            }

            @Override
            public void userStoppedTyping() {

            }
        });
    }
}
