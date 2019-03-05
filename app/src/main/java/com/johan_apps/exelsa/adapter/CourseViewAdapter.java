package com.johan_apps.exelsa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johan_apps.exelsa.R;
import com.johan_apps.exelsa.model.Course;

import java.util.ArrayList;

public class CourseViewAdapter extends RecyclerView.Adapter<CourseViewAdapter.CourseViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Course> courses;
    private OnCourseClickListener onCourseClickListener;

    public CourseViewAdapter(ArrayList<Course> courses, Context context) {
        mInflater = LayoutInflater.from(context);
        this.courses = courses;
        onCourseClickListener = new OnCourseClickListener() {
            @Override
            public void onClick(View view, int i) {

            }
        };
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.list_course, viewGroup, false);

        return new CourseViewHolder(view, this.onCourseClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder courseViewHolder, int i) {
        courseViewHolder.courseName.setText(courses.get(i).getCourseName());
        courseViewHolder.courseDay.setText(courses.get(i).getDay() + " / " + courses.get(i).getTimeStart() + " - " + courses.get(i).getTimeEnd());
        courseViewHolder.courseTeacher.setText(courses.get(i).getTeacher());
    }

    @Override
    public int getItemCount() {
        if (courses == null) {
            return 0;
        }

        return courses.size();
    }

    public void setOnCourseClickListener(OnCourseClickListener onCourseClickListener) {
        this.onCourseClickListener = onCourseClickListener;
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView courseName;
        private TextView courseDay;
        private TextView courseTeacher;

        public CourseViewHolder(@NonNull View itemView, OnCourseClickListener onCourseClickListener) {
            super(itemView);
            courseName = itemView.findViewById(R.id.course_name);
            courseDay = itemView.findViewById(R.id.course_day);
            courseTeacher = itemView.findViewById(R.id.course_teacher);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCourseClickListener.onClick(v, getAdapterPosition());
        }
    }

    public interface OnCourseClickListener {
        public void onClick(View view, int i);
    }
}
