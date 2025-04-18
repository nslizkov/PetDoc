package com.example.petdoc.ui.registration;

import static com.example.petdoc.MainActivity.USERID;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.databinding.FragmentRegistrationBinding;
import com.example.petdoc.ui.entry.EntryFragment;
import com.example.petdoc.ui.pets.PetsFragment;

import java.util.Random;
public class RegistrationFragment extends Fragment {
    private FragmentRegistrationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        binding.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity) getActivity();
                setUSERID();
                mainActivity.appendWinbar();
                mainActivity.sendInTouch();
                mainActivity.changeFragment(new PetsFragment());
            }
        });

        return binding.getRoot();
    }
    public void setUSERID(){
        MainActivity mainActivity = (MainActivity) getActivity();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
        mainActivity.getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new EntryFragment()).commit();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        USERID = new Random().nextInt(1000000) + 2;
        editor.putInt("id", USERID);
        editor.commit();
    }

}