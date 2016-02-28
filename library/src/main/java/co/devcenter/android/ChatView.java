package co.devcenter.android;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import co.devcenter.android.models.ChatMessage;
import co.devcenter.android.models.ChatMessage.Type;

/**
 * Created by timi on 17/11/2015.
 */
public class ChatView extends LinearLayout {

    private CardView inputBar;
    private CardView inputFrame;
    private ListView chatListView;
    private EditText inputEditText;

    private FloatingActionButton sendButton;

    private boolean previousFocusState = false;

    private ChatListener chatListener;
    private ChatViewListAdapter chatViewListAdapter;

    // Fields controlling chat list items
    int bubbleElevation;
    Drawable bubbleBackgroundRcv;
    Drawable bubbleBackgroundSend;
    int messageTextSize;
    int messageTextColorRcv;
    int messageTextColorSend;
    int tStampTextSize;
    int tStampTextColorRcv;
    int tStampTextColorSend;


    public ChatView(Context context) {
        this(context, null);
    }

    public ChatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        setOrientation(VERTICAL);

        LayoutInflater.from(getContext()).inflate(R.layout.chat_view, this, true);

        chatListView = (ListView) findViewById(R.id.chat_list);
        inputBar = (CardView) findViewById(R.id.input_bar);
        inputFrame = (CardView) findViewById(R.id.input_frame);
        inputEditText = (EditText) findViewById(R.id.input_edit_text);
        sendButton = (FloatingActionButton) findViewById(R.id.sendButton);


        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ChatView, defStyleAttr,
                R.style.ChatViewDefault);


        int inputBarBackgroundColor = a.getColor(R.styleable.ChatView_inputBarBackgroundColor, -1);
        int inputBarInsetLeft = a.getDimensionPixelSize(R.styleable.ChatView_inputBarInsetLeft, 0);
        int inputBarInsetTop = a.getDimensionPixelSize(R.styleable.ChatView_inputBarInsetTop, 0);
        int inputBarInsetRight = a.getDimensionPixelSize(R.styleable.ChatView_inputBarInsetRight, 0);
        int inputBarInsetBottom = a.getDimensionPixelSize(R.styleable.ChatView_inputBarInsetBottom, 0);

        inputBar.setCardBackgroundColor(inputBarBackgroundColor);
        inputBar.setContentPadding(inputBarInsetLeft,
                inputBarInsetTop, inputBarInsetRight, inputBarInsetBottom);

        int inputBackground = a.getColor(R.styleable.ChatView_inputBackgroundColor, -1);
        float inputElevation = a.getDimension(R.styleable.ChatView_inputElevation, 0f);
        inputFrame.setCardBackgroundColor(inputBackground);
        inputFrame.setCardElevation(inputElevation);

        int inputTextSize = context.getResources().getDimensionPixelSize(R.dimen.default_input_text_size);
        int inputTextColor = ContextCompat.getColor(context, R.color.black);
        int inputHintColor = ContextCompat.getColor(context, R.color.main_color_gray);

        if (a.hasValue(R.styleable.ChatView_inputTextAppearance)) {

            // Get id of the resource that holds the textAppearance
            final int textAppearanceId = a.getResourceId(
                    R.styleable.ChatView_inputTextAppearance, 0);

            TypedArray atp = getContext().obtainStyledAttributes(textAppearanceId,
                    R.styleable.ChatViewInputTextAppearance);

            if (atp.hasValue(R.styleable.ChatView_inputTextSize)) {
                inputTextSize = a.getDimensionPixelSize(R.styleable.ChatView_inputTextSize, inputTextSize);
            }

            if (atp.hasValue(R.styleable.ChatView_inputTextColor)) {
                inputTextColor = a.getColor(R.styleable.ChatView_inputTextColor, inputTextColor);
            }

            if (atp.hasValue(R.styleable.ChatView_inputHintColor)) {
                inputHintColor = a.getColor(R.styleable.ChatView_inputHintColor, inputHintColor);
            }

            atp.recycle();
        }

        inputTextSize = (int) a.getDimension(R.styleable.ChatView_inputTextSize, inputTextSize);
        inputTextColor = a.getColor(R.styleable.ChatView_inputTextColor, inputTextColor);
        inputHintColor = a.getColor(R.styleable.ChatView_inputHintColor, inputHintColor);

        inputEditText.setTextColor(inputTextColor);
        inputEditText.setHintTextColor(inputHintColor);
        inputEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, inputTextSize);

        boolean sendButtonVisible = a.getBoolean(R.styleable.ChatView_sendBtnVisible, true);
        if (!sendButtonVisible) sendButton.setVisibility(GONE);

        int sendButtonBgTint = a.getColor(R.styleable.ChatView_sendBtnBackgroundTint, -1);
        int sendButtonIconTint = a.getColor(R.styleable.ChatView_sendBtnIconTint, Color.WHITE);
        int sendButtonElevation = a.getDimensionPixelSize(R.styleable.ChatView_sendBtnElevation, 0);
        Drawable sendButtonIcon = a.getDrawable(R.styleable.ChatView_sendBtnIcon);

        sendButton.setBackgroundTintList(ColorStateList.valueOf(sendButtonBgTint));
        sendButton.setImageDrawable(sendButtonIcon);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sendButton.setElevation(sendButtonElevation);
        }
        Drawable d = DrawableCompat.wrap(sendButton.getDrawable());
        d.setColorFilter(sendButtonIconTint, PorterDuff.Mode.SRC_IN);

        boolean useEditorAction = a.getBoolean(R.styleable.ChatView_inputUseEditorAction, false);

        a.recycle();

        if (useEditorAction) {
            setupEditorAction();
        } else {
            inputEditText.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_FLAG_MULTI_LINE
                    | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
                    | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            );
        }

        chatViewListAdapter = new ChatViewListAdapter(context);
        chatListView.setAdapter(chatViewListAdapter);

        sendButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                long stamp = System.currentTimeMillis();
                String message = inputEditText.getText().toString();

                if (!TextUtils.isEmpty(message)) {
                    sendMessage(message, stamp);
                }

            }
        });

        setUserTypingListener();
        setUserStoppedTypingListener();
    }

    private void setupEditorAction() {
        inputEditText.setInputType(InputType.TYPE_CLASS_TEXT
                | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT
                | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        );
        inputEditText.setImeOptions(EditorInfo.IME_ACTION_SEND);
        inputEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    long stamp = System.currentTimeMillis();
                    String message = inputEditText.getText().toString();

                    if (!TextUtils.isEmpty(message)) {
                        sendMessage(message, stamp);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void setUserTypingListener() {
        inputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0 && chatListener != null){
                    chatListener.userIsTyping();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void setUserStoppedTypingListener() {
        inputEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (previousFocusState && !hasFocus && chatListener != null) {
                    chatListener.userHasStoppedTyping();
                }

                previousFocusState = hasFocus;
            }
        });
    }


    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        return super.addViewInLayout(child, index, params);
    }

    public String getTypedMessage() {

        return inputEditText.getText().toString();
    }

    public void setChatListener(ChatListener chatListener) {

        this.chatListener = chatListener;
    }

    public void sendMessage(String message, long stamp) {

        ChatMessage chatMessage = new ChatMessage(message, stamp, Type.SENT);

        if (chatListener != null && chatListener.sendMessage(message, stamp)) {

            chatViewListAdapter.addMessage(chatMessage);
            inputEditText.setText("");
        }
    }

    public void newMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message,
            System.currentTimeMillis(), Type.RECEIVED);

        chatViewListAdapter.addMessage(chatMessage);

        // notify listener
        if (chatListener != null)
            chatListener.onMessageReceived(chatMessage.getMessage(), chatMessage.getTimestamp());
    }

    public void newMessage(ChatMessage chatMessage) {

        chatViewListAdapter.addMessage(chatMessage);

        // notify listener
        if (chatListener != null)
            chatListener.onMessageReceived(chatMessage.getMessage(), chatMessage.getTimestamp());
    }



    public EditText getInputEditText() {

        return inputEditText;
    }

    public FloatingActionButton getSendButton() {

        return sendButton;
    }



    public class ChatViewListAdapter extends BaseAdapter {

        public final int STATUS_SENT = 0;
        public final int STATUS_RECEIVED = 1;

        ArrayList<ChatMessage> chatMessages;

        Context context;
        LayoutInflater inflater;

        public ChatViewListAdapter(Context context) {
            this.chatMessages = new ArrayList<>();
            this.context = context;
            this.inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {

            return chatMessages.size();
        }

        @Override
        public Object getItem(int position) {

            return chatMessages.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public int getItemViewType(int position) {

            return chatMessages.get(position).getType().ordinal();
        }

        @Override
        public int getViewTypeCount() {

            return 2;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            int type = getItemViewType(position);

            if (convertView == null) {

                switch (type) {
                    case STATUS_SENT:
                        convertView = inflater.inflate(R.layout.chat_item_sent, parent, false);
                        break;
                    case STATUS_RECEIVED:
                        convertView = inflater.inflate(R.layout.chat_item_rcv, parent, false);
                        break;
                }

                holder = new ViewHolder(convertView);
                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }

            holder.getMessageTextView().setText(chatMessages.get(position).getMessage());
            holder.getTimestampTextView().setText(chatMessages.get(position).getFormattedTime());

            return convertView;
        }

        public void addMessage(ChatMessage message) {
            chatMessages.add(message);
            notifyDataSetChanged();
        }

        class ViewHolder {
            View row;
            TextView messageTextView;
            TextView timestampTextView;

            public ViewHolder(View convertView) {

                row = convertView;
            }

            public TextView getMessageTextView() {
                if (messageTextView == null) {
                    messageTextView = (TextView) row.findViewById(R.id.message_text_view);
                }

                return messageTextView;
            }

            public TextView getTimestampTextView() {
                if (timestampTextView == null) {
                    timestampTextView= (TextView) row.findViewById(R.id.timestamp_text_view);
                }

                return timestampTextView;
            }
        }
    }



    public interface ChatListener {

        void userIsTyping();

        void userHasStoppedTyping();

        void onMessageReceived(String message, long timestamp);

        /**
         * Called when the user hits the send button
         *
         * @param message The message that was typed as a String
         * @param timestamp The timestamp in seconds since Jan 01 1970, 12:00am
         * @return true to display the message in the chat list, false to ignore it
         */
        boolean sendMessage(String message, long timestamp);
    }

    /**
     * A {@link ChatListener} ChatListener that provides no operations
     * for methods. Only override what you need to.
     *
     * <p>You should override sendMessage() and handle when the user hits send</p>
     */
    public static class SimpleChatListener implements ChatListener {

        @Override
        public void userIsTyping() {
            // No - op
        }

        @Override
        public void userHasStoppedTyping() {
            // No - op
        }

        @Override
        public void onMessageReceived(String message, long timestamp) {
            // No - op
        }

        @Override
        public boolean sendMessage(String message, long timestamp) {
            // No - op
            return false;
        }
    }
}
