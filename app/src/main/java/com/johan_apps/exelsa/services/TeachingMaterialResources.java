package com.johan_apps.exelsa.services;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.johan_apps.exelsa.callbacks.FailureCallback;
import com.johan_apps.exelsa.callbacks.SuccessCallback;
import com.johan_apps.exelsa.model.TeachingMaterial;

import java.util.ArrayList;
import java.util.Map;

public class TeachingMaterialResources {
    private FirebaseFirestore database;
    private FirebaseStorage storage;
    private CollectionReference collectionReference;
    private SuccessCallback<TeachingMaterial> successCallback;
    private FailureCallback<String> failureCallback;

    public TeachingMaterialResources() {
        database = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        collectionReference = database.collection("teaching-materials");

        successCallback = new SuccessCallback<TeachingMaterial>() {
            @Override
            public void onSuccess(TeachingMaterial data) {

            }
        };

        failureCallback = new FailureCallback<String>() {
            @Override
            public void onFailure(String data) {

            }
        };
    }

    public TeachingMaterialResources getTeachingMaterial(String documentId) {
        DocumentReference documentReference = collectionReference.document(documentId);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    ArrayList<Map<String, Object>> materials = (ArrayList<Map<String, Object>>) documentSnapshot.get("materials");
                    for (int i = 0; i < materials.size(); i++) {
                        ArrayList<Map<String, Object>> documents = (ArrayList<Map<String, Object>>) materials.get(i).get("documents");
                        TeachingMaterial teachingMaterial = new TeachingMaterial();
                        teachingMaterial.setTitle((String) materials.get(i).get("title"));
                        teachingMaterial.setDocuments((ArrayList<Map<String, Object>>) materials.get(i).get("documents"));
                        successCallback.onSuccess(teachingMaterial);
                    }
                } else {
                    successCallback.onSuccess(null);
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                failureCallback.onFailure(e.getMessage());
            }
        });

        return this;
    }

    public TeachingMaterialResources addOnSuccessCalback(SuccessCallback<TeachingMaterial> successCallback) {
        this.successCallback = successCallback;

        return this;
    }

    public TeachingMaterialResources addOnFailureCallback(FailureCallback<String> failureCallback) {
        this.failureCallback = failureCallback;

        return this;
    }


}
