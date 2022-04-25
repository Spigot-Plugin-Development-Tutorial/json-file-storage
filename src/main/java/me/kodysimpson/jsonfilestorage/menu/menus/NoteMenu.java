package me.kodysimpson.jsonfilestorage.menu.menus;

import me.kodysimpson.simpapi.colors.ColorTranslator;
import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.AbstractPlayerMenuUtility;
import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.MenuManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class NoteMenu extends Menu {

    public NoteMenu(AbstractPlayerMenuUtility pmu) {
        super(pmu);
    }

    @Override
    public String getMenuName() {
        return "Note Menu";
    }

    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {

        Player p = pmu.getOwner();

        switch (e.getCurrentItem().getType()){
            case WRITABLE_BOOK:
                p.sendMessage(ColorTranslator.translateColorCodes("&a&lCreate a note like this: &d/note create I really like this plugin"));
                p.closeInventory();
                break;
            case PAPER:
                MenuManager.openMenu(ListNotes.class, pmu.getOwner());
                break;
            case LAVA_BUCKET:
                MenuManager.openMenu(DeleteNote.class, pmu.getOwner());
                break;
            case BARRIER:
                pmu.getOwner().closeInventory();
                break;
        }

    }

    @Override
    public void setMenuItems() {

        ItemStack create = makeItem(Material.WRITABLE_BOOK, "Create Note");
        ItemStack list = makeItem(Material.PAPER, "List Notes");
        ItemStack delete = makeItem(Material.LAVA_BUCKET, "Delete Note");
        ItemStack close = makeItem(Material.BARRIER, "Close");

        inventory.setItem(11, create);
        inventory.setItem(13, list);
        inventory.setItem(15, delete);
        inventory.setItem(31, close);

        setFillerGlass();

    }
}
