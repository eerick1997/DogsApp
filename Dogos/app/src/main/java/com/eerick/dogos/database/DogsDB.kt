package com.eerick.dogos.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.eerick.dogos.objects.Dog

/**
 * DogsDB
 *
 * We use a singleton pattern here, to avoid multiple instances of
 * our database it helps to avoid use a lot of memory and reduce
 * the runtime of our app
 *
 * @param context
 */
class DogsDB(context: Context) {
    private val TAG: String = "DogsDB"
    companion object {
        @Volatile
        private var INSTANCE: DogsDB? = null
        /**
         * [getInstance] gets the only existing DogsDB instance
         * @param context
         * @return synchronized
         */
        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: DogsDB(context).also {
                INSTANCE = it
            }
        }
    }

    private val sqliteWriteDB: SQLiteDatabase by lazy {
        DogsReaderDbHelper(context).writableDatabase
    }

    private val sqliteReadDB: SQLiteDatabase by lazy {
        DogsReaderDbHelper(context).readableDatabase
    }

    /**
     * [add] Store a new record in the DB (just internal use)
     * @param dog
     * @return void
     */
    private fun add(dog: Dog) {
        Log.i(TAG, "add: ")
        val values = ContentValues().apply {
            put(DogsContract.DogsEntry.COLUMN_NAME, dog.name)
            put(DogsContract.DogsEntry.COLUMN_DESCRIPTION, dog.description)
            put(DogsContract.DogsEntry.COLUMN_AGE, dog.age)
            put(DogsContract.DogsEntry.COLUMN_IMAGE, dog.uri)
        }
        sqliteWriteDB.insert(DogsContract.DogsEntry.TABLE_NAME,
            null /* nullColumnHack */,
            values)
    }

    /**
     * [add] Stores an ArrayList of data
     * @param dogs
     * @return void
     */
    fun add(dogs: ArrayList<Dog>) {
        for (dog: Dog in dogs) {
            add(dog)
        }
    }

    /**
     * [get] Gets all the content of the table "dogs"
     * @param
     * @return dogs
     */
    fun get() : ArrayList<Dog> {
        val projection = arrayOf(
            DogsContract.DogsEntry.COLUMN_NAME,
            DogsContract.DogsEntry.COLUMN_DESCRIPTION,
            DogsContract.DogsEntry.COLUMN_AGE,
            DogsContract.DogsEntry.COLUMN_IMAGE)
        val cursor = sqliteReadDB.query(

            DogsContract.DogsEntry.TABLE_NAME,
            projection,
            null /* selection */,
            null /* selectionArgs */,
            null /* groupBy */,
            null /* having */,
            null /* orderBy */
        )
        val dogs: ArrayList<Dog> = ArrayList()
        with(cursor) {
            while (moveToNext()) {
                val name: String = getString(getColumnIndexOrThrow(DogsContract.DogsEntry.COLUMN_NAME))
                val description: String = getString(getColumnIndexOrThrow(DogsContract.DogsEntry.COLUMN_DESCRIPTION))
                val age: Int = getInt(getColumnIndexOrThrow(DogsContract.DogsEntry.COLUMN_AGE))
                val uri: String = getString(getColumnIndexOrThrow(DogsContract.DogsEntry.COLUMN_IMAGE))
                dogs.add(Dog(name, description, age, uri))
            }
        }
        cursor.close()
        return dogs
    }
}