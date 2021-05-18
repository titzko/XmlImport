package com.titzko.testingThings.stax.application.model;

import javax.persistence.*;

@Entity
public class Item {

    @SequenceGenerator(
            name="item_id",
            sequenceName = "item_id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_id"
    )
    @Id
    private Long id;
    private String date;
    private String mode;
    private String unit;
    private String current;
    private String interactive;

    public Item() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item(String date, String mode, String unit, String current, String interactive) {
        this.date = date;
        this.mode = mode;
        this.unit = unit;
        this.current = current;
        this.interactive = interactive;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getCurrent() {
        return current;
    }
    public void setCurrent(String current) {
        this.current = current;
    }
    public String getInteractive() {
        return interactive;
    }
    public void setInteractive(String interactive) {
        this.interactive = interactive;
    }

    @Override
    public String toString() {
        return "Item [current=" + current + ", date=" + date + ", interactive="
                + interactive + ", mode=" + mode + ", unit=" + unit + "]";
    }
}


