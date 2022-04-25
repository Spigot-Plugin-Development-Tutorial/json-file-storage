package me.kodysimpson.jsonfilestorage.menu.menus;

import me.kodysimpson.jsonfilestorage.JSONFileStorage;
import me.kodysimpson.jsonfilestorage.menu.PlayerMenuUtility;
import me.kodysimpson.jsonfilestorage.models.Note;
import me.kodysimpson.jsonfilestorage.utils.NoteStorageUtility;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.AbstractPlayerMenuUtility;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;

public class DeleteNote extends Menu {

    public DeleteNote(AbstractPlayerMenuUtility pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
        return "Delete Note";
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

        PlayerMenuUtility playerMenuUtility = (PlayerMenuUtility) pmu;

        if (e.getCurrentItem().getType() == Material.BARRIER){
            pmu.getOwner().closeInventory();
        }else if (e.getCurrentItem().getType() == Material.PAPER){

            PersistentDataContainer container = e.getCurrentItem().getItemMeta().getPersistentDataContainer();
            Note note = NoteStorageUtility.findNote(container.get(new NamespacedKey(JSONFileStorage.getPlugin(), "noteid"), PersistentDataType.STRING));

            playerMenuUtility.setNoteToDelete(note);

            MenuManager.openMenu(ConfirmDeleteMenu.class, playerMenuUtility);

        }

    }

    @Override
    public void setMenuItems() {

        List<Note> findAllNotes = NoteStorageUtility.findAllNotes();
        for (int i = 0; i < findAllNotes.size(); i++) {
            Note note = findAllNotes.get(i);
            ItemStack item = makeItem(Material.PAPER, "Note #" + (i + 1), note.getMessage());
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.getPersistentDataContainer().set(new NamespacedKey(JSONFileStorage.getPlugin(), "noteid"), PersistentDataType.STRING, note.getId());
            item.setItemMeta(itemMeta);
            inventory.addItem(item);
        }

        ItemStack close = makeItem(Material.BARRIER, "Close");

        inventory.setItem(49, close);

        setFillerGlass();

    }
}
