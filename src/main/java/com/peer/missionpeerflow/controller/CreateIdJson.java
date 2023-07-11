package com.peer.missionpeerflow.controller;

import java.util.HashMap;
import java.util.Map;

public class CreateIdJson {
    public static Map<String, String> createIdJson(String value) {
        Map<String, String> idJson = new HashMap<String, String>();
        idJson.put("id", value);
        return idJson;
    }
}
