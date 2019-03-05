package com.johan_apps.exelsa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johan_apps.exelsa.R;

import java.util.ArrayList;
import java.util.Map;

public class InnerTeachingMaterialViewAdapter extends RecyclerView.Adapter<InnerTeachingMaterialViewAdapter.InnerTeachingMaterialViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<Map<String, Object>> documents;

    public InnerTeachingMaterialViewAdapter(ArrayList<Map<String, Object>> documents, Context context) {
        this.documents = documents;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public InnerTeachingMaterialViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.list_document, viewGroup, false);

        return new InnerTeachingMaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerTeachingMaterialViewHolder innerTeachingMaterialViewHolder, int i) {
        innerTeachingMaterialViewHolder.documentName.setText((String) documents.get(i).get("file_name"));
    }

    @Override
    public int getItemCount() {
        if (documents == null) {
            return 0;
        }
        return documents.size();
    }

    public class InnerTeachingMaterialViewHolder extends RecyclerView.ViewHolder {
        private TextView documentName;

        public InnerTeachingMaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            documentName = itemView.findViewById(R.id.document_name);
        }
    }
}
