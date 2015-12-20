package co.devcenter.androiduilibrary;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import co.devcenter.androiduilibrary.models.ChatMessage;
import co.devcenter.androiduilibrary.models.ChatMessage.Status;

/**
 * Created by timi on 17/11/2015.
 */
public class ChatView extends LinearLayout {

    private ChatViewListAdapter chatViewListAdapter;
    private ListView chatListView;

    private EditText inputEditText;
    private SendButton sendButton;
    private ChatViewEventListener eventListener;
    private boolean previousFocusStatus = false;


    public ChatView(Context context){
        super(context);
        init(context);
    }

    public ChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    private void init(Context context){
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.chat_view, this);

        chatListView = (ListView)findViewById(R.id.chat);
        inputEditText = (EditText)findViewById(R.id.inputEditText);
        sendButton = (SendButton) findViewById(R.id.sendButton);

        chatViewListAdapter = new ChatViewListAdapter(context);
        chatListView.setAdapter(chatViewListAdapter);
    }


    public void setEventListener(final ChatViewEventListener eventListener){
        this.eventListener = eventListener;
        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0){
                    eventListener.userIsTyping();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    previousFocusStatus = true;
                } else if (!hasFocus) {
                    previousFocusStatus = false;
                } else if (previousFocusStatus && !hasFocus) {
                    eventListener.userHasStoppedTyping();
                }
            }
        });
    }

    public String getTypedString(){
        return  inputEditText.getText().toString();
    }

    public void sendMessage(ChatMessage message){
        chatViewListAdapter.addMessage(message);
        inputEditText.setText("");
    }

    public void sendMessage(){
        ChatMessage chatMessage = new ChatMessage(inputEditText.getText().toString(), System.currentTimeMillis(), Status.SENT);
        inputEditText.setText("");
        chatViewListAdapter.addMessage(chatMessage);
    }

    public void receiveMessage(String message){
        ChatMessage chatMessage = new ChatMessage(message, System.currentTimeMillis(), Status.RECEIVED);
        chatViewListAdapter.addMessage(chatMessage);
    }

    public SendButton getSendButton(){
        return sendButton;
    }
}
