package com.deeocare.deepcare.bean;

public class BaseBean{
    private String photo_url;
    private String name;
    private float price;
    private int service;
    private String classname;
    private int type;
    private int itemId;
    private int classId;
    private boolean isShow;
    private int shop_num = 0;//购物车已添加数量
    private int min_num;//最小购买数量
    private int max_num;//最大购买数量
    //产品分类
    public static final int TYPE_CHILD = 001;
    public static final int TYPE_SERVICE = 002;
    public static final int TYPE_CLASS = 003;

    //产品级别
//    public static final int SERVICE_LOW = 101;
//    public static final int SERVICE_MID = 102;
//    public static final int SERVICE_HIGH = 103;

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public int getShop_num() {
        return shop_num;
    }

    public void setShop_num(int shop_num) {
        this.shop_num = shop_num;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }
}
