package com.rto.vehicle.numberplate.finder.information.VehicleInfo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rto.vehicle.numberplate.finder.information.VehicleInfo.datamodels.VehicleDetailsDatabaseModel;


public class VehicleDetailsTableAdapter {
    private final TradetuDatabaseHelper databaseHelper;

    public VehicleDetailsTableAdapter(Context context) {
        this.databaseHelper = TradetuDatabaseHelper.getInstance(context);
    }

    public long saveVehicleDetails(String str, String str2) {
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("reg_no", str);
        contentValues.put("show_data", str2);
        long insert = writableDatabase.insert("VehicleDetailsHistory", null, contentValues);
        writableDatabase.close();
        return insert;
    }

    public VehicleDetailsDatabaseModel readVehicleDetails(String str) {
        VehicleDetailsDatabaseModel vehicleDetailsDatabaseModel = new VehicleDetailsDatabaseModel();
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        Cursor rawQuery = writableDatabase.rawQuery("SELECT * FROM VehicleDetailsHistory WHERE reg_no = '" + str + "' ORDER BY id DESC LIMIT 1", null);
        while (rawQuery.moveToNext()) {
            try {
                vehicleDetailsDatabaseModel.setId(rawQuery.getInt(rawQuery.getColumnIndex("id")));
                vehicleDetailsDatabaseModel.setRegistrationNo(rawQuery.getString(rawQuery.getColumnIndex("reg_no")));
                vehicleDetailsDatabaseModel.setData(rawQuery.getString(rawQuery.getColumnIndex("show_data")));
                writableDatabase.close();
                rawQuery.close();
            } finally {
                writableDatabase.close();
                rawQuery.close();
            }
        }
        return vehicleDetailsDatabaseModel;
    }

    public void deleteAllHistory(boolean z) {
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        if (writableDatabase != null) {
            writableDatabase.delete("VehicleDetailsHistory", null, null);
            if (z) {
                writableDatabase.close();
            }
        }
    }

    public void deleteHistoryById(String str) {
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        writableDatabase.delete("VehicleDetailsHistory", "id=?", new String[]{str});
        writableDatabase.close();
    }

    public void deleteHistoryByArgs(String str) {
        SQLiteDatabase writableDatabase = this.databaseHelper.getWritableDatabase();
        writableDatabase.execSQL("DELETE FROM VehicleDetailsHistory WHERE reg_no = '" + str + "'");
        writableDatabase.close();
    }
}
