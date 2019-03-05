package com.johan_apps.exelsa.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.johan_apps.exelsa.R;
import com.johan_apps.exelsa.activities.CourseDetailBAActivity;
import com.johan_apps.exelsa.activities.CourseDetailTugasActivity;

public class CourseMenuFragment extends BottomSheetDialogFragment {
    private Button tugasButton;
    private Button bahanAjarButton;
    private Bundle courseData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_course, container, false);

        initComponents(view);

        return view;
    }

    private void initComponents(View view) {
        courseData = getArguments();

        tugasButton = view.findViewById(R.id.tugas_button);
        tugasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CourseDetailTugasActivity.class);
                intent.putExtra("courseName", courseData.getString("courseName"));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        bahanAjarButton = view.findViewById(R.id.bahan_ajar_button);
        bahanAjarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CourseDetailBAActivity.class);
                intent.putExtra("courseName", courseData.getString("courseName"));
                intent.putExtra("courseId", courseData.getString("courseId"));
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
