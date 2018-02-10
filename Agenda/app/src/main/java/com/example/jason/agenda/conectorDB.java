package com.example.jason.agenda;

public class conectorDB {

    private static Object dbh = null;
    private static Object dataBase = null;

    public static Object getDataBase() {
        return dataBase;
    }

    public static Object getDbh() {
        return dbh;
    }

    public static void setDataBase(Object dataBase) {
        conectorDB.dataBase = dataBase;
    }

    public static void setDbh(Object dbh) {
        conectorDB.dbh = dbh;
    }
}
