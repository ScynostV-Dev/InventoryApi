package de.polygondev.inventoryapi.inventory;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryRegister {

    HashMap<String, Inventory> REGISTER = new HashMap<String, Inventory>();

    public void addInventory(Inventory inv, String name) {
        REGISTER.put(name, inv);
    }
}
