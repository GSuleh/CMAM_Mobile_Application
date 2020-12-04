package com.example.cmamapplication;

public class ResourceClass {
    String type, product_name,serial_number;
    Long committee_id,starting_inventory, inventory_received, inventory_available, inventory_allocated, maximum_inventory, minimum_inventory;

    public ResourceClass() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }

    public Long getCommittee_id() {
        return committee_id;
    }

    public void setCommittee_id(Long committee_id) {
        this.committee_id = committee_id;
    }

    public Long getStarting_inventory() {
        return starting_inventory;
    }

    public void setStarting_inventory(Long starting_inventory) {
        this.starting_inventory = starting_inventory;
    }

    public Long getInventory_received() {
        return inventory_received;
    }

    public void setInventory_received(Long inventory_received) {
        this.inventory_received = inventory_received;
    }

    public Long getInventory_available() {
        return inventory_available;
    }

    public void setInventory_available(Long inventory_available) {
        this.inventory_available = inventory_available;
    }

    public Long getInventory_allocated() {
        return inventory_allocated;
    }

    public void setInventory_allocated(Long inventory_allocated) {
        this.inventory_allocated = inventory_allocated;
    }

    public Long getMaximum_inventory() {
        return maximum_inventory;
    }

    public void setMaximum_inventory(Long maximum_inventory) {
        this.maximum_inventory = maximum_inventory;
    }

    public Long getMinimum_inventory() {
        return minimum_inventory;
    }

    public void setMinimum_inventory(Long minimum_inventory) {
        this.minimum_inventory = minimum_inventory;
    }

    public ResourceClass(String type, String product_name, String serial_number, Long committee_id, Long starting_inventory, Long inventory_received, Long inventory_available, Long inventory_allocated, Long maximum_inventory, Long minimum_inventory) {
        this.type = type;
        this.product_name = product_name;
        this.serial_number = serial_number;
        this.committee_id = committee_id;
        this.starting_inventory = starting_inventory;
        this.inventory_received = inventory_received;
        this.inventory_available = inventory_available;
        this.inventory_allocated = inventory_allocated;
        this.maximum_inventory = maximum_inventory;
        this.minimum_inventory = minimum_inventory;
    }
}
