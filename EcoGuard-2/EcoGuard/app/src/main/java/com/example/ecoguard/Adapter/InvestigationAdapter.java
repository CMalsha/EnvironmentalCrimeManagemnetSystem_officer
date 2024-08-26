package com.example.ecoguard.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecoguard.Model.Investigation;
import com.example.ecoguard.R;
import java.util.List;

public class InvestigationAdapter extends RecyclerView.Adapter<InvestigationAdapter.ViewHolder> {

    private List<Investigation> investigationList;

    public InvestigationAdapter(List<Investigation> investigationList) {
        this.investigationList = investigationList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_investigation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Investigation investigation = investigationList.get(position);
        holder.textViewInvestigationDetails.setText(investigation.getFormattedInvestigation());
    }

    @Override
    public int getItemCount() {
        return investigationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewInvestigationDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewInvestigationDetails = itemView.findViewById(R.id.textViewInvestigationDetails);
        }
    }
}
