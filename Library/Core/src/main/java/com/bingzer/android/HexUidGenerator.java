package com.bingzer.android;

import java.util.UUID;

public final class HexUidGenerator {

    public static String generateUniqueId(){
        UUID uuid = UUID.randomUUID();
        return Long.toHexString(uuid.getMostSignificantBits());
    }

    private HexUidGenerator(){
        // nothing
    }
}
