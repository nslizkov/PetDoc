package com.example.petdoc.ui.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.R;
import com.example.petdoc.data.entities.Pet;

import java.util.List;

public class PetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final PetsRecycleViewInterface petsRecycleViewInterface;
    private List<Pet> pets;

    public Pet pet;

    public PetsAdapter(List<Pet> pets, PetsRecycleViewInterface petsRecycleViewInterface) {
        this.petsRecycleViewInterface = petsRecycleViewInterface;
        this.pets = pets;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.pet, parent, false)
        ){};
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TextView nText = holder.itemView.findViewById(R.id.nameText);
        TextView nAge = holder.itemView.findViewById(R.id.ageText);
        ImageView nPic = holder.itemView.findViewById(R.id.petTypePic);
        if (pets.get(position).getType().equals("Cat")){
            nPic.setImageResource(R.drawable.cat);
        } else if (pets.get(position).getType().equals("Turtle")){
            nPic.setImageResource(R.drawable.turtle);
        }
        nText.setText(pets.get(position).getName());
        nAge.setText("Лет: "+pets.get(position).getAge());

        ImageButton deletePet = (ImageButton) holder.itemView.findViewById(R.id.deletePet_button);
        deletePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Удалить этого питомца?")
                        .setMessage("Вы уверены, что хотите удалить питомца " + pets.get(position).getName() + "?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                petsRecycleViewInterface.onItemClick(pets.get(position));
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_delete)
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.pets.size();
    }
}