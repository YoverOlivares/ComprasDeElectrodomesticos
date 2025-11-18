package unc.edu.pe.comprasdeelectrodomesticos.ui.summary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import unc.edu.pe.comprasdeelectrodomesticos.domain.model.PurchaseSummary;

public class SummaryViewModel extends ViewModel {

    private final MutableLiveData<PurchaseSummary> summaryData = new MutableLiveData<>();

    public void setSummary(PurchaseSummary summary) {
        summaryData.setValue(summary);
    }

    public LiveData<PurchaseSummary> getSummary() {
        return summaryData;
    }
}