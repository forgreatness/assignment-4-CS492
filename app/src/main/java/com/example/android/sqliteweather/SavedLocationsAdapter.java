package com.example.android.sqliteweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.sqliteweather.data.SavedLocation;

import java.util.List;

public class SavedLocationsAdapter extends RecyclerView.Adapter<SavedLocationsAdapter.SavedLocationsViewHolder> {

    private List<SavedLocation> mSavedLocations;
    private OnSavedLocationClickListener mSavedLocationClickListener;

    public interface OnSavedLocationClickListener {
        void onSavedLocationClick(SavedLocation savedLocation);
    }

    public SavedLocationsAdapter(OnSavedLocationClickListener clickListener) {
        mSavedLocationClickListener = clickListener;
    }

    public void updateSavedLocations(List<SavedLocation> savedLocations) {
        mSavedLocations = savedLocations;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mSavedLocations != null) {
            return mSavedLocations.size();
        } else {
            return 0;
        }
    }

    @Override
    public SavedLocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.saved_location_list_item, parent, false);
        return new SavedLocationsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SavedLocationsViewHolder holder, int position) {
        holder.bind(mSavedLocations.get(position));
    }

    class SavedLocationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mLocationTV;

        public SavedLocationsViewHolder(View itemView) {
            super(itemView);
            mLocationTV = itemView.findViewById(R.id.saved_location_tv);
            itemView.setOnClickListener(this);
        }

        public void bind(SavedLocation savedLocation) {
            mLocationTV.setText(savedLocation.name);
        }

        @Override
        public void onClick(View v) {
            SavedLocation savedLocation = mSavedLocations.get(getAdapterPosition());
            mSavedLocationClickListener.onSavedLocationClick(savedLocation);
        }
    }

}
