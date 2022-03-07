package com.kinetic.recommendating

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ChatActivity : AppCompatActivity() {
    private lateinit var otherUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        //if (FirebaseDatabase.getInstance().)

        otherUser = intent.getStringExtra(OTHER_USER).toString()
    }

    fun sendMessage(view: View) {
        val input = findViewById<EditText>(R.id.editTextMessage)
        val message = input.text.toString()
        val auth = FirebaseAuth.getInstance()
        FirebaseDatabase.getInstance().reference.push().setValue(
            ChatMessage(message, auth.currentUser!!.uid, otherUser)
        )
        input.text.clear()
    }

    private fun setOneToOneChat(uid1: String, uid2: String): String {
        if (uid1 < uid2) {
            return uid1 + uid2
        }
        return uid2 + uid1
    }
}