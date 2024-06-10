package com.example.petdoc.ui.staff;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.MainActivity;
import com.example.petdoc.data.entities.Chat;
import com.example.petdoc.data.entities.Message;
import com.example.petdoc.databinding.FragmentStaffBinding;
import com.example.petdoc.ui.adapters.ChatsAdapter;
import com.example.petdoc.ui.adapters.ChatsRecycleViewInterface;
import com.example.petdoc.ui.chat.ChatFragment;
import com.example.petdoc.ui.view_models.ChatViewModel;

import java.util.ArrayList;
import java.util.List;
public class StaffFragment extends Fragment implements ChatsRecycleViewInterface {

    private List<Message> messages = new ArrayList<>();
    private List<Chat> chats = new ArrayList<>();
    public RecyclerView.Adapter adapter = new ChatsAdapter(this.chats, this);

    private FragmentStaffBinding binding;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            refresh();
            handler.postDelayed(this, 5000);
        }
    };
    private ChatViewModel chatViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStaffBinding.inflate(inflater, container, false);

        chatViewModel = new ChatViewModel(getActivity());
        refresh();

        makeChats();

        RecyclerView recycler = binding.ChatsRecyclerView;
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);

        refresh();
        handler.postDelayed(runnable, 5000);
        return binding.getRoot();
    }

    public void makeChats(){
        chats.clear();
        boolean flag;
        for (Message m: messages){
            flag = true;
            for (Chat c: chats){
                if (c.getUserid() == m.getToId() || c.getUserid() == m.getUserId()){
                    c.appendMessage(m);
                    flag = false;
                }
            }
            if (flag){
                Chat c = new Chat();
                if (m.getToId() != 0) c.setUserid(m.getToId()); else c.setUserid(m.getUserId());
                c.appendMessage(m);
                chats.add(c);
            }
        }

        for (Chat c: chats){
            if (c.getMessages().get(c.getMessages().size()-1).getUserId() == 0) c.setReaded(true);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        List<Message> messagesList = ChatViewModel.getMessages();
        messages.clear();
        messages.addAll(messagesList);
        makeChats();
        //Collections.reverse(Collections.singletonList(chats)); WTF!??
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Chat chat) {
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.changeFragment(new ChatFragment(chat.getUserid()));
    }
}