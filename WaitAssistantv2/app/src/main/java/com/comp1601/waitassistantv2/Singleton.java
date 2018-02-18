package com.comp1601.waitassistantv2;

import android.widget.TableLayout;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by sahilkapal on 2018-02-18.
 */

public class Singleton {

    private static Singleton singleton = new Singleton( );
    private TableLayout table;

//    private static FirebaseDatabase mDatabase;
//
//    public static FirebaseDatabase getDatabase() {
//        if (mDatabase == null) {
//            mDatabase = FirebaseDatabase.getInstance();
//            mDatabase.setPersistenceEnabled(true);
//        }
//        return mDatabase;
//    }



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
