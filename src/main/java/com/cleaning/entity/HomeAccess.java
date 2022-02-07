package com.cleaning.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum HomeAccess {
    @JsonProperty("Meet")
    Meet("Meet"),

    @JsonProperty("Key In Mailbox")
    KeyMailbox("Key In Mailbox"),

    @JsonProperty("Pickup Key")
    PickupKey("Pickup Key"),

    @JsonProperty("Call")
    Call("Call");

    private final String label;

    HomeAccess(String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
