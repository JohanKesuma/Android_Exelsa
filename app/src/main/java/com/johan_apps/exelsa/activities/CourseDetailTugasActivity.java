package com.johan_apps.exelsa.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.johan_apps.exelsa.R;

public class CourseDetailTugasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail_tugas);
        setTitle(getIntent().getStringExtra("courseName"));
    }
}
