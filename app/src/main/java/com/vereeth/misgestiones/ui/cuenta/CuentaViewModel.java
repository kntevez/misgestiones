package com.vereeth.misgestiones.ui.cuenta;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CuentaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CuentaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cuenta fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}