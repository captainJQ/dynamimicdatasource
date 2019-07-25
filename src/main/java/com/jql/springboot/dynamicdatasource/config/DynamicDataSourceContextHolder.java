package com.jql.springboot.dynamicdatasource.config;

public class DynamicDataSourceContextHolder {
    private static ThreadLocal<AreaAndKey> currentArea = new ThreadLocal<>();

    public static AreaAndKey get(){
        return currentArea.get();
    }

    public static void set(AreaAndKey areaAndKey){
        currentArea.set(areaAndKey);
    }

    public static void clear(){
        currentArea.remove();
    }
}
