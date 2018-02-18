package com.comp1601.waitassistantv2;

import android.widget.TableLayout;

/**
 * Created by sahilkapal on 2018-02-18.
 */

public class Singleton {

    private static Singleton singleton = new Singleton( );
    private TableLayout table;


    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private Singleton() { }

    /* Static 'instance' method */
    public static Singleton getInstance( ) {
        return singleton;
    }

    /* Other methods protected by singleton-ness */
    protected static void demoMethod( ) {
        System.out.println("demoMethod for singleton");
    }

    public TableLayout getTable() {
        return table;
    }

    public void setTable(TableLayout table) {
        this.table = table;
    }
}
