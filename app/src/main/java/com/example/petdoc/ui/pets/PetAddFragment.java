package com.example.petdoc.ui.pets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.petdoc.MainActivity;
import com.example.petdoc.data.entities.Pet;
import com.example.petdoc.databinding.FragmentPetAddBinding;
import com.example.petdoc.ui.view_models.PetsViewModel;


public class PetAddFragment extends Fragment {
    private PetsViewModel petsViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private FragmentPetAddBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPetAddBinding.inflate(inflater, container, false);

        petsViewModel = new PetsViewModel(getActivity());
        //String[] PetTypes = {"Dog", "Cat", "Turtle"};
        String[] PetTypes = {"Собака", "Кошка/кот", "Черепаха"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, PetTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        binding.petType.setAdapter(adapter);

        binding.createPetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = binding.editTextPetName;
                EditText age = binding.editTextPetAge;
                EditText history = binding.editTextPetHistory;
                int n = 0;
                if (!String.valueOf(age.getText()).equals("")){
                    n = Integer.parseInt(String.valueOf(age.getText()));
                }
                if (n > 30) {
                    Toast.makeText(getActivity(), "Неправельный возраст", Toast.LENGTH_LONG).show();
                    return;
                }

                if (String.valueOf(name.getText()).equals("")){
                    Toast.makeText(getActivity(), "Введите имя питомца", Toast.LENGTH_LONG).show();
                } else {
                    switch (binding.petType.getSelectedItem().toString()){
                        case "Кошка/кот": addPet(String.valueOf(name.getText()), n, "Cat", history.toString()); break;
                        case "Собака": addPet(String.valueOf(name.getText()), n, "Dog", history.toString()); break;
                        case "Черепаха": addPet(String.valueOf(name.getText()), n, "Turtle", history.toString()); break;
                    }

                    MainActivity mainActivity = (MainActivity)getActivity();
                    mainActivity.changeFragment(new PetsFragment());
                }
            }
        });

        binding.backToPetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new PetsFragment());
            }
        });
        return binding.getRoot();
    }


    private void addPet(String name, int age, String type, String history) {
        petsViewModel.insertPet(new Pet(name, age, type, history));
    }
}