package com.deeocare.deepcare.bean;

import java.util.List;

public class ClassfyBean extends BaseBean {
    private List<ServiceBean> serviceBeans ;

    public List<ServiceBean> getServiceBeans() {
        return serviceBeans;
    }

    public void setServiceBeans(List<ServiceBean> serviceBeans) {
        this.serviceBeans = serviceBeans;
    }
}
