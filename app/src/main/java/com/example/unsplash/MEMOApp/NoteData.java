package com.example.unsplash.MEMOApp;

public class NoteData
{
    int id;
      String title;
      String notes;

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
