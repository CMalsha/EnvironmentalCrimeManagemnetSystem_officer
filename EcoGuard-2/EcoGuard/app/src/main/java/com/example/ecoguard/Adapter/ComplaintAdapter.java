package com.example.ecoguard.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoguard.Model.Complaint;
import com.example.ecoguard.R;
import java.util.List;


public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    private List<Complaint> complaintList;
    public ComplaintAdapter(List<Complaint> complaintList) {
        this.complaintList = complaintList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complaint, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Complaint complaint = complaintList.get(position);
        holder.textViewComplaint.setText(complaint.getFormattedComplaint());
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewComplaint;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewComplaint = itemView.findViewById(R.id.textViewComplaint);
        }
    }
}

