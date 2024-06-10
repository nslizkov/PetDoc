package com.example.petdoc.ui.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.R;
import com.example.petdoc.data.entities.Appointment;

import java.util.List;

public class AppointmentsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final AppointmentsRecycleViewInterface appointmentsRecycleViewInterface;
    private int PAID = 0;
    private List<Appointment> appointments;
    public AppointmentsAdapter(List<Appointment> appointments, int paid, AppointmentsRecycleViewInterface appointmentsRecycleViewInterface) {
        PAID = paid;
        this.appointmentsRecycleViewInterface = appointmentsRecycleViewInterface;
        this.appointments = appointments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.appointment, parent, false)
        ){};
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TextView petName = holder.itemView.findViewById(R.id.petNameText);
        TextView doctor = holder.itemView.findViewById(R.id.doctorText);
        TextView date = holder.itemView.findViewById(R.id.dateText);
        TextView price = holder.itemView.findViewById(R.id.priceText);
        petName.setText(appointments.get(position).getPet());
        doctor.setText(appointments.get(position).getDoctor() + " (" + appointments.get(position).getDoctorType() + ")");
        date.setText(appointments.get(position).getDate());


        ImageButton deleteAppointment = (ImageButton) holder.itemView.findViewById(R.id.deleteAppointment_button);
        LinearLayout.LayoutParams buttonParams = (LinearLayout.LayoutParams) deleteAppointment.getLayoutParams();
        if (PAID == 0){
            buttonParams.weight = 1.0f;
            deleteAppointment.setLayoutParams(buttonParams);
            price.setText(appointments.get(position).getPrice()+" руб");
            deleteAppointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Отменить эту запись?")
                            .setMessage("Вы уверены, что хотите отменить запись питомца " + appointments.get(position).getPet() + " к " + appointments.get(position).getDoctor() + " (" + appointments.get(position).getDoctorType() + ")?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    appointmentsRecycleViewInterface.onItemClick(appointments.get(position));
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_delete)
                            .show();

                }
            });
        } else {
            buttonParams.weight = 0.0f;
            deleteAppointment.setLayoutParams(buttonParams);
            price.setHeight(0);
        }
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }
}