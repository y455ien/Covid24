package com.example.covid24.repository.local.room;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;


import androidx.room.Room;

import com.amitshekhar.DebugDB;
import com.example.covid24.model.datamodel.country.Country;
import com.example.covid24.model.datamodel.country.Statistics;
import com.example.covid24.repository.callback.DatabaseCallbackListener;
import com.example.covid24.repository.callback.InteractorCallbackListener;

import java.util.List;


public class DatabaseHandler implements IDataWriter, IDataReader {

    private static final String DATABASE_NAME = "RoomDatabase";
    private CountriesDatabase database;
    private final Handler handler;


    public DatabaseHandler(Context context) {
        this.database = Room.databaseBuilder(context, CountriesDatabase.class, DATABASE_NAME).build();
        this.handler = new Handler(Looper.getMainLooper());
        Log.i("REPO", DebugDB.getAddressLog());
    }


    // IDataWrite Implementation
    @Override
    public void cacheWorld(final Country world, final DatabaseCallbackListener.OnDataCacheListener localDataManagerListener) {
        new Thread() {
            @Override
            public void run() {
                DatabaseHandler.this.database.countryDao().cacheWorld(world);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("REPO", "//----> CACHING WORLD INSTANCE :: WORLD = " + world.getCountryName());
                        localDataManagerListener.OnCache();
                    }
                });
            }
        }.start();
    }

    @Override
    public void cacheStatistics(final String countryName, final String dataDate, final Statistics statistics, final DatabaseCallbackListener.OnDataCacheListener localDataManagerListener) {
        new Thread() {
            @Override
            public void run() {
                DatabaseHandler.this.database.countryDao().cacheCountryStatistics(countryName, dataDate, statistics);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("REPO", "//----> CACHING COUNTRY STATISTICS :: COUNTRY = " + countryName);
                        localDataManagerListener.OnCache();
                    }
                });
            }
        }.start();
    }

    @Override
    public void cacheCountryList(final List<Country> countriesList, final DatabaseCallbackListener.OnDataCacheListener localDataManagerListener) {
        new Thread() {
            @Override
            public void run() {
                for (Country currentCountry : countriesList)
                    DatabaseHandler.this.database.countryDao().cacheCountry(currentCountry);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("REPO", "//----> CACHING COUNTRIES :: COUNTRIES NUMBER = " + countriesList.size());
                        localDataManagerListener.OnCache();
                    }
                });
            }
        }.start();
    }

    @Override
    public void updateSavedStatus(final String countryName, final boolean isSavedStatus) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.this.database.countryDao().updateSavedStatus(countryName, isSavedStatus);
                Log.i("REPO", "//----> UPDATING COUNTRY SAVED STATUS :: COUNTRY = " + countryName + " / STATUS = " + isSavedStatus);
            }
        });
        thread.start();
    }
    //---------------------------------------------------------------------
    // IDataReader Implementation
    @Override
    public void getCachedCountry(final String countryName, final InteractorCallbackListener.OnCachedCountryReceiveListener localDataManagerListener) {
        new Thread() {
            @Override
            public void run() {
                final Country cachedCountry = DatabaseHandler.this.database.countryDao().getCountryStatistics(countryName);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("REPO", "//----> GETTING CACHED COUNTRY STATISTICS :: COUNTRY = " + countryName);
                        localDataManagerListener.OnCachedCountryStatisticsReceive(cachedCountry);
                    }
                });
            }
        }.start();
    }

    @Override
    public void getCachedCountryList(final InteractorCallbackListener.OnCachedCountryListReceiveListener localDataManagerListener) {
        new Thread() {
            @Override
            public void run() {
                final List<Country> cachedCountryList = DatabaseHandler.this.database.countryDao().getAllCountries("All");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("REPO", "//----> GETTING CACHED COUNTRY LIST :: COUNTRIES NUMBER = " + cachedCountryList.size());
                        localDataManagerListener.OnCachedCountryListReceive(cachedCountryList);
                    }
                });
            }
        }.start();
    }

    @Override
    public void getSavedCountryList(final InteractorCallbackListener.OnCachedCountryListReceiveListener localDataManagerListener) {
        new Thread() {
            @Override
            public void run() {
                final List<Country> cachedCountryList = DatabaseHandler.this.database.countryDao().getSavedCountryList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("REPO", "//----> GETTING SAVED COUNTRY LIST :: COUNTRIES NUMBER = " + cachedCountryList.size());
                        localDataManagerListener.OnCachedCountryListReceive(cachedCountryList);
                    }
                });
            }
        }.start();
    }

}
