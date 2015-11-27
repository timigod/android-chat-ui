package co.devcenter.androiduilibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.devcenter.androiduilibrary.models.ChatMessage;

/**
 * Created by onyekachi on 11/18/15.
 */
public class ChatViewListAdapter extends BaseAdapter {

    ArrayList<ChatMessage> chatMessages;
    Context mContext;
    LayoutInflater mInflater;

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
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.chat_item_sent, parent, false);
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


    public void addSentMessage(ChatMessage message) {
        chatMessages.add(message);
        notifyDataSetChanged();
    }
}
