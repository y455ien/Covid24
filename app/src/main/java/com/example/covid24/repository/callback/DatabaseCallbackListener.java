package com.example.covid24.repository.callback;

public interface DatabaseCallbackListener {

    // Listens for Completion of data caching operation.
    interface OnDataCacheListener {
        void OnCache();
    }
}
