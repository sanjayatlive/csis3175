package com.icbc.nafiza.sanjay.icbc.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.icbc.nafiza.sanjay.icbc.R;
public class DBHelper extends SQLiteOpenHelper {

SQLiteDatabase db;

Context ctx;

public DBHelper(Context ctx){

    super(ctx, ctx.getResources().getString(R.string.dbName), null, Integer.parseInt(ctx.getResources().getString(R.string.dbVersion)));
    this.ctx = ctx;
}

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTables(){

    db = this.getReadableDatabase();
        try{

            String dropQuestionsTable = "DROP TABLE IF EXISTS QUESTIONS";


            String createQuestionsTable = "CREATE TABLE QUESTIONS " +
                    "(id text, name text, major text);";


            db.execSQL(dropQuestionsTable);

            db.execSQL(createQuestionsTable);

            Toast.makeText(ctx,"Tables Created", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(ctx,e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
