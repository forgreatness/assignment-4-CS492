package com.example.android.sqliteweather;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sqliteweather.data.ForecastItem;
import com.example.android.sqliteweather.data.ForecastRepository;
import com.example.android.sqliteweather.data.SavedLocation;
import com.example.android.sqliteweather.data.SavedLocationsRepository;
import com.example.android.sqliteweather.data.Status;

import java.util.List;

/*
 * This is the ViewModel class for forecast data.  It manages two different pieces of data: the
 * forecast data itself and a Status value indicating the current network loading status for
 * forecast data.  The ViewModel class uses a Repository class to actually perform data operations.
 */
public class ForecastViewModel extends AndroidViewModel {

    private LiveData<List<ForecastItem>> mForecastItems;
    private LiveData<Status> mLoadingStatus;

    private ForecastRepository mRepository;
    private SavedLocationsRepository mSavedLocationsRepository;

    public ForecastViewModel(Application application) {
        super(application);
        mSavedLocationsRepository = new SavedLocationsRepository(application);
        mRepository = new ForecastRepository();
        mForecastItems = mRepository.getForecast();
        mLoadingStatus = mRepository.getLoadingStatus();
    }

    public void loadForecast(String location, String units) {
        mRepository.loadForecast(location, units);
    }

    public LiveData<List<ForecastItem>> getForecast() {
        return mForecastItems;
    }

    public LiveData<Status> getLoadingStatus() {
        return mLoadingStatus;
    }


    public void insertSavedLocation(SavedLocation location) {
        mSavedLocationsRepository.insertSavedLocation(location);
    }

    public void deleteSavedLocation(SavedLocation location) {
        mSavedLocationsRepository.deleteSavedLocation(location);
    }

    public LiveData<List<SavedLocation>> getAllSavedLocations() {
        return mSavedLocationsRepository.getAllSavedLocations();
    }

    public SavedLocation getSavedLocationByName(String location) {
        return mSavedLocationsRepository.getSavedLocationByName(location);
    }
}
