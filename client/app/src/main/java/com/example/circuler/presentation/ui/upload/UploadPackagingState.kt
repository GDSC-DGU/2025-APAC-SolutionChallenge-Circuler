package com.example.circuler.presentation.ui.upload

import android.net.Uri
import com.example.circuler.domain.entity.PackageImageEntity
import com.example.circuler.presentation.core.util.ImageUiState

data class UploadPackagingState(
    val uiState: ImageUiState<PackageImageEntity> = ImageUiState.PermissionNotGranted,
    val selectedImageUri: Uri? = null,
)
