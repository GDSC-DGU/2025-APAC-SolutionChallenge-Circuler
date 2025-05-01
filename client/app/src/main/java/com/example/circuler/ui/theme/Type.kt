package com.example.circuler.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.circuler.R

val PretendardBold = FontFamily(Font(R.font.pretendard_bold, FontWeight.Bold))
val PretendardSemiBold = FontFamily(Font(R.font.pretendard_semibold, FontWeight.SemiBold))
val PretendardMedium = FontFamily(Font(R.font.pretendard_medium, FontWeight.Medium))
val PretendardRegular = FontFamily(Font(R.font.pretendard_regular, FontWeight.Normal))

@Immutable
data class CirculerTypography(
    val heading1B20: TextStyle,
    val heading2Sb20: TextStyle,
    val heading3M20: TextStyle,
    val heading4B18: TextStyle,
    val heading5Sb18: TextStyle,
    val title1R16: TextStyle,
    val title2Sb16: TextStyle,
    val title3M16: TextStyle,
    val body1R14: TextStyle,
    val body2Sb14: TextStyle,
    val body3M14: TextStyle,
    val body4R12: TextStyle,
    val body5Sb12: TextStyle,
    val body6M12: TextStyle,
    val caption1R10: TextStyle,
    val caption2Sb10: TextStyle,
    val caption3M10: TextStyle
)

val defaultCirculerTypography = CirculerTypography(
    heading1B20 = TextStyle(
        fontFamily = PretendardBold,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.005).em
    ),
    heading2Sb20 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.005).em
    ),
    heading3M20 = TextStyle(
        fontFamily = PretendardMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.005).em
    ),
    heading4B18 = TextStyle(
        fontFamily = PretendardBold,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.005).em
    ),
    heading5Sb18 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = (-0.005).em
    ),
    title1R16 = TextStyle(
        fontFamily = PretendardRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.01).em
    ),
    title2Sb16 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.01).em
    ),
    title3M16 = TextStyle(
        fontFamily = PretendardMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.01).em
    ),
    body1R14 = TextStyle(
        fontFamily = PretendardRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.01).em
    ),
    body2Sb14 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.01).em
    ),
    body3M14 = TextStyle(
        fontFamily = PretendardMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.01).em
    ),
    body4R12 = TextStyle(
        fontFamily = PretendardRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.01).em
    ),
    body5Sb12 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.01).em
    ),
    body6M12 = TextStyle(
        fontFamily = PretendardMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.01).em
    ),
    caption1R10 = TextStyle(
        fontFamily = PretendardRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.01).em
    ),
    caption2Sb10 = TextStyle(
        fontFamily = PretendardSemiBold,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.01).em
    ),
    caption3M10 = TextStyle(
        fontFamily = PretendardMedium,
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = (-0.01).em
    )
)

val LocalTypo = staticCompositionLocalOf { defaultCirculerTypography }
