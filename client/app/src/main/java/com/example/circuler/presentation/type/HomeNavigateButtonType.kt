package com.example.circuler.presentation.type

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.circuler.R

enum class HomeNavigateButtonType(
    @DrawableRes val buttonIcon: Int,
    @StringRes val buttonTitle: Int
) {
    REQUEST(
        buttonIcon = R.drawable.ic_request,
        buttonTitle = R.string.requested_packages
    ),

    DELIVERY(
        buttonIcon = R.drawable.ic_box,
        buttonTitle = R.string.ready_to_go_packages
    )
}
