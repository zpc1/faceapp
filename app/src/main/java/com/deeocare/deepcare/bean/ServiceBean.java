package com.deeocare.deepcare.bean;

import java.util.List;

public class ServiceBean extends BaseBean {
    private List<ProductBean> products;

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }
}
