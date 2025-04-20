package com.example.circuler.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.circuler.R

val PretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
val PretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium))
val PretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))

@Immutable
data class CirculerTypography(
    //head
    val head1: TextStyle,
    val head2: TextStyle,
)

val defaultCirculerTypography = CirculerTypography(
    //head
    head1 = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 32.sp,
        lineHeight = 38.sp
    ),
    head2 = TextStyle(
        fontFamily = PretendardMedium,
        fontSize = 28.sp,
        lineHeight = 34.sp
    )
)

val LocalTypo = staticCompositionLocalOf { defaultCirculerTypography }