package com.example.petdoc.ui.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.ui.appointments.AppointmentsFragment;
import com.example.petdoc.ui.chat.ChatFragment;
import com.example.petdoc.ui.pets.PetsFragment;
import com.example.petdoc.ui.schedule.ScheduleFragment;


public class WinbarFragment extends Fragment {

    ImageButton petsButton, scheduleButton, appointmentsButton, chatButton;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_winbar, container, false);

        scheduleButton = (ImageButton) v.findViewById(R.id.schedule_button);
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColors();
                scheduleButton.setBackgroundResource(R.drawable.round_right_soft_background);
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new ScheduleFragment());
            }
        });

        appointmentsButton = (ImageButton) v.findViewById(R.id.appointments_button);
        appointmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColors();
                appointmentsButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.soft_green));
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new AppointmentsFragment());
            }
        });

        petsButton = (ImageButton) v.findViewById(R.id.pets_button);
        petsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColors();
                petsButton.setBackgroundResource(R.drawable.round_left_soft_background);
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new PetsFragment());
            }
        });

        chatButton = (ImageButton) v.findViewById(R.id.chat_button);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetColors();
                chatButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.soft_green));
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new ChatFragment());
            }
        });
        return v;
    }
    void resetColors(){
        appointmentsButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));
        chatButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green));

        scheduleButton.setBackgroundResource(R.drawable.round_right_background);
        petsButton.setBackgroundResource(R.drawable.round_left_background);
    }

}