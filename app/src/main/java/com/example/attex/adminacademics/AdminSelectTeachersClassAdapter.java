package com.example.attex.adminacademics;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelTeacher;

import java.util.List;

public class AdminSelectTeachersClassAdapter extends RecyclerView.Adapter<AdminSelectTeachersClassAdapter.AdminSelectTeachersClassViewHolder> {

    private final List<ModelTeacher> classList;
    private final Context context;

    String classGrade, schoolID;

    public AdminSelectTeachersClassAdapter(List<ModelTeacher> classList, Context context, String classGrade, String schoolID) {
        this.classList = classList;
        this.context = context;
        this.classGrade = classGrade;
        this.schoolID = schoolID;
    }

    @NonNull
    @Override
    public AdminSelectTeachersClassAdapter.AdminSelectTeachersClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminSelectTeachersClassViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.select_class_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSelectTeachersClassAdapter.AdminSelectTeachersClassViewHolder holder, int position) {
        ModelTeacher className = classList.get(position);

        holder.teacherID.setText(className.getTeacherName());

        holder.teacherID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminSelectSubjectActivity.class);
                intent.putExtra("classID", className.getClassID());
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    public static class AdminSelectTeachersClassViewHolder extends RecyclerView.ViewHolder{
        Button teacherID;


        public AdminSelectTeachersClassViewHolder(@NonNull View itemView) {
            super(itemView);
            teacherID = itemView.findViewById(R.id.className);

        }
    }
}
