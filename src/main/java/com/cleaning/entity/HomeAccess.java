package com.cleaning.entity;

public enum HomeAccess {
    Meet("Meet"),
    KeyMailbox("Key Mailbox"),
    PickupKey("Pickup Key"),
    Call("Call");

    private final String label;

    HomeAccess(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
