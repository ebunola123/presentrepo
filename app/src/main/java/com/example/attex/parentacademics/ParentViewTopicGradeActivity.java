package com.example.attex.parentacademics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.attex.InitialLoginActivity;
import com.example.attex.R;
import com.example.attex.models.ModelAcademics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ParentViewTopicGradeActivity extends AppCompatActivity {

    TextView childGrade, comment, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_view_topic_grade);

        FirebaseAuth auth= FirebaseAuth.getInstance();
        FirebaseUser currentUser=auth.getCurrentUser();

        if(currentUser==null){
            Intent intent=new Intent(this, InitialLoginActivity.class);
            startActivity(intent);
        }

        Intent i = getIntent();
        String schoolID = i.getStringExtra("schoolID");
        String classGrade = i.getStringExtra("classGrade");
        String classID = i.getStringExtra("classID");
        String studentID = i.getStringExtra("studentID");
        String subject = i.getStringExtra("subject");
        String topic = i.getStringExtra("topic");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("AcademicRecord").child(schoolID).child(classGrade).child(classID).child(subject).child(topic).child(studentID);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ModelAcademics grade = snapshot.getValue(ModelAcademics.class);
                childGrade = findViewById(R.id.grade);
                childGrade.setText(grade.getGrade() + "%");

                title = findViewById(R.id.title);
                title.setText(subject + " - " + topic);

                comment = findViewById(R.id.comment);
                comment.setText(grade.getNote());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ArrayList<Float> list = new ArrayList<>();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AcademicRecord").child(schoolID).child(classGrade).child(classID).child(subject).child(topic);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String grade = null;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ModelAcademics academics = dataSnapshot.getValue(ModelAcademics.class);
                     grade = academics.getGrade();
                    float gradeValue = Float.parseFloat(grade);

                    list.add(gradeValue);
                }
                float totalAverage = 0;
                for (int i=0; i<list.size(); i++){
                    totalAverage = totalAverage + list.get(i);
                }

                float avg = totalAverage / list.size();
                System.out.println(avg);


                float rounded = (float) Math.round(avg);
                System.out.println(rounded);

                String avgString = Float.toString(rounded);

                TextView averageTV = findViewById(R.id.averageTV);
                averageTV.setText("Class Average: " + avgString + "%");

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}