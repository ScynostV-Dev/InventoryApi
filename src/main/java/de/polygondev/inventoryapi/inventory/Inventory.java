package de.polygondev.inventoryapi.inventory;

import org.bukkit.inventory.ItemStack;

import java.awt.*;
import java.awt.geom.Dimension2D;
import java.util.ArrayList;

public abstract class Inventory{

    private String title;
    private int size, pointer = -1;
    private ArrayList<ItemStack> content = new ArrayList<>();

    /**
     * Constructor for Inventory
     * @param title title on top of chest
     */
    public Inventory(String title) {
        this.title = title;
    }

    public void addItem(ItemStack item, boolean override) {
        if (!override) {
            while (this.pointer + 1 < this.size && pointer + 1 < getMaxLimit() && content.get(pointer + 1) != null)
                pointer++;
        }

        calculateSize(pointer++);
        content.set(pointer, item);
    }

    private Dimension parseItemPosition(String input) {
        String[] cache = input.split(":");
        return new Dimension(Integer.parseInt(cache[0]), Integer.parseInt(cache[1]));
    }

    public void fillEmpty(ItemStack item, String pos1, String pos2) {
        Dimension p1 = parseItemPosition(pos1);
        int cpos1 = (p1.width -1) *9 +p1.height;

        Dimension p2 = parseItemPosition(pos2);
        int cpos2 = (p2.width -1) *9 +p2.height;

        fillEmpty(item, cpos1, cpos2);
    }

    public void fillEmpty(ItemStack item, int pos1, int pos2) {
        for (int i = pos1; i <= pos2 && i < getMaxLimit(); i++) {
            if (content.get(i) == null) {
                calculateSize(i);
                content.set(i, item);
            }
        }
    }

    public void setArea(ItemStack item, String pos1, String pos2) {
        Dimension p1 = parseItemPosition(pos1);
        int cpos1 = (p1.width -1) *9 +p1.height;

        Dimension p2 = parseItemPosition(pos2);
        int cpos2 = (p2.width -1) *9 +p2.height;

        setArea(item, cpos1, cpos2);
    }

    public void setArea(ItemStack item, int pos1, int pos2) {
        for (int i = pos1; i <= pos2 && i < getMaxLimit(); i++) {
            calculateSize(i);
            content.set(i, item);
        }
    }

    public void setItem(int pos, ItemStack item) {
        calculateSize(pos);
        content.set(pos, item);
    }

    public void removeItem(int pos) {
        calculateSize(pos);
        content.set(pos, null); //TODO? könnte error sein
    }

    public void removeLast() {
        content.remove(content.size() -1);
    }

    public int getMaxLimit() {
        return 54;
    }

    private void calculateSize(int pos) {
        if(pos > this.size) {
            for (int i = 1; i <= 6; i++) {
                if (pos >= (i - 1) * 9 && pos <= (i * 9) - 1) {
                    this.size = i * 9;
                }
            }
        }
    }

    public abstract void clickEvent();
    public abstract void closeEvent();
}
