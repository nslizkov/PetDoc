package com.example.petdoc.ui.pets;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.MainActivity;
import com.example.petdoc.data.entities.Pet;
import com.example.petdoc.databinding.FragmentPetsBinding;
import com.example.petdoc.ui.adapters.PetsAdapter;
import com.example.petdoc.ui.adapters.PetsRecycleViewInterface;
import com.example.petdoc.ui.view_models.PetsViewModel;

import java.util.ArrayList;
import java.util.List;
public class PetsFragment extends Fragment implements PetsRecycleViewInterface {
    private FragmentPetsBinding binding;
    private List<Pet> pets = new ArrayList<>();
    private PetsViewModel petsViewModel;
    public RecyclerView.Adapter adapter = new PetsAdapter(this.pets, this);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPetsBinding.inflate(inflater, container, false);
        binding.PetsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.PetsRecyclerView.setAdapter(adapter);

        petsViewModel = new PetsViewModel(getActivity());
        refresh();

        binding.appendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.changeFragment(new PetAddFragment());
            }
        });

        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        List<Pet> petsList = petsViewModel.getPets();
        pets.clear();
        pets.addAll(petsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Pet pet) {
        Activity act = this.getActivity();
        petsViewModel.deletePet(act, pet);
        petsViewModel.deletePet(this.getActivity(), pet);
        refresh();
    }
}