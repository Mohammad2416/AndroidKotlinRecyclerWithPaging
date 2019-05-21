package com.momir.android.androidcodetest.dataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.momir.android.androidcodetest.dataModel.Authentication

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "AuthenticationDatabase"
        private const val TABLE_CONTACTS = "Authentication"
        private const val KEY_USERNAME = "userName"
        private const val KEY_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_USERNAME + " TEXT PRIMARY KEY,"
                + KEY_PASSWORD + " TEXT" + ")")
        db?.execSQL(CREATE_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_CONTACTS")
        onCreate(db)
    }


    fun addUser(user: Authentication): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_USERNAME, user.userName)
        contentValues.put(KEY_PASSWORD, user.password)
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()
        return success
    }

    fun findAllUser() : List<Authentication> {
        val userList: ArrayList<Authentication> = ArrayList<Authentication>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var userName: String
        var password: String
        if (cursor.moveToFirst()) {
            do {

                userName = cursor.getString(cursor.getColumnIndex(KEY_USERNAME))
                password = cursor.getString(cursor.getColumnIndex(KEY_PASSWORD))
                val emp = Authentication(userName = userName, password = password)
                userList.add(emp)
            } while (cursor.moveToNext())
        }
        return userList
    }

}