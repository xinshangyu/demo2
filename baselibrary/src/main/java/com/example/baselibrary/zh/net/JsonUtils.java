package com.example.baselibrary.zh.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 利用Gson反射对象类型，将json转化为对象
 */
public class JsonUtils {
    private Gson mGson = null;
    private static JsonUtils mUtils;

    public static JsonUtils getInstance() {
        if (mUtils == null) {
            return new JsonUtils();
        } else {
            return mUtils;
        }
    }

    public Gson getGson() {
        if (null == mGson) {
            mGson = new GsonBuilder().serializeNulls().create();
        }
        return mGson;
    }

    /**
     * 将json数组转化为List
     *
     * @param json     json字符串
     * @param classOfT 对应实体类型
     * @return 实体列表
     */
    public <T> ArrayList<T> jsonToList(String json, Class<T> classOfT) {
        try {
            if (mGson == null) {
                // 不要直接new Gson是为了避免放回结果为null的这种情况
                mGson = new GsonBuilder().serializeNulls().create();
            }
            Type type = new TypeToken<ArrayList<JsonObject>>() {
            }.getType();
            ArrayList<JsonObject> jsonObjs = mGson.fromJson(json, type);

            ArrayList<T> listOfT = new ArrayList<T>();
            for (JsonObject jsonObj : jsonObjs) {
                listOfT.add(mGson.fromJson(jsonObj, classOfT));
            }
            return listOfT;
        } catch (Exception e) {
            return null;
        }
    }

    public <T> T fromJson(String json, Class<T> type) {
        return getGson().fromJson(json, type);
    }

    public <T> T fromJson(String json, Type type) {
        return getGson().fromJson(json, type);
    }

    public <T> T fromJson(JsonReader reader, Type typeOfT) throws JsonIOException, JsonSyntaxException {
        return getGson().fromJson(reader, typeOfT);
    }

}
