package com.example.simple_notes_jetpack_room.Functions

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import kotlin.random.Random

object my_Utils {
    fun mToast(context: Context, s: String) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show()
    }

    fun randColour(): Int {
        val rnd = Random.Default //kotlin.random
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
        return color;
    }
}