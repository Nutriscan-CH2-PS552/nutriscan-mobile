

package com.example.nutriscans.screen

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nutriscans.cameraguide.CameraPreview
import com.example.nutriscans.cameraguide.ScanViewModel
import com.example.nutriscans.ui.theme.NutriscansTheme


class ScanActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!hasRequiredPermissions()) {
            ActivityCompat.requestPermissions(
                this, CAMERAX_PERMISSIONS, 0
            )
        }
        setContent {
            NutriscansTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val controller = remember {
                        LifecycleCameraController(applicationContext).apply {
                            setEnabledUseCases(
                                CameraController.IMAGE_CAPTURE
                            )
                        }
                    }
                    val viewModel = viewModel<ScanViewModel>()
                    val bitmaps by viewModel.bitmaps.collectAsState()
                    var capturedBitmap by remember { mutableStateOf<Bitmap?>(null) }

                    Box(

                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CameraPreview(
                            controller = controller,
                            modifier = Modifier
                                .fillMaxSize()
                        )

                        IconButton(
                            modifier = Modifier
                                .align(Alignment.BottomCenter),
                            onClick = {
                                takePhoto(
                                    controller = controller,
                                    onPhotoTaken = { bitmap ->
                                        capturedBitmap = bitmap
                                        viewModel.onTakePhoto(bitmap)
                                    }
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.PhotoCamera,
                                contentDescription = "Take photo"
                            )
                        }
                    }

                    capturedBitmap?.let { Scanned(bitmap = it) }

                }
            }

        }
    }

    private fun takePhoto(
        controller: LifecycleCameraController,
        onPhotoTaken: (Bitmap) -> Unit
    ) {
        controller.takePicture(
            ContextCompat.getMainExecutor(applicationContext),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)

                    val matrix = Matrix().apply {
                        postRotate(image.imageInfo.rotationDegrees.toFloat())
                    }
                    val rotatedBitmap = Bitmap.createBitmap(
                        image.toBitmap(),
                        0,
                        0,
                        image.width,
                        image.height,
                        matrix,
                        true
                    )

                    onPhotoTaken(rotatedBitmap)
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e("Camera", "Couldn't take photo: ", exception)
                }
            }
        )
    }

    private fun hasRequiredPermissions(): Boolean {
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        private val CAMERAX_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
        )
    }

    @Composable
    fun Scanned(bitmap: Bitmap) {
        val imageBitmap = remember { bitmap.asImageBitmap() }

        // State to control the visibility of the card
        var isCardVisible by remember { mutableStateOf(true) }

        // Compose UI
        Column {
            Spacer(modifier = Modifier.height(20.dp))

            // Show the Card only if isCardVisible is true
            if (isCardVisible) {
                Card(

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(8.dp)
                        .clickable {
                            // Hide the card when clicked
                            isCardVisible = false
                            recreate()
                        },
                    content = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                bitmap = imageBitmap,
                                contentDescription = ""
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(text = "Scan")
                        }
                    }
                )
            }
        }
    }

}