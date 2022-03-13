package com.eerick.dogos.database

import android.provider.BaseColumns

/**
 * DogsContract
 *
 * Basically this class contains the schema of our database
 */
object DogsContract {
    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${DogsEntry.TABLE_NAME} " +
                "(${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "${DogsEntry.COLUMN_NAME} TEXT, " +
                "${DogsEntry.COLUMN_DESCRIPTION} TEXT, " +
                "${DogsEntry.COLUMN_AGE} INTEGER, " +
                "${DogsEntry.COLUMN_IMAGE} TEXT)"

    object DogsEntry : BaseColumns {
        const val TABLE_NAME = "dogs"
        const val COLUMN_NAME = "name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_AGE = "age"
        const val COLUMN_IMAGE = "image"
    }
}