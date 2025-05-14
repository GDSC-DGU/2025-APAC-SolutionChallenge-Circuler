package com.example.circuler.presentation.ui.upload.camerax

import android.content.Context
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.StateFlow

interface CameraX {
    fun initialize(context: Context) // cameraX 초기화
    fun startCamera(lifecycleOwner: LifecycleOwner) // cameraX 시작
    fun takePicture() // 사진 촬영
    fun flipCameraFacing() // 카메라 방향 전환
    fun unBindCamera() // 카메라 닫기
    fun getPreviewView(): PreviewView // 카메라 화면
    fun getFacingState(): StateFlow<Int> // 카메라 방향 가져오기
}
