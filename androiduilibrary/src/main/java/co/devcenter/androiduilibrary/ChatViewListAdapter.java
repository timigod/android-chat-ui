package co.devcenter.androiduilibrary;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.devcenter.androiduilibrary.models.ChatMessage;
import co.devcenter.androiduilibrary.models.ChatMessage.Status;

/**
 * Created by onyekachi on 11/18/15.
 */
public class ChatViewListAdapter extends BaseAdapter {

    ArrayList<ChatMessage> chatMessages;
    Context mContext;
    LayoutInflater mInflater;

    public final int STATUS_SENT = 0;
    public final int STATUS_RECEIVED = 1;

    public ChatViewListAdapter(Context context) {
        mContext = context;
        chatMessages = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
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
        return chatMessages.get(position).getStatus().ordinal();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Status status = chatMessages.get(position).getStatus();

        int type = getItemViewType(position);
        ViewHolder holder;

        if (convertView == null) {
            switch (type) {
                case STATUS_SENT:
                    convertView = mInflater.inflate(R.layout.chat_item_sent, parent, false);
                    break;
                case STATUS_RECEIVED:
                    convertView = mInflater.inflate(R.layout.chat_item_rcv, parent, false);
                    break;
            }

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.getMessageTextView().setText(chatMessages.get(position).getMessage());
        return convertView;

    }


    static class ViewHolder {
        View row;
        TextView messageTextView;

        public ViewHolder(View convertView) {
            row = convertView;
        }

        public TextView getMessageTextView() {
            if (messageTextView == null) {
                messageTextView = (TextView) row.findViewById(R.id.message_text_view);
            }

            return messageTextView;
        }
    }


    public void addMessage(ChatMessage message) {
        chatMessages.add(message);
        notifyDataSetChanged();
    }
}
