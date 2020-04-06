package de.polygondev.testplugin;

import de.polygondev.inventoryapi.inventory.Inventory;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

public class SimpleInventory extends Inventory {


    /**
     * @param title
     * @param name
     */
    public SimpleInventory(String title, String name) {
        super(title, name);


        ItemStack is = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemStack is1 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemStack is2 = new ItemStack(Material.BEDROCK);



        for (int i = 0; i < 20; i++) {
            this.addItem((i%2 == 1) ? is : is1, false);
        }

        for (int i = 0; i < 20; i++) {
            this.addItem((i%2 == 1) ? is1 : is, true);
        }

        for (int i = 0; i < 20; i++) {
            this.removeLast();
        }

        for (int i = 0; i < 5; i++) {
            this.addItem(is2, false);
        }

        setExecutor(5, this::thisissparta);

    }

    public void thisissparta(Player player, ClickType clickType) {
        if (clickType == ClickType.LEFT) {
            player.sendMessage("This is Spaaaaarta!!!!!!!!");
        } else if (clickType == ClickType.RIGHT) {
            player.sendMessage("Sei nicht so rechts, EY!");

            ItemStack is = new ItemStack(Material.DARK_OAK_WOOD);
            this.setItem("1:3", is);
        }
    }

    @Override
    public void clickEvent(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player p = (Player) e.getWhoClicked();
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_DESTROY, 10, 10);
        }
    }

    @Override
    public void closeEvent(InventoryCloseEvent e) {
        //Inventoryapi.INV_REGISTER.removeInventoryFromPlayer((Player) e.getPlayer(), "Katze");
    }
}
