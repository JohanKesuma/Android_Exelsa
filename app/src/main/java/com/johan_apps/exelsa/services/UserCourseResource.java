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

import java.util.ArrayList;

public class UserCourseResource {
    private FirebaseFirestore database;
    private CollectionReference collectionReference;
    private SuccessCallback<Course> successCallback;
    private FailureCallback<String> failureCallback;

    public UserCourseResource() {
        database = FirebaseFirestore.getInstance();
        collectionReference = database.collection("user-courses");
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

    public UserCourseResource getCourseByUser(String uid) {
        DocumentReference documentReference = collectionReference
                .document(uid);

        documentReference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            ArrayList<String> courses = (ArrayList<String>) documentSnapshot.get("courses");
                            CourseResource courseResource = new CourseResource();
                            for (int i = 0; i < courses.size(); i++) {
                                courseResource.getCourseById(courses.get(i))
                                        .addOnSuccessCallback(new SuccessCallback<Course>() {
                                            @Override
                                            public void onSuccess(Course data) {
                                                successCallback.onSuccess(data);
                                            }
                                        })
                                        .addOnFailureCallback(new FailureCallback<String>() {
                                            @Override
                                            public void onFailure(String data) {
                                            }
                                        });
                            }
                        } else {
                            successCallback.onSuccess(null);
                        }
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

    public UserCourseResource addOnSuccessCallback(SuccessCallback<Course> successCallback) {
        this.successCallback = successCallback;

        return this;
    }

    public UserCourseResource addOnFailureCallback(FailureCallback<String> failureCallback) {
        this.failureCallback = failureCallback;

        return this;
    }
}
