package com.johan_apps.exelsa.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.johan_apps.exelsa.R;
import com.johan_apps.exelsa.adapter.CourseViewAdapter;
import com.johan_apps.exelsa.callbacks.FailureCallback;
import com.johan_apps.exelsa.callbacks.SuccessCallback;
import com.johan_apps.exelsa.model.Course;
import com.johan_apps.exelsa.services.UserCourseResource;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView;
    private CourseViewAdapter courseViewAdapter;
    private ArrayList<Course> courses;
    private SwipeRefreshLayout swipeRefreshLayout;

    public CourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        initComponents(view);

        return view;
    }

    private void initComponents(View view) {
        swipeRefreshLayout = view.findViewById(R.id.courseRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = view.findViewById(R.id.courses_recyclerView);
        initRecyclerView();
    }

    private void getUserCourse(String uid) {
        swipeRefreshLayout.setRefreshing(true);
        UserCourseResource userCourseResource = new UserCourseResource();
        userCourseResource.getCourseByUser(uid)
                .addOnSuccessCallback(new SuccessCallback<Course>() {
                    @Override
                    public void onSuccess(Course data) {
                        if (data != null) {
                            swipeRefreshLayout.setRefreshing(false);
                            courses.add(data);
                            recyclerView.getAdapter().notifyItemInserted(courses.size());
                        }

                    }
                })
                .addOnFailureCallback(new FailureCallback<String>() {
                    @Override
                    public void onFailure(String data) {

                    }
                });
    }

    private void initRecyclerView() {
        courses = new ArrayList<>();
        courseViewAdapter = new CourseViewAdapter(courses, getContext());
        courseViewAdapter.setOnCourseClickListener(new CourseViewAdapter.OnCourseClickListener() {
            @Override
            public void onClick(View view, int i) {
                CourseMenuFragment courseMenuFragment = new CourseMenuFragment();
                Bundle courseData = new Bundle();
                courseData.putString("courseName", courses.get(i).getCourseName());
                courseData.putString("courseId", courses.get(i).getId());
                courseMenuFragment.setArguments(courseData);
                courseMenuFragment.show(getFragmentManager(), "Course Menu Fragment");
            }
        });
        recyclerView.setAdapter(courseViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getUserCourse("K4MAhGn3o0pSozjqOr8g");
    }

    private void refreshCourse() {
        courses.clear();
        initRecyclerView();
    }


    @Override
    public void onRefresh() {
        refreshCourse();
    }

}
