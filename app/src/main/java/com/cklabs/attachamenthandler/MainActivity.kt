package com.cklabs.attachamenthandler

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
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

data class Attachment(val uri: Uri, val name: String)

@Composable
fun AttachmentHandlerApp() {
    var attachments by remember { mutableStateOf(listOf<Attachment>()) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val name = uri.lastPathSegment ?: "Unnamed"
            attachments = attachments + Attachment(uri, name)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Attachment Handler",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { /* TODO: Implement camera functionality */ }) {
                    Text("Camera")
                }
                Button(onClick = { galleryLauncher.launch("image/*") }) {
                    Text("Gallery")
                }
                Button(onClick = { /* TODO: Implement file functionality */ }) {
                    Text("File")
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(attachments) { attachment ->
                    AttachmentItem(attachment)
                }
            }
        }
    }
}

@Composable
fun AttachmentItem(attachment: Attachment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = attachment.uri)
                    .build()
            ),
            contentDescription = "Attachment thumbnail",
            modifier = Modifier.size(60.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = attachment.name)
    }
}

@Preview(showBackground = true)
@Composable
fun AttachmentHandlerAppPreview() {
    AttachamentHandlerTheme {
        AttachmentHandlerApp()
    }
}