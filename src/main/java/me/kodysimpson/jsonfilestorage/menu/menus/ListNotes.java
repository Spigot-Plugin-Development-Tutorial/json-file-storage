package me.kodysimpson.jsonfilestorage.menu.menus;

import me.kodysimpson.jsonfilestorage.models.Note;
import me.kodysimpson.jsonfilestorage.utils.NoteStorageUtility;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.AbstractPlayerMenuUtility;
import me.kodysimpson.simpapi.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ListNotes extends Menu {

    public ListNotes(AbstractPlayerMenuUtility pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
        return "List of Notes";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {

        if (e.getCurrentItem().getType() == Material.BARRIER){
            pmu.getOwner().closeInventory();
        }

    }

    @Override
    public void setMenuItems() {

        List<Note> findAllNotes = NoteStorageUtility.findAllNotes();
        for (int i = 0; i < findAllNotes.size(); i++) {
            Note note = findAllNotes.get(i);
            ItemStack item = makeItem(Material.PAPER, "Note #" + (i + 1), note.getMessage());
            inventory.addItem(item);
        }

        ItemStack close = makeItem(Material.BARRIER, "Close");

        inventory.setItem(49, close);

        setFillerGlass();

    }
}
