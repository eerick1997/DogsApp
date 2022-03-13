package com.eerick.dogos.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.eerick.dogos.database.DogsContract.SQL_CREATE_ENTRIES

/**
 * DogsReaderDbHelper
 *
 * Implements all the required methods to use a database in our app
 *
 * @param context
 */
class DogsReaderDbHelper(context: Context) :
    SQLiteOpenHelper(context,
        DATABASE_NAME,
        null /* factory */,
        DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "Dogos.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}