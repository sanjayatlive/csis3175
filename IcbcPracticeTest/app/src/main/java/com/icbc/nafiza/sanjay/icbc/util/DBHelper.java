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
import com.icbc.nafiza.sanjay.icbc.activities.RegisterActivity;
import com.icbc.nafiza.sanjay.icbc.bean.Item;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    static SQLiteDatabase db;

    static Context ctx;

    public DBHelper(Context ctx) {
  /*  SQLiteDatabase.OpenParams.Builder builder = new SQLiteDatabase.OpenParams.Builder();
    builder.addOpenFlags(SQLiteDatabase.NO_LOCALIZED_COLLATORS);

    SQLiteDatabase.OpenParams openParams = new SQLiteDatabase.OpenParams();

   // SQLiteDatabase.OpenParams.Builder
    super(ctx, ctx.getResources().getString(R.string.dbName)
            , Integer.parseInt(ctx.getResources().getString(R.string.dbVersion))
    , );*/


        super(ctx, ctx.getResources().getString(R.string.dbName), null, Integer.parseInt(ctx.getResources().getString(R.string.dbVersion)));
        //setOpenParams();

        db = getReadableDatabase();

        this.ctx = ctx;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTables() {

        db = getReadableDatabase();

        try {

            String dropQuestionsTable = "DROP TABLE IF EXISTS QUESTIONS";

            String dropProgressTable = "DROP TABLE IF EXISTS PROGRESS";

            String dropUsersTable = "DROP TABLE IF EXISTS USERS";


            String createQuestionsTable = "CREATE TABLE QUESTIONS " +
                    "(questionid numeric,question text, answer text, distractor1 text,distractor2 text,distractor3 text);";


            String createProgressTable = "CREATE TABLE PROGRESS " +
                    "(userid numeric, questionid numeric, status numeric);";



            db.execSQL(dropQuestionsTable);
            db.execSQL(dropProgressTable);
            // db.execSQL(dropUsersTable);


          //  db.execSQL(createUsersTable);
            db.execSQL(createQuestionsTable);
            db.execSQL(createProgressTable);

            //   Toast.makeText(ctx,"Tables Created", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            //   Toast.makeText(ctx,e.getMessage(), Toast.LENGTH_LONG).show();
            Snackbar.make(((RegisterActivity) ctx).findViewById(R.id.regConstLayout), e.getMessage(), Snackbar.LENGTH_LONG).show();

        }


        try{
            String createUsersTable = "CREATE TABLE IF NOT EXISTS USERS " +
                    "(userid integer primary key autoincrement not null, username text, password text, email text);";
            db.execSQL(createUsersTable);

        }catch(Exception e){
            Snackbar.make(((RegisterActivity)ctx).findViewById(R.id.regConstLayout), e.getMessage(), Snackbar.LENGTH_LONG).show();
        }

    }



    public static void addQuestionsToDB(List<Item> dataList) {
        try {
            ContentValues cv;
            long result = 0;
            for (int i = 0; i < dataList.size(); i++) {
                cv = new ContentValues();
                cv.put("questionid", dataList.get(i).getId());
                cv.put("question", dataList.get(i).getQuestion());
                cv.put("answer", dataList.get(i).getAnswer());
                cv.put("distractor1", dataList.get(i).getDistractor1());
                cv.put("distractor2", dataList.get(i).getDistractor2());
                cv.put("distractor3", dataList.get(i).getDistractor3());
                result = db.insert("QUESTIONS", null, cv);
            }

            if (result == -1) {
                //    txtViewMessage.
                //     Toast.makeText(ctx,"Questions couldn't be added", Toast.LENGTH_SHORT).show();
                Snackbar.make(((MainActivity) ctx).findViewById(R.id.constraintLayout), "Questions couldn't be added", Snackbar.LENGTH_LONG).show();

            } else {
                // Toast.makeText(ctx,"Questions added", Toast.LENGTH_SHORT).show();
                Snackbar.make(((MainActivity) ctx).findViewById(R.id.constraintLayout), "Questions added", Snackbar.LENGTH_LONG).show();


            }

        } catch (Exception e) {

        }

    }

    public static List<Item> getQuestionsFromDB() {

        List<Item> dataList = new ArrayList<>();
        Item item;

        String queryStr = "SELECT * FROM QUESTIONS;";
        try {
            Cursor cur = db.rawQuery(queryStr, null);


            if (cur != null) {
                cur.moveToFirst();
                while (!cur.isAfterLast()) {
                    item = new Item();
                    item.setId(cur.getInt(0));
                    item.setQuestion(cur.getString(1));
                    item.setAnswer(cur.getString(2));
                    item.setDistractor1(cur.getString(3));
                    item.setDistractor2(cur.getString(4));
                    item.setDistractor3(cur.getString(5));
                    dataList.add(item);

                    System.out.println("<<<<QUESTIONS>>>>" + cur.getInt(0) +
                            " " + cur.getString(1) + " " + cur.getString(2) + " " +
                            cur.getString(3) + " " + cur.getString(4) + " "
                            + cur.getString(5) + " ");
                    cur.moveToNext();
                }
            }
        } catch (Exception e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        return dataList;
    }


    public static void addResponseToDB(int questionId, int status) {
        try {
            ContentValues cv;
            long result = 0;

            cv = new ContentValues();
            cv.put("userid", 0);
            cv.put("questionid", questionId);
            cv.put("status", status);

                /*cv.put("questionid",dataList.get(i).getId());
                cv.put("question",dataList.get(i).getQuestion());
                cv.put("answer",dataList.get(i).getAnswer());
                cv.put("distractor1",dataList.get(i).getDistractor1());
                cv.put("distractor2",dataList.get(i).getDistractor2());
                cv.put("distractor3",dataList.get(i).getDistractor3());*/
            result = db.insert("PROGRESS", null, cv);

            if (result == -1) {
                //    txtViewMessage.
                Toast.makeText(ctx, "Answer couldn't be submitted", Toast.LENGTH_SHORT).show();
                //   Snackbar.make(((DetailActivity)ctx).findViewById(R.id.constraintLayout), "Answer couldn't be submitted", Snackbar.LENGTH_LONG).show();

            } else {
                //   Toast.makeText(ctx,"Answer submitted successfully", Toast.LENGTH_SHORT).show();
                // Snackbar.make(((MainActivity)ctx).findViewById(R.id.constraintLayout), "Answer submitted successfully", Snackbar.LENGTH_LONG).show();


            }

        } catch (Exception e) {

        }

    }

    public static int getResultFromDB(int questionId) {

        int status = 0;

        String queryStr = "SELECT status FROM PROGRESS WHERE questionid = " + questionId + ";";

        try {
            Cursor cur = db.rawQuery(queryStr, null);


            if (cur != null) {
                cur.moveToFirst();
                if (!cur.isAfterLast())
                    status = cur.getInt(0);
            }
        } catch (Exception e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        return status;


    }


    public static int getScoreFromDB(int userId) {
        String queryStr = "SELECT COUNT(*) count FROM PROGRESS WHERE status=1 and userid = 0;";

        int count = 0;


        try {
            Cursor cur = db.rawQuery(queryStr, null);


            if (cur != null) {
                cur.moveToFirst();
                if (!cur.isAfterLast())
                    count = cur.getInt(0);
            }
        } catch (Exception e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        return count;
    }


    public static void insertUserDataToDB(String username, String password, String email) {
        try {

            ContentValues cv;
            long result = 0;

            cv = new ContentValues();
            cv.put("username", username);
            cv.put("password", password);
            cv.put("email", email);
            result = db.insert("USERS", null, cv);

            if (result == -1) {
                //    txtViewMessage.
                Snackbar.make(((RegisterActivity) ctx).findViewById(R.id.regConstLayout), "Registration Unsuccessfull", Snackbar.LENGTH_LONG).show();
                //   Snackbar.make(((DetailActivity)ctx).findViewById(R.id.constraintLayout), "Answer couldn't be submitted", Snackbar.LENGTH_LONG).show();

            } else {
                Snackbar.make(((RegisterActivity) ctx).findViewById(R.id.regConstLayout), "Registration Successfull", Snackbar.LENGTH_LONG).show();
                // Snackbar.make((this.findViewById(R.id.constraintLayout), "Answer submitted successfully", Snackbar.LENGTH_LONG).show();


            }
        } catch (Exception e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public static int getLoginResultFromDB(String logUser, String logPass) {

        int status = -1;
        int dbstatus=-1;

        String queryStr = "SELECT userid FROM USERS WHERE username = '"+ logUser +"' AND password = '"+ logPass +"' ;";

        System.out.println("<<<<<<<>>>>>>>>>>>>>" + queryStr);

        try {
            Cursor cur = db.rawQuery(queryStr, null);


            /*if (cur != null) {
                cur.moveToFirst();
                if (!cur.isAfterLast()){
                    dbstatus = cur.getInt(0);
                    if(status==dbstatus)
                        status=1;
                }}*/


            if (cur != null) {
                cur.moveToFirst();
                if (!cur.isAfterLast())
                    status = cur.getInt(0);
            }


        } catch (Exception e) {
            Toast.makeText(ctx, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        return status;

    }

}
