package com.ctis487.ifdatabasenotgiven;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class TeamTable {
    public static String TABLE_NAME="team";
    public static String TEAM_ID = "_id";
    public static String TEAM_NAME = "name";
    public static String SUPPORTER = "supporter";

    public static String CREATE_TABLE_SQL="CREATE TABLE "+TABLE_NAME+" ( "+TEAM_ID+" number, "+TEAM_NAME +" text, "+ SUPPORTER +" text)";
    public static String DROP_TABLE_SQL = "DROP TABLE if exists "+TABLE_NAME;

    public static ArrayList<Team>  getAllTeam(DatabaseHelper dbHelper){
        Team anItem;
        ArrayList<Team> data = new ArrayList<>();
        Cursor cursor = dbHelper.getAllRecords(TABLE_NAME, null);
        Log.d("DATABASE OPERATIONS", cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int team_id = cursor.getInt(0);
            String team_name = cursor.getString(1);
            String supporter = cursor.getString(2);
            anItem = new Team(team_id, team_name, supporter);
            data.add(anItem);

        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static ArrayList<Team> findContact(DatabaseHelper dbHelper, String key) {
        Team anItem;
        ArrayList<Team> data = new ArrayList<>();
        String where = TEAM_NAME +" like '%"+key+"%'";

        Cursor cursor = dbHelper.getSomeRecords(TABLE_NAME, null, where);
        Log.d("DATABASE OPERATIONS",  where+", "+cursor.getCount()+",  "+cursor.getColumnCount());
        while(cursor.moveToNext()){
            int team_id = cursor.getInt(0);
            String team_name = cursor.getString(1);
            String supporter = cursor.getString(2);
            anItem = new Team(team_id, team_name, supporter);
            data.add(anItem);
        }
        Log.d("DATABASE OPERATIONS",data.toString());
        return data;
    }

    public static boolean insert(DatabaseHelper dbHelper, int id, String name, String supporter) {
        ContentValues contentValues = new ContentValues( );
        contentValues.put(TEAM_ID, id);
        contentValues.put(TEAM_NAME, name);
        contentValues.put(SUPPORTER, supporter);

        boolean res = dbHelper.insert(TABLE_NAME,contentValues);
        return res;
    }

    public static boolean update(DatabaseHelper dbHelper, int id, String name, String supporter) {

        ContentValues contentValues = new ContentValues( );
        contentValues.put(TEAM_NAME, name);
        contentValues.put(SUPPORTER, supporter);

        String where = TEAM_ID +" = "+id;
        boolean res = dbHelper.update(TABLE_NAME,contentValues,where );
        return res;
    }


}
