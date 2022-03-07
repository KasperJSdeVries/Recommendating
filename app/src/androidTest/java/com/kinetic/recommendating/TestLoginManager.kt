package com.kinetic.recommendating

import android.os.SystemClock.sleep
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

object TestLoginManager {
    const val testEmail = "test@testing.test"
    const val testPassword = "oahteuhdu43uhdr23dhtnueoa"
    private var auth = FirebaseAuth.getInstance()

    fun createTestUser() {
        if (auth.currentUser != null) {
            auth.signOut()
        }
        var completed = false
        auth.createUserWithEmailAndPassword(testEmail, testPassword)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    if (task.exception is FirebaseAuthUserCollisionException) {
                        auth.signInWithEmailAndPassword(testEmail, testPassword)
                            .addOnCompleteListener {
                                completed = true
                            }
                    }
                } else {
                    completed = true
                }
            }

        while (!completed) {
            sleep(1)
        }
    }

    fun signOut() {
        auth.signOut()
    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null;
    }

    fun deleteTestUser() {

        var user = auth.currentUser

        if (user == null) {
            var completed = false
            auth.signInWithEmailAndPassword(testEmail, testPassword)
                .addOnCompleteListener {
                    completed = true
                }

            while (!completed) {
                sleep(1)
            }
        }

        if (user != null) {
            var completed = false
            user.delete().addOnCompleteListener {
                completed = true
            }
            while (!completed) {
                sleep(1)
            }
        }
    }
}