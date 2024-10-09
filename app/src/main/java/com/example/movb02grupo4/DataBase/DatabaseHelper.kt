package com.example.movb02grupo4.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mydatabase.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_USERS = "users"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_USERS (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "lastname TEXT," +
                "phone TEXT," +
                "email TEXT UNIQUE," +
                "password TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(name: String, lastname: String, phone: String, email: String, password: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put("name", name)
            put("lastname", lastname)
            put("phone", phone)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }
        val result = db.insert(TABLE_USERS, null, contentValues)
        db.close()
        return result
    }

    fun checkUser(email: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ?", arrayOf(email))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }

    fun checkUserCredentials(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?", arrayOf(email, password))
        val isValid = cursor.count > 0
        cursor.close()
        db.close()
        return isValid
    }

    fun obtenerDatosUsuarioPorEmail(email: String): Map<String, String>? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS WHERE $COLUMN_EMAIL = ?", arrayOf(email))

        if (cursor.moveToFirst()) {
            // Obtener los datos del usuario de la base de datos
            val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            val lastname = cursor.getString(cursor.getColumnIndexOrThrow("lastname"))
            val phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))

            val userInfo = mapOf(
                "name" to name,
                "lastname" to lastname,
                "phone" to phone,
                "email" to email
            )

            cursor.close()
            db.close()
            return userInfo
        }
        cursor.close()
        db.close()
        return null
    }
}