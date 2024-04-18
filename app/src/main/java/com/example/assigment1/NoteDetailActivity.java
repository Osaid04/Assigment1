package com.example.assigment1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NoteDetailActivity extends AppCompatActivity {

    private EditText editTextNote;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        editTextNote = findViewById(R.id.edit_text_note);
        sharedPreferences = getSharedPreferences("Notes", MODE_PRIVATE);
    }

    public void onSaveClicked(View view) {
        String noteContent = editTextNote.getText().toString();
        if (noteContent.isEmpty()) {
            Toast.makeText(this, "Note is empty, please enter a note.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (isNoteExists(noteContent)) {
            Toast.makeText(this, "Note already exists.", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(System.currentTimeMillis() + "", noteContent);  // Use current time as unique key
        editor.apply();
        Toast.makeText(this, "Note saved.", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onDeleteClicked(View view) {
        String noteToDelete = editTextNote.getText().toString();
        if (noteToDelete.isEmpty()) {
            Toast.makeText(this, "Note is empty, nothing to delete.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!deleteNoteIfItExists(noteToDelete)) {
            Toast.makeText(this, "No such note found to delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note deleted.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private boolean isNoteExists(String noteContent) {
        return sharedPreferences.getAll().containsValue(noteContent);
    }

    private boolean deleteNoteIfItExists(String noteContent) {
        Map<String, ?> allNotes = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allNotes.entrySet()) {
            if (entry.getValue().equals(noteContent)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(entry.getKey());
                editor.apply();
                return true;
            }
        }
        return false;
    }


    public List<String> getAllNotes() {
        List<String> notes = new ArrayList<>();
        Map<String, ?> allEntries = sharedPreferences.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            notes.add(entry.getValue().toString());
        }
        return notes;
    }
}
