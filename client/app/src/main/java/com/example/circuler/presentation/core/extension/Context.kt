package com.example.circuler.presentation.core.extension

import android.content.Context
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT

fun Context.showToast(message: String) {
    Toast.makeText(this, message, LENGTH_SHORT).show()
}
