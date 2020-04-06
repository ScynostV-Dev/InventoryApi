package de.polygondev.inventoryapi.inventory;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import java.awt.*;
import java.util.ArrayList;

/**
 * This is the Inventory Class for the whole API
 */
public abstract class Inventory {

    private String title;
    String name;
    private int size, pointer = -1;
    private ArrayList<ItemStack> content = new ArrayList<>();
    private ArrayList<Executor> executors = new ArrayList<>();

    /**
     *
     * @param title
     * @param name
     */
    public Inventory(String title, String name) {
        this.title = title;
        this.name = name;
    }

    public String getName() {
        return this.name;
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
            calculateSize(pos, false);
            content.set(pos, item);
            return true;
        } else {
            return false;
        }

        //updateInventory();
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

    /**
     *
     * @param pos
     * @return
     */
    Executor getExecutor(int pos){
        if(pos < executors.size() && pos >= 0)
            return executors.get(pos);
        return null;
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

    void internCloseEvent(InventoryCloseEvent e) {

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
