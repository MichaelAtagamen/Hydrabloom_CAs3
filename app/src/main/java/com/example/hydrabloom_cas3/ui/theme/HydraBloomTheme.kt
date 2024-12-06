package com.example.hydrabloom_cas3.ui.theme



import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun HydraBloomTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = MaterialTheme.colorScheme.primary,
            secondary = MaterialTheme.colorScheme.secondary
        ),
        typography = Typography(),
        content = content
    )
}
