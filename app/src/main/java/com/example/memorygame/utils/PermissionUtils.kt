package com.example.memorygame.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

fun isPermissionGranted(context: Context,permission:String):Boolean{
    return ContextCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED
}

fun requestPermission(activity: Activity?, Permission:String, requestCode:Int){
    ActivityCompat.requestPermissions(activity!!, arrayOf(Permission),requestCode)
}
