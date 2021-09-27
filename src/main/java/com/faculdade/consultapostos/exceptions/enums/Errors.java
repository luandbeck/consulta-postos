package com.faculdade.consultapostos.exceptions.enums;

import java.util.Locale;
import java.util.ResourceBundle;

public enum Errors {
    ATM001,
    ATM002,
    ATM003,
    ATM004,
    ATM005,
    ATM006,
    ATM007;

    public String getMessage(final Locale messageLocale) {
        final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages/exceptions", messageLocale);
        return resourceBundle.getString(name() + ".message");
    }
}
