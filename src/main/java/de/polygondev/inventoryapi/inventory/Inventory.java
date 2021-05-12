package de.polygondev.inventoryapi.inventory;

import de.polygondev.inventoryapi.InventoryApi;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This is the Inventory Class for the whole API
 */
public abstract class Inventory {

    private Player player = null;
    private String title;
    private String name;
    private int size, pointer = -1;
    private ArrayList<ItemStack> content = new ArrayList<>();
    private ArrayList<Executor> executors = new ArrayList<>();
    private boolean isOpen = false;
    private ArrayList<Boolean> protectedSlots = new ArrayList<>();
    private boolean allowPlayerInventory = false;

    /**
     * Create your Inventory
     * @param title The title of the Inventory (can have Colors)
     * @param name
     */
    public Inventory(String title, String name) {
        this.title = title;
        this.name = name;

        for (int i = 1; i < (9*6); i++) {
            protectedSlots.add(true);
        }
    }

    public String getName() {
        return this.name;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public boolean isOpen() {
        return isOpen;
    }

    /**
     *
     * @param item
     * @param override
     */
    public void addItem(ItemStack item, boolean override) {
        if (!override) {
            while (this.pointer + 1 < this.size && pointer + 1 < getMaxLimit() && pointer + 1 < content.size() && content.get(pointer + 1) != null)
                pointer++;
        }

        calculateSize(pointer++, false);
        setItem(pointer, item);
        updateInventory();
    }

    /**
     *
     * @param item
     * @param pos1
     * @param pos2
     */
    public void fillEmpty(ItemStack item, String pos1, String pos2) {
        int cpos1 = parseItemPosition(pos1);
        int cpos2 = parseItemPosition(pos2);

        fillEmpty(item, cpos1, cpos2);
    }

    /**
     *
     * @param item
     * @param pos1
     * @param pos2
     */
    public void fillEmpty(ItemStack item, int pos1, int pos2) {
        for (int i = pos1; i <= pos2 && i < getMaxLimit(); i++) {
            if (content.get(i) == null) {
                calculateSize(i, false);
                setItem(i, item);
            }
        }
        updateInventory();
    }

    /**
     *
     * @param item
     * @param pos1
     * @param pos2
     */
    public void setArea(ItemStack item, String pos1, String pos2) {
        int cpos1 = parseItemPosition(pos1);
        int cpos2 = parseItemPosition(pos2);

        setArea(item, cpos1, cpos2);
    }

    public void setArea(ItemStack item, int pos1, int pos2) {

        for (int i = pos1; i <= pos2 && i < getMaxLimit(); i++) {
            calculateSize(i, false);
            setItem(i, item);
        }
        updateInventory();
    }

    /**
     *
     * @param pos
     * @param item
     * @return
     */
    public boolean setItem(String pos, ItemStack item) {

        int cpos1 = parseItemPosition(pos);
        return setItem(cpos1, item);
    }

    /**
     *
     * @param pos
     * @param item
     * @return
     */
    public boolean setItem(int pos, ItemStack item) {
        while (content.size() <= pos) {
            content.add(null);
        }

        if (pos < 54) {
            content.set(pos, item);
            calculateSize(content.size(), false);
            updateInventory();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param pos
     */
    public void removeItem(String pos) {
        int cpos = parseItemPosition(pos);

        removeItem(cpos);
    }

    /**
     *
     * @param pos
     */
    public void removeItem(int pos) {
        if (pos > 0) {
            calculateSize(pos, false);
            setItem(pos, null);
            updateInventory();
        }
    }

    /**
     *
     */
    public void removeLast() {
        if(pointer > content.size() - 2)
            pointer = content.size() -2;
        calculateSize(pointer, true);
        content.remove(content.size() -1);
        updateInventory();
    }

    public void removeAll() {
        content.clear();
        calculateSize(pointer, true);
        updateInventory();
    }

    /**
     *
     * @return
     */
    public int getMaxLimit() {
        return 54;
    }

    /**
     *
     * @param rows
     * @return
     */
    public boolean setRows(int rows) {
        if (rows <= 6) {
            this.size = rows * 9;
            updateInventory();
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param pos
     * @param executor
     */
    public void setExecutor(int pos, Executor executor){
        while (executors.size() <= pos) {
            executors.add(null);
        }

        executors.set(pos, executor);
    }

    public void setExecutor(String pos, Executor executor){
        setExecutor(parseItemPosition(pos) ,executor);
    }

    public void removeExecutor(int pos) {
        executors.remove(pos);
    }

    public void removeExecutor(String pos) {
        removeExecutor(parseItemPosition(pos));
    }

    public void removeAllExecutors() {
        executors.clear();
    }

    /**
     *
     * @param pos
     * @return
     */
    public Executor getExecutor(int pos){
        if(pos < executors.size() && pos >= 0)
            return executors.get(pos);
        return null;
    }

    public boolean isSlotProtected(int pos) {
        if (pos < getMaxLimit())
        {
            return protectedSlots.get(pos);
        }
        return true;
    }

    public void setSlotProtection(int pos, boolean protection) {
        if (pos < getMaxLimit())
        {
            protectedSlots.set(pos, protection);
        }
    }

    public void setSlotProtection(String pos, boolean protection) {
        setSlotProtection(parseItemPosition(pos), protection);
    }

    public void setAreaSlotProtection(int pos1, int pos2, boolean protection) {
        for (int i = pos1; i <= pos2 && i < getMaxLimit(); i++) {
            setSlotProtection(i, protection);
        }
    }

    public void setAreaSlotProtection(String pos1, String pos2, boolean protection) {
        int p1 = parseItemPosition(pos1);
        int p2 = parseItemPosition(pos2);

        setAreaSlotProtection(p1, p2, protection);
    }

    public boolean isAllowPlayerInventory() {
        return allowPlayerInventory;
    }

    public void setAllowPlayerInventory(boolean allow) {
        allowPlayerInventory = allow;
    }

    public void openInventory() {
        if (!this.isOpen) {
            org.bukkit.inventory.Inventory inv = Bukkit.createInventory(player, this.size, Component.text(this.title));

            ItemStack[] iscache = new ItemStack[content.size()];
            content.toArray(iscache);
            inv.setContents(iscache);
            player.openInventory(inv);
            this.isOpen = true;
        }
    }

    public void updateInventory() {
        boolean resize = false;
        if (this.isOpen) {
            org.bukkit.inventory.Inventory inv = player.getOpenInventory().getTopInventory();
            InventoryView iV = player.getOpenInventory();

            if (this.size != inv.getSize() && this.size % 9 == 0 && this.size != 0) {
                player.closeInventory();
                this.isOpen = false;
                openInventory();
                //InventoryApi.INV_REGISTER.updateInventoryForPlayer(player, this.getName(), this);
            }

            ItemStack[] iscache = new ItemStack[content.size()];
            content.toArray(iscache);
            inv.setContents(iscache);
            player.updateInventory();
        }
    }

    /**
     *
     * @param e
     */
    public abstract void clickEvent(InventoryClickEvent e);

    /**
     *
     * @param e
     */
    public abstract void closeEvent(InventoryCloseEvent e);

    /**
     * DO NOT NEVER USE THIS!!!!!!!!
     * @param e
     */
    public void internCloseEvent(InventoryCloseEvent e) {
        this.isOpen = false;
        closeEvent(e);
    }

    private void calculateSize(int pos, boolean shrink) {
        if(!shrink && pos > this.size) {
            for (int i = 1; i <= 6; i++) {
                if (pos >= (i - 1) * 9 && pos <= (i * 9) - 1) {
                    this.size = i * 9;
                }
            }
        }else{
            if(pos<this.size - 9){
                this.size -= 9;
            }
        }
    }

    private int parseItemPosition(String input) {
        String[] cache = input.split(":");
        Dimension p1 = new Dimension(Integer.parseInt(cache[0]), Integer.parseInt(cache[1]));
        return (p1.width -1) *9 +p1.height -1;
    }


}
