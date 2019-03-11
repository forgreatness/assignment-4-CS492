package com.example.android.sqliteweather.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SavedLocationsRepository {
    private SavedLocationsDao mSavedLocationsDao;

    public SavedLocationsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        mSavedLocationsDao = db.savedLocationsDao();
    }

    public void insertSavedLocation(SavedLocation location) {
        new InsertAsyncTask(mSavedLocationsDao).execute(location);
    }

    public void deleteSavedLocation(SavedLocation location) {
        new DeleteAsyncTask(mSavedLocationsDao).execute(location);
    }

    public LiveData<List<SavedLocation>> getAllSavedLocations(){
        return mSavedLocationsDao.getAllLocations();
    }

    public SavedLocation getSavedLocationByName(String location){
        SavedLocation savedLocation = new SavedLocation();
        try{
            savedLocation = new getSavedLocationByNameAsyncTask(mSavedLocationsDao).execute(location).get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        return savedLocation;
    }

    private static class getSavedLocationByNameAsyncTask extends AsyncTask<String, Void, SavedLocation>{
        private SavedLocationsDao mAsyncTaskDao;
        getSavedLocationByNameAsyncTask(SavedLocationsDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected SavedLocation doInBackground(String... strings) {
            return mAsyncTaskDao.getSavedLocationByName(strings[0]);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<SavedLocation, Void, Void> {
        private SavedLocationsDao mAsyncTaskDao;
        InsertAsyncTask(SavedLocationsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SavedLocation... locations) {
            mAsyncTaskDao.insert(locations[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<SavedLocation, Void, Void> {
        private SavedLocationsDao mAsyncTaskDao;
        DeleteAsyncTask(SavedLocationsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(SavedLocation... locations) {
            mAsyncTaskDao.delete(locations[0]);
            return null;
        }
    }
}
