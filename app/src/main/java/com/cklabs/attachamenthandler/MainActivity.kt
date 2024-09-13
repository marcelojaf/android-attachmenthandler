package com.cklabs.attachamenthandler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cklabs.attachamenthandler.ui.theme.AttachamentHandlerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AttachamentHandlerTheme {
                AttachmentHandlerApp()
            }
        }
    }
}

@Composable
fun AttachmentHandlerApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // App name at the top
            Text(
                text = "Attachment Handler",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )

            // Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { /* TODO: Implement camera functionality */ }) {
                    Text("Camera")
                }
                Button(onClick = { /* TODO: Implement gallery functionality */ }) {
                    Text("Gallery")
                }
                Button(onClick = { /* TODO: Implement file functionality */ }) {
                    Text("File")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Placeholder for attachment list
            Text("Attachment List Placeholder")
            // Here you would typically use a LazyColumn for the list of attachments
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AttachmentHandlerAppPreview() {
    AttachamentHandlerTheme {
        AttachmentHandlerApp()
    }
}