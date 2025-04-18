package com.example.petdoc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.petdoc.data.entities.Message;
import com.example.petdoc.databinding.ActivityMainBinding;
import com.example.petdoc.ui.entry.EntryFragment;
import com.example.petdoc.ui.navigation.WinbarFragment;
import com.example.petdoc.ui.pets.PetsFragment;
import com.example.petdoc.ui.staff.StaffFragment;
import com.example.petdoc.ui.view_models.ChatViewModel;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static Fragment winFrag = new WinbarFragment();
    public static int USERID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        Log.i("mytag", "USERID: "+sharedPreferences.getInt("id", 1));
        if (sharedPreferences.getInt("id", 1) == 1){
            getSupportFragmentManager().beginTransaction().add(R.id.main_fragment, new EntryFragment()).commit();
        } else if (sharedPreferences.getInt("id", 1) == 0){
            USERID = 0;
            changeFragment(new StaffFragment());
        } else{
            USERID = sharedPreferences.getInt("id", 1);
            sendInTouch();
            appendWinbar();
            changeFragment(new PetsFragment());
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

    }

    public void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().addToBackStack(null);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment).commit();
    }

    public void appendWinbar(){
        getSupportFragmentManager().beginTransaction()
                .add(R.id.winbar_fragment, winFrag)
                .commit();
    }

    public void hideWinbar(){
        winFrag.getView().setVisibility(View.GONE);
    }

    public void showWinbar(){
        winFrag.getView().setVisibility(View.VISIBLE);
    }

    @Override 
    public void onBackPressed() {
        super.onBackPressed();
        if (USERID != 0) showWinbar();
        else changeFragment(new StaffFragment());
    }

    public void sendInTouch(){
        ChatViewModel chatViewModel = new ChatViewModel(this);
        List<Message> messages = chatViewModel.getMessages();
        if (messages.size() == 0 ){ //|| !messages.get(messages.size()-1).getText().equals("Наш оператор на связи")
            String dateTime = Calendar.getInstance().getTime().toString();
            dateTime = dateTime.substring(4, dateTime.length()-12);
            chatViewModel.insertMessage(new Message(0, USERID, "Наш оператор на связи", dateTime));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("О программе");
        menu.add("Инструкция пользователю");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AboutActivity.class);
        intent.putExtra("type", ""+item.getTitle());
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}