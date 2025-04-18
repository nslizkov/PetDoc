package com.example.petdoc.ui.schedule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petdoc.data.entities.Appointment;
import com.example.petdoc.databinding.FragmentScheduleBinding;
import com.example.petdoc.ui.adapters.AppointmentsAdapter;
import com.example.petdoc.ui.view_models.AppointmentsViewModel;

import java.util.ArrayList;
import java.util.List;

public class ScheduleFragment extends Fragment {

    private List<Appointment> appointments = new ArrayList<>();
    private AppointmentsViewModel appointmentsViewModel;

    public RecyclerView.Adapter adapter = new AppointmentsAdapter(this.appointments, 1, null);

    private FragmentScheduleBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentScheduleBinding.inflate(inflater, container, false);

        RecyclerView recycler = binding.scheduleRecyclerView;
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recycler.setAdapter(adapter);
        appointmentsViewModel = new AppointmentsViewModel(getActivity());
        refresh();

        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void refresh(){
        List<Appointment> appointmentsList = appointmentsViewModel.getPaidAppointments();
        appointments.clear();
        appointments.addAll(appointmentsList);
        adapter.notifyDataSetChanged();
    }
}