package com.example.petdoc.ui.adapters;

import static com.example.petdoc.MainActivity.USERID;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.R;
import com.example.petdoc.data.entities.Message;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messages;
    public MessagesAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message, parent, false)
        ){};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView text = holder.itemView.findViewById(R.id.messageText);
        TextView date = holder.itemView.findViewById(R.id.messageDate);
        text.setText(messages.get(position).getText());
        date.setText(messages.get(position).getDate());
        LinearLayout ll = holder.itemView.findViewById(R.id.msg);
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) ll.getLayoutParams();

        if ((messages.get(position).getUserId() == 0 && USERID != 0) || (messages.get(position).getUserId() != 0 && USERID == 0)){
            ll.setBackgroundResource(R.drawable.server_msg_background);
            ll.setGravity(Gravity.LEFT);
            text.setGravity(Gravity.LEFT);
            date.setGravity(Gravity.LEFT);
            layoutParams.setMargins(30, layoutParams.topMargin, 100, layoutParams.bottomMargin);
        } else {
            ll.setBackgroundResource(R.drawable.user_msg_background);
            ll.setGravity(Gravity.RIGHT);
            text.setGravity(Gravity.RIGHT);
            date.setGravity(Gravity.RIGHT);
            layoutParams.setMargins(100, layoutParams.topMargin, 30, layoutParams.bottomMargin);
        }
    }

    @Override
    public int getItemCount() {
        return this.messages.size();
    }
}