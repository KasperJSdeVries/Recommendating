package com.kinetic.recommendating

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth

const val OTHER_USER = "com.kinetic.recommendating.OTHER_USER"

fun getScreenWidth(): Int {
    return Resources.getSystem().displayMetrics.widthPixels
}

fun getScreenHeight(): Int {
    return Resources.getSystem().displayMetrics.heightPixels
}

class MainActivity : Activity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.profileImageView).layoutParams.height = getScreenHeight()

        auth = FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser == null) {
            val login = Intent(this, LoginActivity::class.java)
            startActivity(login)
        }
    }

    fun onClickMessagesButton(view: View) {
        val intent = Intent(this, PickChatActivity::class.java)
        startActivity(intent)
    }

    fun onClickProfileButton(view: View) {
        val intent = Intent(this, AccountInfoActivity::class.java)
        startActivity(intent)
    }
}