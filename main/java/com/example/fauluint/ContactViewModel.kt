package com.example.fauluint

import androidx.lifecycle.ViewModel
import com.google.firebase.database.FirebaseDatabase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {

    // State to track loading state
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    // Firebase Database reference
    private val database = FirebaseDatabase.getInstance()
    private val contactsRef = database.getReference("Contacts")

    /**
     * Saves a contact to Firebase Realtime Database.
     *
     * @param name The name of the contact.
     * @param email The email of the contact.
     * @param message The message from the contact.
     * @param onSuccess Callback invoked on successful save.
     * @param onFailure Callback invoked on failure with an error message.
     */
    fun saveContact(
        name: String,
        email: String,
        message: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        _isLoading.value = true

        // Create a unique key for the contact
        val contactId = contactsRef.push().key

        // Create a Contact object
        val contact = Contact(name, email, message)

        // Save the contact to Firebase
        viewModelScope.launch {
            try {
                if (contactId != null) {
                    contactsRef.child(contactId).setValue(contact)
                        .addOnSuccessListener {
                            _isLoading.value = false
                            onSuccess()
                        }
                        .addOnFailureListener { error ->
                            _isLoading.value = false
                            onFailure(error.message ?: "Unknown error")
                        }
                }
            } catch (e: Exception) {
                _isLoading.value = false
                onFailure(e.message ?: "Unknown error")
            }
        }
    }
}


