package me.kodysimpson.jsonfilestorage.menu.menus;

import me.kodysimpson.jsonfilestorage.menu.PlayerMenuUtility;
import me.kodysimpson.jsonfilestorage.models.Note;
import me.kodysimpson.jsonfilestorage.utils.NoteStorageUtility;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.AbstractPlayerMenuUtility;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class ConfirmDeleteMenu extends Menu {

    public ConfirmDeleteMenu(AbstractPlayerMenuUtility pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
        return "Confirm: Delete Note";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {

        PlayerMenuUtility playerMenuUtility = (PlayerMenuUtility) pmu;

        switch (e.getCurrentItem().getType()){
            case GREEN_BANNER:
                Note note = playerMenuUtility.getNoteToDelete();
                try {
                    NoteStorageUtility.deleteNote(note.getId());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                pmu.getOwner().sendMessage(ChatColor.GREEN + "The note has been deleted.");
                pmu.getOwner().closeInventory();
                break;
            case RED_BANNER:
                MenuManager.openMenu(DeleteNote.class, pmu);
                break;
        }

    }

    @Override
    public void setMenuItems() {

        ItemStack yes = makeItem(Material.GREEN_BANNER, "Delete Note");
        ItemStack no = makeItem(Material.RED_BANNER, "Nevermind");

        inventory.setItem(3, yes);
        inventory.setItem(5, no);

        setFillerGlass();

    }
}
