package com.example.softsignproj.adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.softsignproj.R;
import com.example.softsignproj.scheduleEvent.ScheduleEvent;
import com.example.softsignproj.data.model.Venue;
import com.example.softsignproj.viewHolder.VenueViewHolder;

import java.util.ArrayList;

public class VenueAdapter extends RecyclerView.Adapter<VenueViewHolder> {
    private final ArrayList<Venue> venues;
    public static final String POSITION = "com.example.softsignproj.POSITION";

    public VenueAdapter(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    @NonNull
    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.venue_item, parent, false);
        return new VenueViewHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull VenueViewHolder holder, int position) {
        Venue venue = venues.get(position);
        holder.getVenueName().setText(venue.getVenue_name());
        ArrayList<String> sportsList = venue.getSports();
        String sports = "";
        for (String a: sportsList){
            sports = sports + a + '\n';
        }
        holder.getSportsList().setText(sports);
        holder.getSchedule().setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ScheduleEvent.class);
                int position = holder.getBindingAdapterPosition();
                intent.putExtra(POSITION, venues.get(position).getVenue_name());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

}
