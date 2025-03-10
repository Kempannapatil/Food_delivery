package com.tapfood.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, CartItem> items;

    public Cart() {
        this.items = new HashMap<>();
    }

    public void addItem(CartItem item) {
        int itemid = item.getItemid();
        if (items.containsKey(itemid)) {
            CartItem existingItem = items.get(itemid);
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            items.put(itemid, item);
        }
    }

    public void updateItem(int itemId, int quantity) {
        if (items.containsKey(itemId)) {
            if (quantity <= 0) {
                items.remove(itemId);
            } else {
                items.get(itemId).setQuantity(quantity);
            }
        }
    }

    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    // Method to calculate the total price of all items in the cart
    public double getTotalPrice() {
        return items.values().stream().mapToDouble(CartItem::getTotalPrice).sum();
    }
}
