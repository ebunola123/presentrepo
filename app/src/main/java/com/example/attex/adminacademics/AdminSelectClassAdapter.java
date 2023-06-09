package com.example.attex.adminacademics;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.models.ModelTeacher;
import com.example.attex.R;

import java.util.List;

public class AdminSelectClassAdapter extends RecyclerView.Adapter<AdminSelectClassAdapter.AdminSelectClassViewHolder> {

    private final List<ModelTeacher> classList;
    private final Context context;

    String classGrade, schoolID, subject;

    public AdminSelectClassAdapter(List<ModelTeacher> classList, Context context, String classGrade, String schoolID, String subject) {
        this.classList = classList;
        this.context = context;
        this.classGrade = classGrade;
        this.schoolID = schoolID;
        this.subject = subject;
    }

    @NonNull
    @Override
    public AdminSelectClassAdapter.AdminSelectClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminSelectClassAdapter.AdminSelectClassViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.select_class_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminSelectClassAdapter.AdminSelectClassViewHolder holder, int position) {
        ModelTeacher className = classList.get(position);

        holder.teacherID.setText(className.getTeacherName());

        holder.teacherID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AdminViewResultsActivity.class);
                intent.putExtra("classID", className.getClassID());
                intent.putExtra("classGrade", classGrade);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("subject", subject);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }


    public static class AdminSelectClassViewHolder extends RecyclerView.ViewHolder{
        Button teacherID;

        public AdminSelectClassViewHolder(@NonNull View itemView) {
            super(itemView);

            teacherID = itemView.findViewById(R.id.className);
        }
    }
}
