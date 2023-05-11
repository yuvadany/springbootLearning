package com.example.springbootlearning.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionModel {
    public SubscriptionModel() {
    }
    public SubscriptionModel(String subscriptionId, String brand, String msisdn, String customerName) {
        this.subscriptionId = subscriptionId;
        this.brand = brand;
        this.msisdn = msisdn;
        this.customerName = customerName;
    }
    @ApiModelProperty(notes = "Id under msisdn")
    private String subscriptionId;
    @ApiModelProperty(notes = "mobile network Brand (o2 / blau / aldiTalk)")
    private String brand;
    @ApiModelProperty(notes = "mobile number")
    private String msisdn;
    @ApiModelProperty(notes = "Customer Name")
    private String customerName;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }



}
