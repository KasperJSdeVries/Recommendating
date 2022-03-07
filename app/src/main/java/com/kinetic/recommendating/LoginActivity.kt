package com.kinetic.recommendating

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var emailWarning: TextView
    private lateinit var passwordWarning: TextView

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun resetWarnings() {
        emailWarning.visibility = View.INVISIBLE
        passwordWarning.visibility = View.INVISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextEmail = findViewById(R.id.loginEmailField)
        editTextPassword = findViewById(R.id.loginPasswordField)
        emailWarning = findViewById(R.id.loginInvalidEmailWarning)
        passwordWarning = findViewById(R.id.loginInvalidPasswordWarning)

        auth = FirebaseAuth.getInstance()
    }

    override fun onBackPressed() {
        this.finishAffinity()
    }

    fun onSignIn(view: View) {
        resetWarnings()
        var valid = true

        val email = editTextEmail.text.toString()
        if (!isEmailValid(email)) {
            emailWarning.visibility = View.VISIBLE
            valid = false
        }

        val password = editTextPassword.text.toString()
        if (password == "") {
            passwordWarning.visibility = View.VISIBLE
            valid = false
        }

        if (valid) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithEmail:success")
                    finish()
                } else {
                    Log.d(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onSignUp(view: View) {
        resetWarnings()
        var valid = true

        val email = editTextEmail.text.toString()
        if (!isEmailValid(email)) {
            emailWarning.visibility = View.VISIBLE
            valid = false
        }

        val password = editTextPassword.text.toString()
        if (password.length < 6) {
            passwordWarning.visibility = View.VISIBLE
            valid = false
        }

        if (valid) {
            val popupWindow = createPopupWindow(email, password)
            popupWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL or  Gravity.TOP, 0, 300)
            popupWindow.setOnDismissListener { finish() }
        }
    }

    private fun createPopupWindow(email: String, password: String): PopupWindow {
        val inflater: LayoutInflater =
            getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_password, null)
        val submitButton = popupView.findViewById<Button>(R.id.button4)
        val passwordField = popupView.findViewById<EditText>(R.id.editTextTextPassword2)
        submitButton.setOnClickListener {
            if (passwordField.text.toString() == password) {
                createUser(email, password)
            }
        }
        val width = LinearLayout.LayoutParams.WRAP_CONTENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = true
        return PopupWindow(popupView, width, height, focusable)
    }

    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                Log.d(TAG, "createUserWithEmail:success")
                finish()
            } else {
                Log.d(TAG, "createUserWithEmail:failure")
                Toast.makeText(
                    baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}