package com.example.circuler.presentation.ui.upload.camerax

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.OptIn
import androidx.camera.camera2.interop.ExperimentalCamera2Interop
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FallbackStrategy
import androidx.camera.video.MediaStoreOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.LifecycleOwner
import com.google.common.util.concurrent.ListenableFuture
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine

internal class CameraXImpl : CameraX {

    private val _facing = MutableStateFlow(CameraSelector.LENS_FACING_BACK)
    private val _flash = MutableStateFlow(false)

    private lateinit var previewView: PreviewView
    private lateinit var preview: Preview
    private lateinit var cameraProvider: ListenableFuture<ProcessCameraProvider>
    private lateinit var provider: ProcessCameraProvider
    private lateinit var camera: Camera
    private lateinit var context: Context
    private lateinit var executor: ExecutorService
    private lateinit var mediaStoreOutput: MediaStoreOutputOptions

    private lateinit var imageCapture: ImageCapture
    private lateinit var videoCapture: VideoCapture<Recorder>

    // cameraX 초기화
    override fun initialize(context: Context) {
        previewView = PreviewView(context)
        preview = Preview.Builder().build().also { it.setSurfaceProvider(previewView.surfaceProvider) }
        cameraProvider = ProcessCameraProvider.getInstance(context)
        provider = cameraProvider.get()
        imageCapture = ImageCapture.Builder().build()
        executor = Executors.newSingleThreadExecutor()
        this.context = context

        // video 초기화 필수
        initializeVideo()
    }

    // video 초기화
    @OptIn(ExperimentalCamera2Interop::class)
    fun initializeVideo() {
        val qualitySelector = QualitySelector.fromOrderedList(
            listOf(Quality.UHD, Quality.FHD, Quality.HD, Quality.SD),
            FallbackStrategy.lowerQualityOrHigherThan(Quality.SD)
        )

        val recorder = Recorder.Builder()
            .setExecutor(executor).setQualitySelector(qualitySelector)
            .build()
        videoCapture = VideoCapture.withOutput(recorder)

        val path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/cameraX")
        if (!path.exists()) path.mkdirs()
        val name = "" + SimpleDateFormat(
            "yyyy-MM-dd-HH-mm-ss-SSS",
            Locale.KOREA
        ).format(System.currentTimeMillis()) + ".mp4"

        mediaStoreOutput = MediaStoreOutputOptions.Builder(
            context.contentResolver,
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        )
            .setContentValues(
                ContentValues().apply {
                    put(MediaStore.Video.Media.DISPLAY_NAME, name)
                }
            )
            .build()
    }

    // cameraX 시작
    override fun startCamera(
        lifecycleOwner: LifecycleOwner
    ) {
        unBindCamera()

        val cameraSelector = CameraSelector.Builder().requireLensFacing(_facing.value).build()

        cameraProvider.addListener({
            CoroutineScope(Dispatchers.Main).launch {
                camera = provider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture,
                    videoCapture
                )
            }
        }, executor)
    }

    // 사진 촬영
    @kotlin.OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun takePicture(): Uri? {
        val path =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/cameraX")

        if (!path.exists()) path.mkdirs()

        val photoFile = File(
            path,
            SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss-SSS",
                Locale.KOREA
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputFileOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        return suspendCancellableCoroutine { continuation ->
            imageCapture.takePicture(
                outputFileOptions,
                ContextCompat.getMainExecutor(context),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(error: ImageCaptureException) {
                        continuation.resume(null) {} // 실패 시 null 반환
                    }

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        // File -> Uri 변환
                        val uri = FileProvider.getUriForFile(
                            context,
                            "${context.packageName}.provider",
                            photoFile
                        )
                        continuation.resume(uri) {}
                    }
                }
            )
        }
    }

    // 카메라 방향 전환
    override fun flipCameraFacing() {
        if (_facing.value == CameraSelector.LENS_FACING_BACK) {
            _flash.value = false
            _facing.value = CameraSelector.LENS_FACING_FRONT
        } else {
            _facing.value = CameraSelector.LENS_FACING_BACK
        }
    }

    // 카메라 닫기
    override fun unBindCamera() {
        provider.unbindAll()
    }

    // 카메라 화면
    override fun getPreviewView(): PreviewView = previewView

    // 카메라 방향 가져오기
    override fun getFacingState(): StateFlow<Int> = _facing.asStateFlow()
}
