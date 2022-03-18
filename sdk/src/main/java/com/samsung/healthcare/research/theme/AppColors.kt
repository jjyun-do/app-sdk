package com.samsung.healthcare.research.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class AppColors(
    // MaterialTheme Color
    primary: Color,
    primaryVariant: Color,
    secondary: Color,
    secondaryVariant: Color,
    background: Color,
    surface: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onSurface: Color,
    onError: Color,
    isLight: Boolean,
    // Extension
    primaryDark: Color,
    textPrimary: Color,
    textPrimaryAccent: Color,
    textSecondary: Color,
    textHint: Color,
) {
    var primary by mutableStateOf(primary)
        private set

    var primaryVariant by mutableStateOf(primaryVariant)
        private set

    var secondary by mutableStateOf(secondary)
        private set

    var secondaryVariant by mutableStateOf(secondaryVariant)
        private set

    var background by mutableStateOf(background)
        private set

    var surface by mutableStateOf(surface)
        private set

    var error by mutableStateOf(error)
        private set

    var onPrimary by mutableStateOf(onPrimary)
        private set

    var onSecondary by mutableStateOf(onSecondary)
        private set

    var onBackground by mutableStateOf(onBackground)
        private set

    var onSurface by mutableStateOf(onSurface)
        private set

    var onError by mutableStateOf(onError)
        private set

    var isLight by mutableStateOf(isLight)
        private set

    var primaryDark by mutableStateOf(primaryDark)
        private set

    var textPrimary by mutableStateOf(textPrimary)
        private set

    var textPrimaryAccent by mutableStateOf(textPrimaryAccent)
        private set

    var textSecondary by mutableStateOf(textSecondary)
        private set

    var textHint by mutableStateOf(textHint)
        private set

    fun copy(
        primary: Color = this.primary,
        primaryVariant: Color = this.primaryVariant,
        secondary: Color = this.secondary,
        secondaryVariant: Color = this.secondaryVariant,
        background: Color = this.background,
        surface: Color = this.surface,
        error: Color = this.error,
        onPrimary: Color = this.onPrimary,
        onSecondary: Color = this.onSecondary,
        onBackground: Color = this.onBackground,
        onSurface: Color = this.onSurface,
        onError: Color = this.onError,
        isLight: Boolean = this.isLight,
        primaryDark: Color = this.primaryDark,
        textPrimary: Color = this.textPrimary,
        textPrimaryAccent: Color = this.textPrimaryAccent,
        textSecondary: Color = this.textSecondary,
        textHint: Color = this.textHint,
    ): AppColors = AppColors(
        primary,
        primaryVariant,
        secondary,
        secondaryVariant,
        background,
        surface,
        error,
        onPrimary,
        onSecondary,
        onBackground,
        onSurface,
        onError,
        isLight,
        primaryDark,
        textPrimary,
        textPrimaryAccent,
        textSecondary,
        textHint,
    )

    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        primaryVariant = other.primaryVariant
        secondary = other.secondary
        secondaryVariant = other.secondaryVariant
        background = other.background
        surface = other.surface
        error = other.error
        onPrimary = other.onPrimary
        onSecondary = other.onSecondary
        onBackground = other.onBackground
        onSurface = other.onSurface
        onError = other.onError
        isLight = other.isLight
        primaryDark = other.primaryDark
        textPrimary = other.textPrimary
        textPrimaryAccent = other.textPrimaryAccent
        textSecondary = other.textPrimary
        textHint = other.textHint
    }

    fun materialColors(): Colors =
        Colors(
            primary = primary,
            primaryVariant = primaryVariant,
            secondary = secondary,
            secondaryVariant = secondaryVariant,
            background = background,
            surface = surface,
            error = error,
            onPrimary = onPrimary,
            onSecondary = onSecondary,
            onBackground = onBackground,
            onSurface = onSurface,
            onError = onError,
            isLight = isLight,
        )
}

fun lightColors(
    // FIXME should set light color
    primary: Color = Color(0xFFFF9E00),
    primaryVariant: Color = Color(0xFFFF9E00),
    secondary: Color = Color(0xFFFF9E00),
    secondaryVariant: Color = Color(0xFFFF9E00),
    background: Color = Color(0xFFFFFFFF),
    surface: Color = Color(0xFFFFFFFF),
    error: Color = Color(0xFFFF9E00),
    onPrimary: Color = Color(0xFFFF9E00),
    onSecondary: Color = Color(0xFFFF9E00),
    onBackground: Color = Color(0xFFFFFFFF),
    onSurface: Color = Color(0xFFFF9E00),
    onError: Color = Color(0xFFFF9E00),
    isLight: Boolean = true,
    primaryDark: Color = Color(0xFF443F36),
    textPrimary: Color = Color(0xFF130C00),
    textPrimaryAccent: Color = Color(0xFF443F36),
    textSecondary: Color = Color(0xFF443F36),
    textHint: Color = Color(0xFF746B5C),
): AppColors = AppColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
    onError = onError,
    isLight = isLight,
    primaryDark = primaryDark,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    textPrimaryAccent = textPrimaryAccent,
    textHint = textHint,
)

fun darkColors(
    // FIXME should set dark color
    primary: Color = Color(0xFFFF9E00),
    primaryVariant: Color = Color(0xFFFF9E00),
    secondary: Color = Color(0xFFFF9E00),
    secondaryVariant: Color = Color(0xFFFF9E00),
    background: Color = Color(0xFF626262),
    surface: Color = Color(0xFFFFFFFF),
    error: Color = Color(0xFFFF9E00),
    onPrimary: Color = Color(0xFFFF9E00),
    onSecondary: Color = Color(0xFFFF9E00),
    onBackground: Color = Color(0xFFFFFFFF),
    onSurface: Color = Color(0xFFFF9E00),
    onError: Color = Color(0xFFFF9E00),
    isLight: Boolean = true,
    primaryDark: Color = Color(0xFFFF9E00),
    textPrimary: Color = Color(0xFFFFFFFF),
    textPrimaryAccent: Color = Color(0xFFFF9E00),
    textSecondary: Color = Color(0xFFFFFFFF),
    textHint: Color = Color(0xFFADA597),
): AppColors = AppColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
    onError = onError,
    isLight = isLight,
    primaryDark = primaryDark,
    textPrimary = textPrimary,
    textSecondary = textSecondary,
    textPrimaryAccent = textPrimaryAccent,
    textHint = textHint,
)

internal val LocalColors = staticCompositionLocalOf { lightColors() }
