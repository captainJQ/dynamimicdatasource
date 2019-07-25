package com.jql.springboot.dynamicdatasource.config;

public enum AreaAndKey {
    BEIJING("北京","010"),
    SHENZHEN("深圳","020");


    private String name;

    private String key;

    private AreaAndKey(String name,String key){
        this.setName(name);
        this.setKey(key);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
