package com.johan_apps.exelsa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.johan_apps.exelsa.R;
import com.johan_apps.exelsa.model.TeachingMaterial;

import java.util.ArrayList;
import java.util.Map;

public class TeachingMaterialViewAdapter extends RecyclerView.Adapter<TeachingMaterialViewAdapter.TeachingMaterialViewHolder> {
    private ArrayList<TeachingMaterial> teachingMaterials;
    private LayoutInflater mInflater;
    private Context context;

    public TeachingMaterialViewAdapter(ArrayList<TeachingMaterial> teachingMaterials, Context context) {
        this.teachingMaterials = teachingMaterials;
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TeachingMaterialViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.list_ba, viewGroup, false);

        return new TeachingMaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeachingMaterialViewHolder teachingMaterialViewHolder, int i) {
        teachingMaterialViewHolder.baMaterialTitle.setText(teachingMaterials.get(i).getTitle());
        ArrayList<Map<String, Object>> documents = teachingMaterials.get(i).getDocuments();
        InnerTeachingMaterialViewAdapter innerTeachingMaterialViewAdapter = new InnerTeachingMaterialViewAdapter(documents, context);
        teachingMaterialViewHolder.recyclerView.setAdapter(innerTeachingMaterialViewAdapter);
        teachingMaterialViewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public int getItemCount() {
        if (teachingMaterials == null) {
            return 0;
        }
        return teachingMaterials.size();
    }

    public class TeachingMaterialViewHolder extends RecyclerView.ViewHolder {
        private TextView baMaterialTitle;
        private RecyclerView recyclerView;

        public TeachingMaterialViewHolder(@NonNull View itemView) {
            super(itemView);

            baMaterialTitle = itemView.findViewById(R.id.ba_material_title);
            recyclerView = itemView.findViewById(R.id.document_recycler_view);
        }
    }
}
