package com.example.babi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.babi.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BabiTheme {
                AuthorProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorProfileScreen() {
    Scaffold(
        containerColor = BabiBackground,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Autor",
                        fontSize = 28.sp,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        color = BabiTextDark
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Outlined.NotificationsActive,
                            contentDescription = "Notificações",
                            tint = BabiTextDark
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = BabiBackground)
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.AutoMirrored.Outlined.Article, contentDescription = "Feed") },
                    label = { Text("Feed") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Outlined.ChatBubbleOutline, contentDescription = "Chat") },
                    label = { Text("Chat") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Default.Add, contentDescription = "Create") },
                    label = { Text("Create") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { },
                    icon = { Icon(Icons.Outlined.Search, contentDescription = "Search") },
                    label = { Text("Search") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { },
                    icon = { Icon(Icons.Outlined.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = BabiOrange,
                        selectedTextColor = BabiOrange,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                
                // Profile Image Placeholder
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                ) {
                    Icon(
                        Icons.Outlined.Person,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize().padding(20.dp),
                        tint = Color.White
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Helena Andrade",
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    color = BabiTextDark
                )
                
                Text(
                    text = "@helena_escreve",
                    fontSize = 14.sp,
                    color = BabiOrange
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Text(
                    text = "\"Escrevo para desenhar pontes secretas no silêncio do mundo cotidiano.\"",
                    fontSize = 14.sp,
                    fontStyle = FontStyle.Italic,
                    color = BabiTextGray,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 48.dp)
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    StatItem(count = "2.4k", label = "Leitores")
                    Spacer(modifier = Modifier.width(48.dp))
                    StatItem(count = "340", label = "Lendo")
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Custom Tabs
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TabItem("Publicações", active = true, modifier = Modifier.weight(1f))
                    TabItem("Favoritos", active = false, modifier = Modifier.weight(1f))
                }
                
                Spacer(modifier = Modifier.height(24.dp))
            }
            
            items(publications) { pub ->
                PublicationCard(pub)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun StatItem(count: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp, color = BabiTextDark)
        Text(text = label, fontSize = 12.sp, color = BabiTextGray)
    }
}

@Composable
fun TabItem(title: String, active: Boolean, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = if (active) FontWeight.Bold else FontWeight.Normal,
            color = if (active) BabiOrange else BabiTextGray,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(if (active) 3.dp else 1.dp)
                .background(if (active) BabiOrange else Color.LightGray.copy(alpha = 0.5f))
        )
    }
}

@Composable
fun PublicationCard(pub: Publication) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BabiCardBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pub.category,
                    fontSize = 10.sp,
                    color = BabiTextGray,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = pub.time,
                    fontSize = 10.sp,
                    color = BabiTextGray
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = pub.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = BabiTextDark,
                fontFamily = FontFamily.Serif
            )
        }
    }
}

data class Publication(val category: String, val title: String, val time: String)

val publications = listOf(
    Publication("POESIA", "Sussurros do Crepúsculo", "Há 2 dias"),
    Publication("CRÔNICA", "A Biblioteca Invisível", "Há 1 semana")
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BabiTheme {
        AuthorProfileScreen()
    }
}
