package com.ls.wod.util;

import lombok.Data;

/**
 *
 * @author Francisco
 */

@Data
public class Alert {
    private String icon;
    private String title;
    private String descripcion;

    public Alert() {
    }

    public Alert(String icon, String title, String descripcion) {
        this.icon = icon;
        this.title = title;
        this.descripcion = descripcion;
    }
}
