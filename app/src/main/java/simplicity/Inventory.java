package simplicity;

import java.util.HashMap;

public class Inventory {

    private HashMap<Object, Integer> inventory;

    public Inventory() {
        inventory = new HashMap<Object, Integer>();
    }

    public HashMap<Object, Integer> getInventory() {
        return inventory;
    }

    public void addInventory(Object object) {
        if (object instanceof Masakan || object instanceof Bahan_Makanan) {
            if (inventory.containsKey(object)) {
                int currentQuantity = inventory.get(object);
                inventory.put(object, currentQuantity + 1);
            } else {
                inventory.put(object, 1);
            }
        } else {
            System.out.println("Object cannot be added to inventory.");
        }
    }

    public void removeInventory(Object object) {
        if (inventory.containsKey(object)) {
            int currentQuantity = inventory.get(object);
            if (currentQuantity > 1) {
                inventory.put(object, currentQuantity - 1);
            } else {
                inventory.remove(object);
            }
        } else {
            System.out.println("Object not found in inventory.");
        }
    }

}

public boolean contains(Bahan_Makanan bahan) {
    if (inventory.containsKey(bahan)) {
        int quantity = inventory.get(bahan);
        if (quantity > 0) {
            return true;
        }
    }
    return false;
}