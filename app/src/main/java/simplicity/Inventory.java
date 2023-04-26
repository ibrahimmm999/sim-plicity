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
        if (object instanceof Masakan || object instanceof Bahan_Makanan || object instanceof Non_Makanan) {
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

    public boolean contains(Bahan_Makanan bahan) {
        if (inventory.containsKey(bahan)) {
            int quantity = inventory.get(bahan);
            if (quantity > 0) {
                return true;
            }
        }
        return false;
    }

    public int getQuantity(Bahan_Makanan bahan) {
        if (inventory.containsKey(bahan)) {
            return inventory.get(bahan);
        } else {
            return 0;
        }
    }

    public boolean cariBarang(String namaObjek) {
        for (Object objek : inventory.keySet()) {
            if (objek instanceof Masakan || objek instanceof Bahan_Makanan || objek instanceof Non_Makanan) {
                if (((Objek) objek).getNamaObjek().equals(namaObjek)) {
                    int currentQuantity = inventory.get(objek);
                    if (currentQuantity > 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void hapusBarang(String namaObjek) {
        for (Object objek : inventory.keySet()) {
            if (objek instanceof Masakan || objek instanceof Bahan_Makanan || objek instanceof Non_Makanan) {
                if (((Objek) objek).getNamaObjek().equals(namaObjek)) {
                    int currentQuantity = inventory.get(objek);
                    if (currentQuantity > 1) {
                        inventory.put(objek, currentQuantity - 1);
                    } else {
                        inventory.remove(objek);
                    }
                    System.out.println("Objek " + namaObjek + " telah dihapus dari inventory.");
                    return;
                }
            }
        }
        System.out.println("Objek " + namaObjek + " tidak ditemukan dalam inventory.");
    }

}
