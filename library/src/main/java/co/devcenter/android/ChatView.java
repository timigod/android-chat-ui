package co.devcenter.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.devcenter.android.fab.FloatingActionsMenu;
import co.devcenter.android.models.ChatMessage;
import co.devcenter.android.models.ChatMessage.Type;

/**
 * Created by timi on 17/11/2015.
 */
public class ChatView extends RelativeLayout {

    private CardView inputFrame;
    private ListView chatListView;
    private EditText inputEditText;

    private FloatingActionsMenu actionsMenu;
    private boolean previousFocusState = false, useEditorAction, isTyping;
    private Runnable typingTimerRunnable = new Runnable() {
        @Override
        public void run() {
            if (isTyping) {
                isTyping = false;
                if (typingListener != null)
                    typingListener.userStoppedTyping();
            }
        }
    };
    private TypingListener typingListener;
    private OnSentMessageListener onSentMessageListener;
    private ChatViewListAdapter chatViewListAdapter;

    private int inputFrameBackgroundColor, backgroundColor;
    private int inputTextSize, inputTextColor, inputHintColor;
    private int sendButtonBackgroundTint, sendButtonIconTint, sendButtonElevation;
    private float inputElevation, bubbleElevation;
    private int bubbleBackgroundRcv, bubbleBackgroundSend; // Drawables cause cardRadius issues. Better to use background color
    private Drawable sendButtonIcon, buttonDrawable;
    private TypedArray attributes, textAppearanceAttributes;
    private Context context;


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
        LayoutInflater.from(getContext()).inflate(R.layout.chat_view, this, true);
        this.context = context;
        initializeViews();
        getXMLAttributes(attrs, defStyleAttr);
        setViewAttributes();
        setListAdapter();
        setButtonClickListeners();
        setUserTypingListener();
        setUserStoppedTypingListener();
    }

    private void initializeViews() {
        chatListView = (ListView) findViewById(R.id.chat_list);
        inputFrame = (CardView) findViewById(R.id.input_frame);
        inputEditText = (EditText) findViewById(R.id.input_edit_text);
        actionsMenu = (FloatingActionsMenu) findViewById(R.id.sendButton);
    }

    private void getXMLAttributes(AttributeSet attrs, int defStyleAttr) {
        attributes = context.obtainStyledAttributes(attrs, R.styleable.ChatView, defStyleAttr, R.style.ChatViewDefault);
        getChatViewBackgroundColor();
        getAttributesForInputFrame();
        getAttributesForInputText();
        getAttributesForSendButton();
        getUseEditorAction();
        getAttributesForBubbles();
        attributes.recycle();
    }

    private void setListAdapter() {
        chatViewListAdapter = new ChatViewListAdapter(context);
        chatListView.setAdapter(chatViewListAdapter);
    }


    private void setViewAttributes() {
        setChatViewBackground();
        setInputFrameAttributes();
        setInputTextAttributes();
        setSendButtonAttributes();
    }

    private void getChatViewBackgroundColor() {
        backgroundColor = attributes.getColor(R.styleable.ChatView_backgroundColor, -1);
    }

    private void getAttributesForBubbles() {
        bubbleBackgroundRcv = attributes.getColor(R.styleable.ChatView_bubbleBackgroundRcv, ContextCompat.getColor(context, R.color.default_bubble_color_rcv));
        bubbleBackgroundSend = attributes.getColor(R.styleable.ChatView_bubbleBackgroundSend, ContextCompat.getColor(context, R.color.default_bubble_color_send));
    }


    private void getAttributesForInputFrame() {
        inputFrameBackgroundColor = attributes.getColor(R.styleable.ChatView_inputBackgroundColor, -1);
        inputElevation = attributes.getDimension(R.styleable.ChatView_inputElevation, 0f);
    }

    private void setInputFrameAttributes() {
        inputFrame.setCardBackgroundColor(inputFrameBackgroundColor);
        inputFrame.setCardElevation(inputElevation);
    }

    private void setChatViewBackground() {
        this.setBackgroundColor(backgroundColor);
    }

    private void getAttributesForInputText() {
        setInputTextDefaults();
        if (hasStyleResourceSet()) {
            setTextAppearanceAttributes();
            setInputTextSize();
            setInputTextColor();
            setInputHintColor();
            textAppearanceAttributes.recycle();
        }
        overrideTextStylesIfSetIndividually();
    }

    private void setTextAppearanceAttributes() {
        final int textAppearanceId = attributes.getResourceId(R.styleable.ChatView_inputTextAppearance, 0);
        textAppearanceAttributes = getContext().obtainStyledAttributes(textAppearanceId, R.styleable.ChatViewInputTextAppearance);
    }

    private void setInputTextAttributes() {
        inputEditText.setTextColor(inputTextColor);
        inputEditText.setHintTextColor(inputHintColor);
        inputEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, inputTextSize);
    }

    private void getAttributesForSendButton() {
        sendButtonBackgroundTint = attributes.getColor(R.styleable.ChatView_sendBtnBackgroundTint, -1);
        sendButtonIconTint = attributes.getColor(R.styleable.ChatView_sendBtnIconTint, Color.WHITE);
        sendButtonElevation = attributes.getDimensionPixelSize(R.styleable.ChatView_sendBtnElevation, 0);
        sendButtonIcon = attributes.getDrawable(R.styleable.ChatView_sendBtnIcon);
    }

    private void setSendButtonAttributes() {
        actionsMenu.getSendButton().setBackgroundColor(sendButtonBackgroundTint);
        actionsMenu.setIconDrawable(sendButtonIcon);

        ViewCompat.setElevation(actionsMenu.getSendButton(), sendButtonElevation);

        buttonDrawable = actionsMenu.getIconDrawable();
        buttonDrawable.setColorFilter(sendButtonIconTint, PorterDuff.Mode.SRC_IN);
    }

    private void getUseEditorAction() {
        useEditorAction = attributes.getBoolean(R.styleable.ChatView_inputUseEditorAction, false);
    }

    private void setUseEditorAction() {
        if (useEditorAction) {
            setupEditorAction();
        } else {
            inputEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        }
    }

    private boolean hasStyleResourceSet() {
        return attributes.hasValue(R.styleable.ChatView_inputTextAppearance);
    }

    private void setInputTextDefaults() {
        inputTextSize = context.getResources().getDimensionPixelSize(R.dimen.default_input_text_size);
        inputTextColor = ContextCompat.getColor(context, R.color.black);
        inputHintColor = ContextCompat.getColor(context, R.color.main_color_gray);
    }

    private void setInputTextSize() {
        if (textAppearanceAttributes.hasValue(R.styleable.ChatView_inputTextSize)) {
            inputTextSize = attributes.getDimensionPixelSize(R.styleable.ChatView_inputTextSize, inputTextSize);
        }
    }

    private void setInputTextColor() {
        if (textAppearanceAttributes.hasValue(R.styleable.ChatView_inputTextColor)) {
            inputTextColor = attributes.getColor(R.styleable.ChatView_inputTextColor, inputTextColor);
        }
    }

    private void setInputHintColor() {
        if (textAppearanceAttributes.hasValue(R.styleable.ChatView_inputHintColor)) {
            inputHintColor = attributes.getColor(R.styleable.ChatView_inputHintColor, inputHintColor);
        }
    }

    private void overrideTextStylesIfSetIndividually() {
        inputTextSize = (int) attributes.getDimension(R.styleable.ChatView_inputTextSize, inputTextSize);
        inputTextColor = attributes.getColor(R.styleable.ChatView_inputTextColor, inputTextColor);
        inputHintColor = attributes.getColor(R.styleable.ChatView_inputHintColor, inputHintColor);
    }

    private void setupEditorAction() {
        inputEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_AUTO_CORRECT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
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

    private void setButtonClickListeners() {

        actionsMenu.getSendButton().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Hello I'm Timigod", Toast.LENGTH_SHORT).show();

                if (actionsMenu.isExpanded()) {
                    actionsMenu.collapse();
                    return;
                }

                long stamp = System.currentTimeMillis();
                String message = inputEditText.getText().toString();
                if (!TextUtils.isEmpty(message)) {
                    sendMessage(message, stamp);
                }

            }
        });

        actionsMenu.getSendButton().setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                actionsMenu.expand();
                Toast.makeText(context, "Hello I'm Timi", Toast.LENGTH_SHORT).show();
                return true;
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
                if (s.length() > 0) {
                    if (!isTyping) {
                        isTyping = true;
                        if (typingListener != null)
                            typingListener.userStartedTyping();
                    }
                    removeCallbacks(typingTimerRunnable);
                    postDelayed(typingTimerRunnable, 1500);
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
                if (previousFocusState && !hasFocus && typingListener != null) {
                    typingListener.userStoppedTyping();
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

    public void setTypingListener(TypingListener typingListener) {
        this.typingListener = typingListener;
    }

    public void setOnSentMessageListener(OnSentMessageListener onSentMessageListener) {
        this.onSentMessageListener = onSentMessageListener;
    }

    private void sendMessage(String message, long stamp) {

        ChatMessage chatMessage = new ChatMessage(message, stamp, Type.SENT);
        if (onSentMessageListener != null && onSentMessageListener.sendMessage(chatMessage)) {
            chatViewListAdapter.addMessage(chatMessage);
            inputEditText.setText("");
        }
    }

    public void addMessage(ChatMessage chatMessage) {
        chatViewListAdapter.addMessage(chatMessage);
    }

    public void addMessages(ArrayList<ChatMessage> messages) {
        chatViewListAdapter.addMessages(messages);
    }


    public EditText getInputEditText() {
        return inputEditText;
    }

    public FloatingActionsMenu getActionsMenu() {
        return actionsMenu;
    }


    public interface TypingListener {

        void userStartedTyping();

        void userStoppedTyping();

    }

    public interface OnSentMessageListener {
        boolean sendMessage(ChatMessage chatMessage);
    }

    private class ChatViewListAdapter extends BaseAdapter {
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
            holder.setBackground(type);

            return convertView;
        }

        private void addMessage(ChatMessage message) {
            chatMessages.add(message);
            notifyDataSetChanged();
        }

        private void addMessages(ArrayList<ChatMessage> chatMessages) {
            chatMessages.addAll(chatMessages);
            notifyDataSetChanged();
        }

        class ViewHolder {
            View row;
            CardView bubble;
            TextView messageTextView;
            TextView timestampTextView;

            private ViewHolder(View convertView) {
                row = convertView;
                bubble = (CardView) convertView.findViewById(R.id.bubble);
            }

            private TextView getMessageTextView() {
                if (messageTextView == null) {
                    messageTextView = (TextView) row.findViewById(R.id.message_text_view);
                }
                return messageTextView;
            }

            private TextView getTimestampTextView() {
                if (timestampTextView == null) {
                    timestampTextView = (TextView) row.findViewById(R.id.timestamp_text_view);
                }

                return timestampTextView;
            }

            private void setBackground(int messageType) {
                int background = ContextCompat.getColor(context, R.color.cardview_light_background);
                switch (messageType) {
                    case STATUS_RECEIVED:
                        background = bubbleBackgroundRcv;
                        break;
                    case STATUS_SENT:
                        background = bubbleBackgroundSend;
                        break;
                }

                bubble.setCardBackgroundColor(background);
            }
        }
    }
}
