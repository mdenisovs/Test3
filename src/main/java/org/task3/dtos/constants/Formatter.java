package org.task3.dtos.constants;

import java.time.format.DateTimeFormatter;

public final class Formatter {

    public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    private Formatter() {}
}
