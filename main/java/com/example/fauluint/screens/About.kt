package com.example.fauluint.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fauluint.ContactViewModel
import com.example.fauluint.ui.theme.FauluIntTheme
import com.example.fauluint.R
import com.example.fauluint.ui.theme.GreenGN

//import com.google.firebase.Firebase
//import com.google.firebase.database.database


@Composable
fun About(scrollState: ScrollState,viewModel: ContactViewModel = viewModel(),){
//    val database = com.google.firebase.Firebase.database

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.primaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
                .background(Color.Blue),
        ) {
            Text(
                modifier = Modifier
                    .padding(25.dp)
                    .padding(start = 40.dp),
                textAlign = TextAlign.Center,
                text = stringResource(R.string.header_title),
                color = Color.Yellow,
                fontFamily = com.example.fauluint.ui.theme.Fonti,
                fontSize = 25.sp
            )
        }

        // Images and Content
        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Color.LightGray)
                .height(250.dp)
                .width(300.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(com.example.fauluint.R.drawable.faul),
                contentDescription = stringResource(R.string.header_title),
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Color.LightGray)
                .height(250.dp)
                .width(300.dp)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(com.example.fauluint.R.drawable.map),
                contentDescription = stringResource(R.string.location)
            )
        }

        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(RoundedCornerShape(25.dp))
                .background(Color.White)
                .height(250.dp)
                .width(300.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = stringResource(R.string.location))
                Spacer(modifier = Modifier.height(30.dp))
                Text(text = stringResource(R.string.courses_offered))
            }
        }

        Text(
            modifier = Modifier
                .padding(start = 40.dp),
            text = stringResource(R.string.have_a_question)
        )
        Spacer(modifier = Modifier.height(10.dp))

        // Contact Form
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.contact_us),
                modifier = Modifier.padding(bottom = 20.dp)
            )

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(stringResource(R.string.name_hint)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(stringResource(R.string.email_hint)) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = message,
                onValueChange = { message = it },
                label = {
                    Text(stringResource(R.string.message_hint))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .height(150.dp)
            )

            val context = LocalContext.current
            SubmitButton(
                isLoading = isLoading,
                onSubmit = {
                    if (name.isBlank() || email.isBlank() || message.isBlank()) {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@SubmitButton
                    }
                    isLoading = true
                    viewModel.saveContact(
                        name = name,
                        email = email,
                        message = message,
                        onSuccess = {
                            isLoading = false
                            Toast.makeText(context, "Contact saved successfully", Toast.LENGTH_SHORT).show()
                            name = ""
                            email = ""
                            message = ""
                        },
                        onFailure = { error ->
                            isLoading = false
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            )


        }


    }



}
@Composable
fun SubmitButton(
    isLoading: Boolean,
    onSubmit: () -> Unit
) {
    Button(
        onClick = onSubmit,
        modifier = Modifier.fillMaxWidth(),
        enabled = !isLoading
    ) {
        Text(
            text = if (isLoading) stringResource(R.string.submitting_button) else stringResource(R.string.submit_button)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun AboutPreview(){
    FauluIntTheme {
        About(scrollState = rememberScrollState())
    }

}






