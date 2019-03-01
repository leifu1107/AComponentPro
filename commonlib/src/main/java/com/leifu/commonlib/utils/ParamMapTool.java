package com.leifu.commonlib.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liu le on 2018/9/4.
 * ----2018/9/4-------liu le----xxxxxxxxx--
 */
public class ParamMapTool {
    private Map<String,Object> mParamMap;


    public ParamMapTool() {
        mParamMap = new HashMap<>();
    }

    public ParamMapTool put(String key, Object Value){
        mParamMap.put(key,Value);
        return this;
    }

    public Map<String,Object> build(){
        return mParamMap;
    }
}
