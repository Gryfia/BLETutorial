package com.example.bletutorial.util

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.bletutorial.data.model.BabyModel
import com.example.bletutorial.data.model.WeightModel

class SQLiteHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_BABY_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_BABY_NAME = "baby.db"
        private const val TABLE_BABY_NAME = "tbl_baby"
        private const val ID_BABY = "id"
        private const val NIK_BABY = "NIK"
        private const val NAMA = "nama"

        private const val TABLE_WEIGHT_NAME = "tbl_weight"
        private const val ID_WEIGHT = "id"
        private const val NIK_WEIGHT = "NIK"
        private const val UMUR = "umur"
        private const val BERAT = "berat"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblBaby = ("CREATE TABLE " + TABLE_BABY_NAME + "("
                + ID_BABY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NIK_BABY + " TEXT,"
                + NAMA + " TEXT" + ")")
        val createTblWeight = ("CREATE TABLE " + TABLE_WEIGHT_NAME + "("
                + ID_WEIGHT + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NIK_WEIGHT + " TEXT,"
                + UMUR + " INTEGER,"
                + BERAT + " FLOAT" + ")")

        db?.execSQL(createTblBaby)
        db?.execSQL(createTblWeight)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_BABY_NAME")
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_WEIGHT_NAME")
        onCreate(db)
    }

    fun insertBaby(baby: BabyModel): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NIK_BABY, baby.NIK)
        values.put(NAMA, baby.nama)
        val _success = db.insert(TABLE_BABY_NAME, null, values)
        db.close()
        return _success
    }

    @SuppressLint("Range")
    fun getAllBaby(): ArrayList<BabyModel>{
        val babyList: ArrayList<BabyModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_BABY_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()){
            do {
                val baby = BabyModel()
                baby.id = cursor.getInt(cursor.getColumnIndex(ID_BABY))
                baby.NIK = cursor.getString(cursor.getColumnIndex(NIK_BABY))
                baby.nama = cursor.getString(cursor.getColumnIndex(NAMA))
                babyList.add(baby)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return babyList
    }


    @SuppressLint("Range")
    fun getWeightByNIK(NIK: String): ArrayList<WeightModel>{
        val weightList: ArrayList<WeightModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_WEIGHT_NAME WHERE $NIK_WEIGHT = '$NIK'"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()){
            do {
                val weight = WeightModel()
                weight.id = cursor.getInt(cursor.getColumnIndex(ID_WEIGHT))
                weight.NIK = cursor.getString(cursor.getColumnIndex(NIK_WEIGHT))
                weight.umur = cursor.getInt(cursor.getColumnIndex(UMUR))
                weight.berat = cursor.getFloat(cursor.getColumnIndex(BERAT))
                weightList.add(weight)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return weightList
    }

    fun updateBaby(baby: BabyModel): Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID_BABY, baby.id)
        values.put(NIK_BABY, baby.NIK)
        values.put(NAMA, baby.nama)
        return db.update(TABLE_BABY_NAME, values, "$ID_BABY=?", arrayOf(baby.id.toString()))
    }

    fun deleteBaby(NIK: String): Int{
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(NIK_BABY, NIK)

        val _success = db.delete(TABLE_BABY_NAME, "NIK="+NIK, null)
        db.close()
        return _success
    }

    fun insertWeight(weight: WeightModel): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NIK_WEIGHT, weight.NIK)
        values.put(UMUR, weight.umur)
        values.put(BERAT, weight.berat)
        val _success = db.insert(TABLE_WEIGHT_NAME, null, values)
        db.close()
        return _success
    }

    @SuppressLint("Range")
    fun getAllWeight(): ArrayList<WeightModel>{
        val weightList: ArrayList<WeightModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_WEIGHT_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()){
            do {
                val weight = WeightModel()
                weight.id = cursor.getInt(cursor.getColumnIndex(ID_WEIGHT))
                weight.NIK = cursor.getString(cursor.getColumnIndex(NIK_WEIGHT))
                weight.umur = cursor.getInt(cursor.getColumnIndex(UMUR))
                weight.berat = cursor.getFloat(cursor.getColumnIndex(BERAT))
                weightList.add(weight)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return weightList
    }

    fun deleteWeight(NIK: String): Int{
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(NIK_WEIGHT, NIK)

        val _success = db.delete(TABLE_WEIGHT_NAME, "NIK="+NIK, null)
        db.close()
        return _success
    }

    fun updateWeight(weight: WeightModel): Int{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(ID_WEIGHT, weight.id)
        values.put(NIK_WEIGHT, weight.NIK)
        values.put(UMUR, weight.umur)
        values.put(BERAT, weight.berat)
        return db.update(TABLE_WEIGHT_NAME, values, "$ID_WEIGHT=?", arrayOf(weight.id.toString()))
    }

}