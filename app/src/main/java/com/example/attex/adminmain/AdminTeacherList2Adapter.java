package com.example.attex.adminmain;

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

public class AdminTeacherList2Adapter extends RecyclerView.Adapter<AdminTeacherList2Adapter.AdminTeacherList2ViewHolder> {

    private final List<ModelTeacher> teacherList;
    private final Context context;

    String schoolID, classGrade;

    public AdminTeacherList2Adapter(List<ModelTeacher> teacherList, Context context, String schoolID, String classGrade) {
        this.teacherList = teacherList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
    }

    @NonNull
    @Override
    public AdminTeacherList2Adapter.AdminTeacherList2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminTeacherList2Adapter.AdminTeacherList2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_teachers_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminTeacherList2Adapter.AdminTeacherList2ViewHolder holder, int position) {
        ModelTeacher teachers = teacherList.get(position);

        String classID = teachers.getClassID();
        holder.lastName.setText(teachers.getTeacherName());

        holder.lastName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TeachersStudentListActivity.class);
                intent.putExtra("classID", classID);
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("classGrade", classGrade);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public static class AdminTeacherList2ViewHolder extends RecyclerView.ViewHolder{

        Button lastName;
        public AdminTeacherList2ViewHolder(@NonNull View itemView) {
            super(itemView);
            lastName = itemView.findViewById(R.id.lastName);
        }
    }
}
