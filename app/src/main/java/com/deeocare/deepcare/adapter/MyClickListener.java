package com.deeocare.deepcare.adapter;


import com.deeocare.deepcare.bean.ServiceBean;

public interface MyClickListener {
    void OnChartAddClicked(ServiceBean bean, int posion);

    void OnChartDescClicked(ServiceBean bean, int posion);

    void OnChartDelClicked(int posion);
}
