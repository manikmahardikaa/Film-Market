package com.example.myapk4.ui.screen.profile

import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapk4.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),

    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "About",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        )
        Avatar(
            avatarUrl = "",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )
        UserDetails(
            name = "Ngurah Manik Mahardika",
            email = "manik.mahardika@gmail.com",
            bio = "Ngurah Manik Mahardika, born in Denpasar in May 2023, is an individual who is passionate and dedicated to the world of mobile application development. With his desire to continue learning and developing, Ngurah Manik Mahardika has shown a deep interest in everything related to technology development, especially mobile applications.\n" +
                    "\n" +
                    "Since the beginning of his life, Ngurah Manik Mahardika has shown great interest in the world of technology and innovation. His birth in Denpasar, Bali, created a cultural foundation and environment rich in inspiration, which later shaped his views and interests in exploring the world of application development.",
            modifier = Modifier.fillMaxWidth()
        )

        ProfileInfoItem("Location", "Bali, Indonesia")
        ProfileInfoItem("Social Media", "manikmahardikaa",)

    }
}

@Composable
fun Avatar(
    avatarUrl: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = R.drawable.manik),
        contentDescription = "User Avatar",
        modifier = modifier
    )
}

@Composable
fun UserDetails(
    name: String,
    email: String,
    bio: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = email, color = androidx.compose.ui.graphics.Color.Red)
        Text(text = bio, textAlign = TextAlign.Justify, style = MaterialTheme.typography.bodyMedium,)
    }
}

@Composable
fun ProfileInfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value, color = androidx.compose.ui.graphics.Color.Red)
    }
}

