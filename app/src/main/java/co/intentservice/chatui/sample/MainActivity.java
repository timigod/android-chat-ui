package co.intentservice.chatui.sample;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import co.intentservice.chatui.ChatView;
import co.intentservice.chatui.models.ChatMessage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] permissions = { Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, permissions, 1000);

        ChatView chatView = (ChatView) findViewById(R.id.chat_view);

        chatView.addMessage(new ChatMessage("Message received", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, ChatMessage.ContentType.TEXT));

        chatView.addMessage(new ChatMessage("https://media.giphy.com/media/LHZyixOnHwDDy/giphy.gif", System.currentTimeMillis(), ChatMessage.Type.SENT, ChatMessage.ContentType.IMAGE));

        chatView.addMessage(new ChatMessage("https://media.giphy.com/media/11JTxkrmq4bGE0/giphy.gif", System.currentTimeMillis(), ChatMessage.Type.SENT, ChatMessage.ContentType.IMAGE));

        chatView.addMessage(new ChatMessage("A message with a sender name", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, ChatMessage.ContentType.TEXT, "Ryan Java"));

        chatView.addMessage(new ChatMessage("https://media.giphy.com/media/IRFQYGCokErS0/giphy.gif", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, ChatMessage.ContentType.IMAGE));

        chatView.addMessage(new ChatMessage("https://pbs.twimg.com/profile_images/972154872261853184/RnOg6UyU_400x400.jpg", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, ChatMessage.ContentType.IMAGE));

        chatView.addMessage(new ChatMessage("file:///storage/emulated/0/Download/julito.gif", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, ChatMessage.ContentType.IMAGE));

        chatView.addMessage(new ChatMessage("file:///storage/emulated/0/Download/hahaha.gif", System.currentTimeMillis(), ChatMessage.Type.RECEIVED, ChatMessage.ContentType.IMAGE));

        chatView.setOnSentMessageListener(new ChatView.OnSentMessageListener() {
            @Override public boolean sendMessage(ChatMessage chatMessage) {
                Log.d("MainActivity", "setOnSentMessageListener()");
                return true;
            }
        });

        chatView.setTypingListener(new ChatView.TypingListener() {
            @Override public void userStartedTyping() {
                Log.d("MainActivity", "userStartedTyping()");
            }

            @Override public void userStoppedTyping() {
                Log.d("MainActivity", "userStoppedTyping()");
            }
        });

        chatView.setOnAttachListener(new ChatView.OnAttachListener() {
            @Override public void attachFile() {
                Toast.makeText(MainActivity.this, "gonna attach something :)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
