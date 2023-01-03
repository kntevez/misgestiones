package com.vereeth.misgestiones.ui.metricas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MetricasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MetricasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is métricas fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}