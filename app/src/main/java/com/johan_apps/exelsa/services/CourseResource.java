package com.johan_apps.exelsa.services;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.johan_apps.exelsa.callbacks.FailureCallback;
import com.johan_apps.exelsa.callbacks.SuccessCallback;
import com.johan_apps.exelsa.model.Course;

public class CourseResource {
    private FirebaseFirestore database;
    private CollectionReference collectionReference;
    private SuccessCallback<Course> successCallback;
    private FailureCallback<String> failureCallback;

    public CourseResource() {
        database = FirebaseFirestore.getInstance();
        collectionReference = database.collection("courses");

        successCallback = new SuccessCallback<Course>() {
            @Override
            public void onSuccess(Course data) {

            }
        };

        failureCallback = new FailureCallback<String>() {
            @Override
            public void onFailure(String data) {

            }
        };
    }

    public CourseResource getCourseById(String courseId) {
        DocumentReference documentReference = collectionReference.document(courseId);
        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Course course = documentSnapshot.toObject(Course.class);
                        course.setId(documentSnapshot.getId());
                        successCallback.onSuccess(course);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        failureCallback.onFailure(e.getMessage());
                    }
                });

        return this;
    }

    public CourseResource addOnSuccessCallback(SuccessCallback<Course> successCallback) {
        this.successCallback = successCallback;

        return this;
    }

    public CourseResource addOnFailureCallback(FailureCallback<String> failureCallback) {
        this.failureCallback = failureCallback;

        return this;
    }

}
