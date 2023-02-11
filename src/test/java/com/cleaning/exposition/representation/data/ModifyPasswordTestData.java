package com.cleaning.exposition.representation.data;

import com.cleaning.exposition.representation.request.*;

public class ModifyPasswordTestData {

    public static ModifyPassword dummyModifyPassword() {
        return new ModifyPassword(
                "password",
                "newPassword"
        );
    }
}
