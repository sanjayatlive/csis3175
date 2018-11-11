package com.icbc.nafiza.sanjay.icbc.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.icbc.nafiza.sanjay.icbc.R;
import com.icbc.nafiza.sanjay.icbc.activities.MainActivity;
import com.icbc.nafiza.sanjay.icbc.bean.Item;

import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

static SQLiteDatabase db;

static Context ctx;

public DBHelper(Context ctx){

    super(ctx, ctx.getResources().getString(R.string.dbName), null, Integer.parseInt(ctx.getResources().getString(R.string.dbVersion)));
    //setOpenParams();
    this.ctx = ctx;
}

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTables(){

    db = getReadableDatabase();
        try{

            String dropQuestionsTable = "DROP TABLE IF EXISTS QUESTIONS";

            String dropProgressTable = "DROP TABLE IF EXISTS PROGRESS";


            String createQuestionsTable = "CREATE TABLE QUESTIONS " +
                    "(id numeric,question text, answer text, distractor1 text,distractor2 text,distractor3 text);";

            String createProgressTable = "CREATE TABLE PROGRESS " +
                    "(id numeric,attempt boolean, correct boolean);";


            db.execSQL(dropQuestionsTable);
            db.execSQL(dropProgressTable);

            db.execSQL(createQuestionsTable);
            db.execSQL(createProgressTable);

            Toast.makeText(ctx,"Tables Created", Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Toast.makeText(ctx,e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public static void addQuestionsToDB(List<Item> dataList)
    {
        try{
            ContentValues cv;
            long result=0;
            for(int i=0;i<dataList.size();i++){
               cv = new ContentValues();
                cv.put("id",dataList.get(i).getId());
                cv.put("question",dataList.get(i).getQuestion());
                cv.put("answer",dataList.get(i).getAnswer());
                cv.put("distractor1",dataList.get(i).getDistractor1());
                cv.put("distractor2",dataList.get(i).getDistractor2());
                cv.put("distractor3",dataList.get(i).getDistractor3());
                result = db.insert("QUESTIONS", null, cv);
            }

            if(result==-1){
                //    txtViewMessage.
                Toast.makeText(ctx,"Questions couldn't be added", Toast.LENGTH_SHORT).show();
               // Snackbar.make(Context.findViewById(R.id.constraint), "", Snackbar.LENGTH_LONG).show();

            }else
            {
                Toast.makeText(ctx,"Questions added", Toast.LENGTH_SHORT).show();
                browseStudentRecs();
            }

        }catch (Exception e){

        }
    }

    public static void browseStudentRecs(){
        String queryStr = "SELECT * FROM QUESTIONS;";
        try{
            Cursor cur = db.rawQuery(queryStr, null);


            if(cur!=null)
            {
                cur.moveToFirst();
                while(!cur.isAfterLast()){
                    System.out.println("<<<<QUESTIONS>>>>" + cur.getInt(0) +
                    " " + cur.getString(1) + " " + cur.getString(2) + " " +
                            cur.getString(3) + " " + cur.getString(4) + " "
                    + cur.getString(5) + " ");
                    cur.moveToNext();
                }
            }
        }catch (Exception e){
            Toast.makeText(ctx,e.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }



}
