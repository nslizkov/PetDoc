package com.example.petdoc.ui.appointments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.MainActivity;
import com.example.petdoc.data.entities.Appointment;
import com.example.petdoc.databinding.FragmentAppointmentsBinding;
import com.example.petdoc.ui.adapters.AppointmentsAdapter;
import com.example.petdoc.ui.adapters.AppointmentsRecycleViewInterface;
import com.example.petdoc.ui.view_models.AppointmentsViewModel;
import com.example.petdoc.ui.view_models.PetsViewModel;

import java.util.ArrayList;
import java.util.List;
public class AppointmentsFragment extends Fragment implements AppointmentsRecycleViewInterface {
    private FragmentAppointmentsBinding binding;
    private List<Appointment> appointments = new ArrayList<>();
    private AppointmentsViewModel appointmentsViewModel;
    private PetsViewModel petsViewModel;
    public RecyclerView.Adapter adapter = new AppointmentsAdapter(this.appointments, 0, this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAppointmentsBinding.inflate(inflater, container, false);

        binding.appointmentsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.appointmentsRecyclerView.setAdapter(adapter);
        appointmentsViewModel = new AppointmentsViewModel(getActivity());
        petsViewModel = new PetsViewModel(getActivity());
        refresh();

        binding.appendAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (petsViewModel.getPets().size() == 0) {  
                        Toast.makeText(getActivity(), "Сначала вам нужно добавить питомца", Toast.LENGTH_LONG).show();
                    } else {
                        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                        if (connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting()) {
                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.changeFragment(new AppointmentAddFragment());
                        } else {
                            Toast.makeText(getActivity(), "Проверьте свое подключение к Интернету", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Проверьте подключение к интернету", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.payAppointmentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appointments.size() != 0){
                    Toast.makeText(getActivity(), "Оплачено. Вы можете ознакомиться с записями на прием к вашим врачам на следующей вкладке", Toast.LENGTH_LONG).show();
                    appointmentsViewModel.payAll();
                    refresh();
                    MainActivity mainActivity = (MainActivity)getActivity();
                    mainActivity.changeFragment(new AppointmentsFragment());
                } else {
                    Toast.makeText(getActivity(), "У вас нет предварительных записей чтобы оплачивать", Toast.LENGTH_LONG).show();
                }
            }
        });

        TextView cost = (TextView) binding.totalCost;
        int appointmentssCost = 0;
        for (Appointment i : appointmentsViewModel.getAppointments()){appointmentssCost += i.getPrice();}
        cost.setText("Всего: " + appointmentssCost + " руб");
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        List<Appointment> appointmentsList = appointmentsViewModel.getAppointments();
        appointments.clear();
        appointments.addAll(appointmentsList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(Appointment appointment) {
        appointmentsViewModel.deleteAppointment(appointment);
        refresh();

    }
}