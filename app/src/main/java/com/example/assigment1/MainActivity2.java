package com.example.assigment1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private ArrayAdapter<String> adapter;
    private ListView notesListView;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        notesListView = findViewById(R.id.notes_list_view);
        sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);

        updateListView();
        setupListViewClickListener();
    }

    private void setupListViewClickListener() {
        notesListView.setOnItemClickListener((parent, view, position, id) -> {

            String note = adapter.getItem(position);
            Intent intent = new Intent(MainActivity2.this, NoteDetailActivity.class);
            intent.putExtra("note_content", note);
            startActivity(intent);
        });
    }

    private void updateListView() {
        List<String> notes = new ArrayList<>();
        Map<String, ?> allNotes = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allNotes.entrySet()) {
            notes.add((String) entry.getValue());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        notesListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }

    public void onAddNoteClicked(View view) {
        Intent intent = new Intent(this, NoteDetailActivity.class);
        startActivity(intent);
    }
}
