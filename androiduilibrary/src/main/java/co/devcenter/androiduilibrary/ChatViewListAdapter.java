package co.devcenter.androiduilibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by onyekachi on 11/18/15.
 */
public class ChatViewListAdapter extends BaseAdapter {

    ArrayList<String> mListViewData;
    Context mContext;
    LayoutInflater mInflater;

    public ChatViewListAdapter(Context context){
        mContext = context;
        mListViewData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mListViewData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListViewData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.chat_item_sent, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.getChatMessageView().setText(mListViewData.get(position));

        return convertView;
    }


    static class ViewHolder{

        View row;
        TextView chatMessage;

        public ViewHolder(View convertView){
            row = convertView;
        }

        public TextView getChatMessageView(){
            if(chatMessage == null){
                chatMessage = (TextView) row.findViewById(R.id.chat_message);
            }

            return chatMessage;
        }
    }


    public void addSentMessage(String message){
        mListViewData.add(message);
        notifyDataSetChanged();
    }
}
