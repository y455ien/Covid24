package com.example.covid24.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.covid24.repository.local.LocalDataManager;
import com.example.covid24.repository.remote.RemoteDataManager;
import com.example.covid24.model.businessmodel.DataInteractor;

public abstract class Repository {
    /**
     * This abstract class is a parent class to all other more specific repositories.
     */

    protected DataInteractor interactor;
    protected RemoteDataManager remoteDataManager;
    protected LocalDataManager localDataManager;
    private Context context;

    public Repository(Context context) {
        this.interactor = new DataInteractor(context);
        this.remoteDataManager = new RemoteDataManager();
        this.localDataManager = new LocalDataManager(context);
        this.context = context;
    }


    // Helper method to check current internet connectivity
    protected boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
