package co.intentservice.chatui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import co.intentservice.chatui.R;
import co.intentservice.chatui.models.ChatMessage;
import co.intentservice.chatui.viewholders.MessageViewHolder;
import co.intentservice.chatui.views.ViewBuilder;
import co.intentservice.chatui.views.ViewBuilderInterface;

/**
 * List Adapter for use in the recycler view to display messages using the Message View Holder
 *
 * Created by Timi
 * Extended by James Lendrem
 */

public class ChatViewListAdapter extends BaseAdapter {

    public final int STATUS_SENT = 0;
    public final int STATUS_RECEIVED = 1;

    private int bubbleBackgroundRcv, bubbleBackgroundSend;
    private float bubbleElevation;
    private ViewBuilderInterface viewBuilder = new ViewBuilder();

    ArrayList<ChatMessage> chatMessages;

    Context context;
    LayoutInflater inflater;

    public ChatViewListAdapter(Context context, ViewBuilderInterface viewBuilder, int bubbleBackgroundRcv, int bubbleBackgroundSend, float bubbleElevation) {
        this.chatMessages = new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.bubbleBackgroundRcv = bubbleBackgroundRcv;
        this.bubbleBackgroundSend = bubbleBackgroundSend;
        this.bubbleElevation = bubbleElevation;
        this.viewBuilder = viewBuilder;
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
        MessageViewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case STATUS_SENT:
                    convertView = viewBuilder.buildSentView(context);
                    break;
                case STATUS_RECEIVED:
                    convertView = viewBuilder.buildRecvView(context);
                    break;
            }

            holder = new MessageViewHolder(convertView, bubbleBackgroundRcv, bubbleBackgroundSend);
            convertView.setTag(holder);
        } else {
            holder = (MessageViewHolder) convertView.getTag();
        }

        holder.setMessage(chatMessages.get(position).getMessage());
        holder.setTimestamp(chatMessages.get(position).getFormattedTime());
        holder.setElevation(bubbleElevation);
        holder.setBackground(type);

        return convertView;
    }

    public void addMessage(ChatMessage message) {
        chatMessages.add(message);
        notifyDataSetChanged();
    }

    public void addMessages(ArrayList<ChatMessage> chatMessages) {
        this.chatMessages.addAll(chatMessages);
        notifyDataSetChanged();
    }

    public void removeMessage(int position) {
        if (this.chatMessages.size() > position) {
            this.chatMessages.remove(position);
        }
    }

    public void clearMessages() {
        this.chatMessages.clear();
        notifyDataSetChanged();
    }
}