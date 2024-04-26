package com.example.noteapp1.NoteApp

import android.os.Parcel
import android.os.Parcelable


data class ColorNotification(val color: Int) : Parcelable {
    constructor(parcel: Parcel) : this(parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ColorNotification> {
        override fun createFromParcel(parcel: Parcel): ColorNotification {
            return ColorNotification(parcel)
        }

        override fun newArray(size: Int): Array<ColorNotification?> {
            return arrayOfNulls(size)
        }
    }
}
