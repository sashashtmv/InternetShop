package com.entersnowman.internetshop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityRequestBody {

    @SerializedName("apiKey")
    @Expose
    private String apiKey;
    @SerializedName("modelName")
    @Expose
    private String modelName;
    @SerializedName("calledMethod")
    @Expose
    private String calledMethod;
    @SerializedName("methodProperties")
    @Expose
    private AbstractMethod methodProperties;

    public String getApiKey() {
        return apiKey;
    }
    public  CityRequestBody(){

    }
    public CityRequestBody(String apiKey, String modelName, String calledMethod, AbstractMethod methodProperties) {
        this.apiKey = apiKey;
        this.modelName = modelName;
        this.calledMethod = calledMethod;
        this.methodProperties = methodProperties;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public void setCalledMethod(String calledMethod) {
        this.calledMethod = calledMethod;
    }

    public AbstractMethod getMethodProperties() {
        return methodProperties;
    }

    public void setMethodProperties(AbstractMethod methodProperties) {
        this.methodProperties = methodProperties;
    }

}