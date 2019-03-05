package com.johan_apps.exelsa.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.johan_apps.exelsa.R;
import com.johan_apps.exelsa.adapter.TeachingMaterialViewAdapter;
import com.johan_apps.exelsa.callbacks.FailureCallback;
import com.johan_apps.exelsa.callbacks.SuccessCallback;
import com.johan_apps.exelsa.model.TeachingMaterial;
import com.johan_apps.exelsa.services.TeachingMaterialResources;

import java.util.ArrayList;

public class CourseDetailBAActivity extends AppCompatActivity {
    private TextView baStatusText;
    private ArrayList<TeachingMaterial> teachingMaterials;
    private RecyclerView recyclerView;
    private String courseId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail_ba);
        setTitle(getIntent().getStringExtra("courseName"));

        initComponents();
    }

    private void initComponents() {
        recyclerView = findViewById(R.id.ba_recyclerview);
        courseId = getIntent().getStringExtra("courseId");
        baStatusText = findViewById(R.id.ba_status_text);

        initRecyclerview();
        getTeachingMaterial(courseId);
    }

    private void initRecyclerview() {
        teachingMaterials = new ArrayList<>();
        TeachingMaterialViewAdapter teachingMaterialViewAdapter =
                new TeachingMaterialViewAdapter(teachingMaterials, getApplicationContext());

        recyclerView.setAdapter(teachingMaterialViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void getTeachingMaterial(String documentId) {
        TeachingMaterialResources teachingMaterialResources = new TeachingMaterialResources();
        teachingMaterialResources.getTeachingMaterial(documentId)
                .addOnSuccessCalback(new SuccessCallback<TeachingMaterial>() {
                    @Override
                    public void onSuccess(TeachingMaterial data) {
                        if (data != null) {
                            teachingMaterials.add(data);
                            recyclerView.getAdapter().notifyItemInserted(teachingMaterials.size());
                        } else {
                            baStatusText.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .addOnFailureCallback(new FailureCallback<String>() {
                    @Override
                    public void onFailure(String data) {
                    }
                });
    }
}
