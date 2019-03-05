package com.johan_apps.exelsa.model;

import java.util.ArrayList;
import java.util.Map;

public class TeachingMaterial {
    private String title;
    private ArrayList<Map<String, Object>> documents;

    public TeachingMaterial() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDocuments(ArrayList<Map<String, Object>> documents) {
        this.documents = documents;
    }

    public ArrayList<Map<String, Object>> getDocuments() {
        return documents;
    }
}
