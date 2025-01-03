package com.example.dogfood.activities;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.dogfood.R;
import com.example.dogfood.adapters.EducationAdapter;
import com.example.dogfood.models.EducationItem;

import java.util.ArrayList;
import java.util.List;

public class EducationActivity extends AppCompatActivity {
    ListView listViewEducation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        listViewEducation = findViewById(R.id.listViewEducation);

        // Dummy educational content
        List<EducationItem> educationItems = new ArrayList<>();
        educationItems.add(new EducationItem("Dog Nutrition Basics", "https://example.com/video1"));
        educationItems.add(new EducationItem("Best Foods for Puppies", "https://example.com/video2"));
        educationItems.add(new EducationItem("Senior Dog Nutrition Tips", "https://example.com/video3"));

        EducationAdapter adapter = new EducationAdapter(this, educationItems);
        listViewEducation.setAdapter(adapter);
    }
}
