package me.kodysimpson.jsonfilestorage.utils;

import com.google.gson.Gson;
import me.kodysimpson.jsonfilestorage.JSONFileStorage;
import me.kodysimpson.jsonfilestorage.models.Note;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoteStorageUtility {

    private static ArrayList<Note> notes = new ArrayList<Note>();

    public static Note createNote(Player p, String message){

        Note note = new Note(p.getDisplayName(), message);
        notes.add(note);

        return note;
    }

    public static void deleteNote(String id) throws IOException {
        for (Note note : notes) {
            if (note.getId().equalsIgnoreCase(id)) {
                notes.remove(note);
                break;
            }
        }
        saveNotes();
    }

    public static Note findNote(String id){
        for (Note note : notes) {
            if (note.getId().equalsIgnoreCase(id)) {
                return note;
            }
        }
        return null;
    }

    public static Note updateNote(String id, Note newNote){
        for (Note note : notes) {
            if (note.getId().equalsIgnoreCase(id)) {
                note.setPlayerName(newNote.getPlayerName());
                note.setMessage(newNote.getMessage());
            }
        }
        return null;
    }

    public static List<Note> findAllNotes(){
        return notes;
    }

    public static void loadNotes() throws IOException {

        Gson gson = new Gson();
        File file = new File(JSONFileStorage.getPlugin().getDataFolder().getAbsolutePath() + "/notes.json");
        if (file.exists()){
            Reader reader = new FileReader(file);
            Note[] n = gson.fromJson(reader, Note[].class);
            notes = new ArrayList<>(Arrays.asList(n));
            System.out.println("Notes loaded.");
        }

    }

    public static void saveNotes() throws IOException {

        Gson gson = new Gson();
        System.out.println(JSONFileStorage.getPlugin().getDataFolder().getAbsolutePath());
        File file = new File(JSONFileStorage.getPlugin().getDataFolder().getAbsolutePath() + "/notes.json");
        file.getParentFile().mkdir();
        file.createNewFile();
        Writer writer = new FileWriter(file, false);
        gson.toJson(notes, writer);
        writer.flush();
        writer.close();
        System.out.println("Notes saved.");

    }

}
