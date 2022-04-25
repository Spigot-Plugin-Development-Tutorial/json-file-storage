package me.kodysimpson.jsonfilestorage.commands;

import me.kodysimpson.jsonfilestorage.utils.NoteStorageUtility;
import me.kodysimpson.simpapi.command.SubCommand;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.List;

public class CreateNoteCommand extends SubCommand {

    @Override
    public String getName() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create a new note";
    }

    @Override
    public String getSyntax() {
        return "/note create message goes here";
    }

    @Override
    public void perform(Player p, String[] args) {

        if (args.length > 0){
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < (args.length - 1); i++){
                stringBuilder.append(args[i]).append(" ");
            }
            stringBuilder.append(args[args.length - 1]);

            NoteStorageUtility.createNote(p, stringBuilder.toString());
            try {
                NoteStorageUtility.saveNotes();
            } catch (IOException e) {
                System.out.println("SAVING NOTES FAILED AAAAAAH!!!!");
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<String> getSubcommandArguments(Player player, String[] args) {
        return null;
    }
}
