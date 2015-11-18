package co.devcenter.androiduilibrary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;

/**
 * Created by timi on 17/11/2015.
 */
public class ChatView extends LinearLayout {


    ChatViewListAdapter mChatViewListAdapter;
    ListView mChatViewList;

    EditText mMessageBodyField;
    FloatingActionButton mSendButton;



    public ChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.chat_view, this);

        //retrieve views
        mChatViewList = (ListView)findViewById(R.id.chat);
        mMessageBodyField = (EditText)findViewById(R.id.messageBodyField);
        mSendButton = (FloatingActionButton) findViewById(R.id.sendButton);


        //configure mChatListView
        mChatViewListAdapter = new ChatViewListAdapter(context);
        mChatViewList.setAdapter(mChatViewListAdapter);


        //trigger message sending
        mSendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(mMessageBodyField.getText().toString());
            }
        });

    }


    public void sendMessage(String message){
        mChatViewListAdapter.addSentMessage(message);
        mMessageBodyField.setText("");
    }



}
