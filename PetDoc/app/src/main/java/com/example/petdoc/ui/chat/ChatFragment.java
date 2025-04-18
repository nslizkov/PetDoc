package com.example.petdoc.ui.chat;

import static com.example.petdoc.MainActivity.USERID;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.MainActivity;
import com.example.petdoc.data.entities.Message;
import com.example.petdoc.databinding.FragmentChatBinding;
import com.example.petdoc.ui.adapters.MessagesAdapter;
import com.example.petdoc.ui.view_models.ChatViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class ChatFragment extends Fragment{
    private FragmentChatBinding binding;
    private List<Message> messages = new ArrayList<>();

    private ChatViewModel chatViewModel;
    public RecyclerView.Adapter adapter = new MessagesAdapter(this.messages);


    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            refresh();
            handler.postDelayed(this, 5000);
        }
    };
    public ChatFragment(){}
    int targetUserID = 0;
    public ChatFragment(int userID){
        targetUserID = userID;
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);

        EditText text = (EditText) binding.messageSendText;

        chatViewModel = new ChatViewModel(getActivity());
        refresh();

        RecyclerView recycler = binding.ChatRecyclerView;
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);

        if (USERID != 0){
            showKeyboard(text);
            hideKeyboard(text);
            ((MainActivity)getActivity()).hideWinbar();
            ((MainActivity)getActivity()).showWinbar();
        }


        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (!connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting())
                Toast.makeText(getActivity(), "Проверьте свое подключение к Интернету", Toast.LENGTH_LONG).show();
        } catch (Exception e){}
        recycler.scrollToPosition(messages.size() - 1);
        refresh();

        ScrollView sv = (ScrollView) binding.chatScroller;
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(View.FOCUS_DOWN);
            }
        });
        binding.messageSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = Calendar.getInstance().getTime();
                if (text.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Невозможно отправить пустое сообщение", Toast.LENGTH_LONG).show();
                } else {
                    try {
                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()) {
                            String dateTime = date.toString();
                            dateTime = dateTime.substring(4, dateTime.length() - 12);
                            if (targetUserID == 0) {
                                chatViewModel.insertMessage(new Message(USERID, targetUserID, text.getText().toString(), dateTime));
                                hideKeyboard(text);
                                MainActivity mainActivity = (MainActivity) getActivity();
                                mainActivity.changeFragment(new ChatFragment());
                            } else {
                                chatViewModel.insertMessage(new Message(USERID, targetUserID, text.getText().toString(), dateTime));
                                hideKeyboard(text);
                                MainActivity mainActivity = (MainActivity) getActivity();
                                mainActivity.changeFragment(new ChatFragment(targetUserID));
                            }

                        } else {
                            Toast.makeText(getActivity(), "Проверьте свое подключение к Интернету", Toast.LENGTH_LONG).show();
                        }
                    }   catch (Exception e) {}
            }}
        });

        binding.videocallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                if (targetUserID == 0) mainActivity.showWinbar();
                if (targetUserID == 0) mainActivity.changeFragment(new VideoCallFragment());
                else mainActivity.changeFragment(new VideoCallFragment(targetUserID));
            }
        });

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKeyboard(text);
            }

        });
        text.requestFocus();

        handler.postDelayed(runnable, 5000);
        return binding.getRoot();
    }


    public void showKeyboard(EditText text){
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(text, InputMethodManager.SHOW_IMPLICIT);
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.hideWinbar();
    }

    public void hideKeyboard(EditText text){
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(text.getWindowToken(), 0);
        MainActivity mainActivity = (MainActivity)getActivity();
        if (targetUserID == 0) mainActivity.showWinbar();
    }
    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        List<Message> messagesList;
        if (targetUserID == 0)  messagesList = chatViewModel.getMessages();
        else messagesList = chatViewModel.getMessages(targetUserID);
        messages.clear();
        messages.addAll(messagesList);
        adapter.notifyDataSetChanged();
    }
}