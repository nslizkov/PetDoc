package com.example.petdoc.ui.entry;

import static com.example.petdoc.MainActivity.USERID;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.petdoc.MainActivity;
import com.example.petdoc.R;
import com.example.petdoc.databinding.FragmentEntryBinding;
import com.example.petdoc.ui.pets.PetsFragment;
import com.example.petdoc.ui.registration.RegistrationFragment;
import com.example.petdoc.ui.staff.StaffFragment;

import java.security.MessageDigest;
public class EntryFragment extends Fragment {

    private FragmentEntryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentEntryBinding.inflate(inflater, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String registred = sharedPreferences.getString("registred", "0");
        if (registred.equals("1")){
            if ((int)sharedPreferences.getInt("id", 1) == 0){ //staff
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new StaffFragment());
            } else {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.appendWinbar();
                mainActivity.changeFragment(new PetsFragment());
            }
        }


        binding.registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new RegistrationFragment());
            }
        });

        EditText login = binding.editTextLogin;
        EditText password = binding.editTextPassword;


        binding.goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                //Log.i("myTagLogin", )
                if (login.getText().toString().equals("root") && password.getText().toString().equals("root")){ //staff
                    setUSERID(0);
                    mainActivity.changeFragment(new StaffFragment());
                } else {
                    setUSERID();
                    mainActivity.appendWinbar();
                    mainActivity.sendInTouch();
                    mainActivity.changeFragment(new PetsFragment());
                }
            }
        });
        return binding.getRoot();
    }

    public void setUSERID(){
        MainActivity mainActivity = (MainActivity) getActivity();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
        mainActivity.getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new EntryFragment()).commit();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String lgpsw = String.valueOf(binding.editTextLogin.getText()) + String.valueOf(binding.editTextPassword.getText());
        USERID = hashStringToNumber(lgpsw);//new Random().nextInt(1000000) + 2;
        editor.putInt("id", USERID);
        editor.commit();
    }

    public static int hashStringToNumber(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());

            int number = 0;
            for (int i = 0; i < Math.min(hash.length, 8); i++) {
                number += (int) (hash[i] & 0xFF) << (8 * i);
            }
            return number % 147000000;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setUSERID(int userid){
        MainActivity mainActivity = (MainActivity) getActivity();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mainActivity);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("id", userid);
        editor.commit();
    }

}