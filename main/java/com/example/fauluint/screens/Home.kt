package com.example.fauluint.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.fauluint.R
import com.example.fauluint.ui.theme.Fonti


@Composable
fun Home() {
    Box(modifier= Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.index),
            contentDescription = "background image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize())
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = buildAnnotatedString {
            append("Welcome to")
            withStyle(
                SpanStyle(
                    color = Color.Yellow,
                    fontFamily = Fonti,

                    fontSize = 45.sp
                )
            ){
                append("Faulu")
            }


        },
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold)


    }


}
@Preview(showBackground = true)
@Composable
fun HomePreview(){
    Home()

}









