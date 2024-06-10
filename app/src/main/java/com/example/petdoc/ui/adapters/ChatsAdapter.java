package com.example.petdoc.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.R;
import com.example.petdoc.data.entities.Chat;
import com.example.petdoc.data.entities.Message;

import java.util.List;

public class ChatsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final ChatsRecycleViewInterface chatsRecycleViewInterface;
    private List<Chat> chats;

    public Chat chat;

    public ChatsAdapter(List<Chat> chats, ChatsRecycleViewInterface chatsRecycleViewInterface) {
        this.chatsRecycleViewInterface = chatsRecycleViewInterface;
        this.chats = chats;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.chat, parent, false)
        ){};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TextView userId = holder.itemView.findViewById(R.id.userId);
        TextView lastMessageField = holder.itemView.findViewById(R.id.lastMessage);
        TextView readed = holder.itemView.findViewById(R.id.readed);

        Chat chat = chats.get(position);
        Message lastMessage = chat.getMessages().get(chat.getMessages().size()-1);
        userId.setText("User ID: "+chat.getUserid());
        if (lastMessage.getText().length() > 50){
            lastMessageField.setText(lastMessage.getText().substring(0, 47)+"...");
        } else {
            lastMessageField.setText(lastMessage.getText());
        }

        readed.setText(lastMessage.getDate().substring(0, 12));

        if (chats.get(position).getReaded()){
            readed.setBackgroundResource(R.drawable.round_right_background);
        }

        LinearLayout ll = holder.itemView.findViewById(R.id.chat);

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatsRecycleViewInterface.onItemClick(chats.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.chats.size();
    }
}
