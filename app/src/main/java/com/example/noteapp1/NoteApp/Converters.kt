package com.example.noteapp1.NoteApp

import android.os.Parcel
import android.os.Parcelable
import androidx.room.TypeConverter
import java.util.Date

class DateConverter() : Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let { Date(it) }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DateConverter> {
        override fun createFromParcel(parcel: Parcel): DateConverter {
            return DateConverter(parcel)
        }

        override fun newArray(size: Int): Array<DateConverter?> {
            return arrayOfNulls(size)
        }
    }
}