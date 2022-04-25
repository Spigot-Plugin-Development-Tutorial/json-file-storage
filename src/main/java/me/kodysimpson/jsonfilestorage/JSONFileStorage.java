package me.kodysimpson.jsonfilestorage;

import me.kodysimpson.jsonfilestorage.commands.CreateNoteCommand;
import me.kodysimpson.jsonfilestorage.commands.NoteMenuCommand;
import me.kodysimpson.jsonfilestorage.menu.PlayerMenuUtility;
import me.kodysimpson.jsonfilestorage.utils.NoteStorageUtility;
import me.kodysimpson.simpapi.command.CommandList;
import me.kodysimpson.simpapi.command.CommandManager;
import me.kodysimpson.simpapi.command.SubCommand;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.List;

public final class JSONFileStorage extends JavaPlugin {

    private static JSONFileStorage plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic

        plugin = this;

        try {
            CommandManager.createCoreCommand(this, "note", "Create and list notes", "/note", new CommandList() {
                @Override
                public void displayCommandList(Player p, List<SubCommand> subCommandList) {
                    p.sendMessage("--------------------------------");
                    for (SubCommand subcommand : subCommandList){
                        p.sendMessage(subcommand.getSyntax() + " - " + subcommand.getDescription());
                    }
                    p.sendMessage("--------------------------------");
                }
            }, CreateNoteCommand.class, NoteMenuCommand.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        MenuManager.setup(getServer(), this, PlayerMenuUtility.class);

        try {
            NoteStorageUtility.loadNotes();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static JSONFileStorage getPlugin() {
        return plugin;
    }
}
