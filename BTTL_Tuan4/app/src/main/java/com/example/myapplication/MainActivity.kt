package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home // Import Home icon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Sealed class for screen navigation
sealed class Screen {
    object Welcome : Screen()
    object ComponentsList : Screen()
    data class ComponentDetail(val componentName: String) : Screen()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Welcome) }

    when (val screen = currentScreen) {
        is Screen.Welcome -> WelcomeScreen(
            onReadyClicked = { currentScreen = Screen.ComponentsList }
        )
        is Screen.ComponentsList -> ComponentsListScreen(
            onComponentClick = { componentName ->
                currentScreen = Screen.ComponentDetail(componentName)
            },
            onNavigateBackToWelcome = { currentScreen = Screen.Welcome }
        )
        is Screen.ComponentDetail -> ComponentDetailScreen(
            componentName = screen.componentName,
            onBackToList = { currentScreen = Screen.ComponentsList }, // Renamed for clarity
            onNavigateToWelcome = { currentScreen = Screen.Welcome } // New navigation action
        )
    }
}

@Composable
fun WelcomeScreen(onReadyClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Image(
            painter = painterResource(id = R.drawable.logo), // Ensure you have R.drawable.logo
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(250.dp)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Jetpack Compose",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Jetpack Compose is a modern UI toolkit for\n" +
                        "building native Android applications using\na declarative programming approach.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 12.dp)
            )
        }

        Button(
            onClick = onReadyClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text(text = "I'm ready", color = Color.White)
        }
    }
}

data class ComponentItemData(val name: String, val description: String)
data class ComponentSectionData(val title: String, val items: List<ComponentItemData>)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsListScreen(
    onComponentClick: (String) -> Unit,
    onNavigateBackToWelcome: () -> Unit
) {
    val sections = remember {
        List(200) { sectionIndex ->
            ComponentSectionData(
                title = "Section ${sectionIndex + 1}",
                items = listOf(
                    ComponentItemData("Component A in Section ${sectionIndex + 1}", "Description A"),
                    ComponentItemData("Component B in Section ${sectionIndex + 1}", "Description B")
                )
            )
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "UI Components List",
                        color = Color(0xFF2196F3),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBackToWelcome) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack, // Using ArrowBack, could be Home too
                            contentDescription = "Back to Welcome",
                            tint = Color(0xFF2196F3)
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = Color(0xFF2196F3)
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(sections) { section ->
                ComponentSection(
                    title = section.title,
                    items = section.items,
                    onItemClick = onComponentClick
                )
            }
        }
    }
}

@Composable
fun ComponentSection(
    title: String,
    items: List<ComponentItemData>,
    onItemClick: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        items.forEach { item ->
            ComponentInfoCard(item, onClick = { onItemClick(item.name) })
        }
    }
}

@Composable
fun ComponentInfoCard(item: ComponentItemData, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = Color(0xFFE0F7FA),
        tonalElevation = 2.dp,
        shadowElevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentDetailScreen(
    componentName: String,
    onBackToList: () -> Unit, // Renamed from onBack for clarity
    onNavigateToWelcome: () -> Unit // New lambda
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = componentName, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBackToList) { // This is for going back to the list
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to components list"
                        )
                    }
                },
                actions = { // Actions are typically on the right side of TopAppBar
                    IconButton(onClick = onNavigateToWelcome) {
                        Icon(
                            imageVector = Icons.Filled.Home, // Home icon for Welcome screen
                            contentDescription = "Go to Welcome Screen"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer // Color for action icons
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Details for:",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = componentName,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onBackToList) { // This button still goes back to the list
                Text("Go Back to List")
            }
            Spacer(modifier = Modifier.height(16.dp)) // Spacer between buttons
            Button(
                onClick = onNavigateToWelcome,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary) // Different color for distinction
            ) {
                Text("Go to Welcome Screen")
            }
        }
    }
}

// --- Previews ---

@Preview(showBackground = true, name = "Welcome Screen Preview")
@Composable
fun PreviewWelcomeScreen() {
    MaterialTheme {
        WelcomeScreen(onReadyClicked = {})
    }
}

@Preview(showBackground = true, name = "Components List Preview", showSystemUi = true)
@Composable
fun PreviewComponentsListScreen() {
    MaterialTheme {
        ComponentsListScreen(onComponentClick = {}, onNavigateBackToWelcome = {})
    }
}

@Preview(showBackground = true, name = "Component Detail Preview", showSystemUi = true)
@Composable
fun PreviewComponentDetailScreen() {
    MaterialTheme {
        ComponentDetailScreen(
            componentName = "Sample Component XYZ",
            onBackToList = {},
            onNavigateToWelcome = {} // Provide dummy lambda for the preview
        )
    }
}