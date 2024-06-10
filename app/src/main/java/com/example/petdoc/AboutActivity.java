package com.example.petdoc;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.petdoc.databinding.ActivityAboutBinding;


public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActivityAboutBinding binding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getExtras().get("type").toString();
        binding.aboutHeader.setText(type);

        String text = "";
        if (type.equals("О программе")){
            text = "Данное мобильное приложение предназначено для взаимодействия между ветклиникой и ее клиентами. \nВы можете добавлять питомцев, записываться на приемы и общаться со службой поддержки используя это приложение.";
        } else if (type.equals("Об авторе")){
            binding.imageView.setVisibility(View.VISIBLE);
            text = "Слизков Н.А.\nИКБО-03-22\n2024 г.\nnikita.slizkov@mail.ru";

            ObjectAnimator scaleXAnimator = ObjectAnimator.
                    ofFloat(binding.imageView, "scaleX", 0.95f, 1.1f);
            scaleXAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleXAnimator.setRepeatMode(ObjectAnimator.REVERSE);

            ObjectAnimator scaleYAnimator = ObjectAnimator.
                    ofFloat(binding.imageView, "scaleY", 0.95f, 1.1f);
            scaleYAnimator.setRepeatCount(ObjectAnimator.INFINITE);
            scaleYAnimator.setRepeatMode(ObjectAnimator.REVERSE);

            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(scaleXAnimator, scaleYAnimator);
            animatorSet.setDuration(2000);
            animatorSet.start();

        } else {
            text = "Раздел «Питомцы» предоставляет пользователю информацию о его питомцах. Здесь можно посмотреть уже добавленных питомцев или добавить новых.\n\n" +
                    "Раздел «Коммуникация» содержит мессенджер. Здесь можно написать в службу поддержки или попросить консультацию. Рядом находится кнопка видеозвонка для срочной видеоконсультации.\n\n" +
                    "Разделе «Записи» Служит для записи питомца к врачу. Там располагается список предзаказанных записей, их цена. Также там располагается кнопка добавления записи и кнопка оплаты.\n\n" +
                    "Раздел «Оплаченные записи» содержит список оплаченных клиентом записей.\n\n" +
                    "Также в верхней части приложение есть меню с пунктами «О программе», «Об авторе» и «Инструкция пользователю».\n";
        }

        binding.aboutText.setText(text);
        binding.aboutText.setMovementMethod(new ScrollingMovementMethod());
    }

}