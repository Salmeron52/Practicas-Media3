package com.buenhijogames.media3practica01

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.media3.exoplayer.ExoPlayer
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.media3.common.MediaItem

@Composable
fun BeepScreen() {
    val context = LocalContext.current
    // Creamos un ExoPlayer y lo liberamos cuando el Composable se destruye
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Button(onClick = {
                // Reproducimos el sonido
                sonar(context, exoPlayer, R.raw.sonido1)
            }) {
                Text(text = "Sonido1")
            }

                Button(onClick = {
                    // Reproducimos el sonido
                    sonar(context, exoPlayer, R.raw.sonido2)
                }) {
                    Text(text = "Sonido2")
                }

        }
    }
}

private fun sonar(
    context: Context,
    exoPlayer: ExoPlayer,
    sonido: Int
) {
    val uri = Uri.parse("android.resource://" + context.packageName + "/" + R.raw.sonido1)
    val mediaItem = MediaItem.fromUri(uri)
    exoPlayer.setMediaItem(mediaItem)
    exoPlayer.prepare()
    exoPlayer.play()
}