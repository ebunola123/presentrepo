package com.example.attex.adminmain;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.attex.R;
import com.example.attex.models.ModelTeacher;

import java.util.List;

public class AdminListClassesAdapter extends RecyclerView.Adapter<AdminListClassesAdapter.AdminListClassesViewHolder> {

    private final List<ModelTeacher> classList;
    private final Context context;

    String classGrade;
    String schoolID;

    public AdminListClassesAdapter(Context context, List<ModelTeacher> classList, String classGrade, String schoolID) {
        this.classList = classList;
        this.context = context;
        this.schoolID = schoolID;
        this.classGrade = classGrade;
    }

    @NonNull
    @Override
    public AdminListClassesAdapter.AdminListClassesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdminListClassesAdapter.AdminListClassesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_classes_layout, null));
    }

    @Override
    public void onBindViewHolder(@NonNull AdminListClassesViewHolder holder, int position) {
        ModelTeacher classes = classList.get(position);

        holder.teacherNameTV.setText(classes.getTeacherName());
        holder.classIDTV.setText("  -  " + classes.getClassID());
        holder.emailTV.setText(classes.getEmail());
        holder.classGradeTV.setText(classes.getClassGrade());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }


    public static class AdminListClassesViewHolder extends RecyclerView.ViewHolder{

        TextView teacherNameTV, classIDTV, emailTV, classGradeTV;

        public AdminListClassesViewHolder(@NonNull View itemView) {
            super(itemView);
            teacherNameTV = itemView.findViewById(R.id.teacherNameTV);
            classIDTV = itemView.findViewById(R.id.classIDTV);
            emailTV = itemView.findViewById(R.id.emailTV);
            classGradeTV = itemView.findViewById(R.id.classGradeTV);


        }
    }
}
