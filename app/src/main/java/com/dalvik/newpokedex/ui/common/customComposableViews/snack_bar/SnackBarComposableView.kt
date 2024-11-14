package com.dalvik.newpokedex.ui.common.customComposableViews.snack_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose.colorError
import com.example.compose.colorSuccess
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun rememberSnackBarState(): SnackBarState {
    return remember { SnackBarState() }
}

@Composable
fun SnackBarError(
    modifier: Modifier = Modifier,
    state: SnackBarState,
    duration: Long = 3000L,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        SnackBarComponent(
            state = state,
            duration = duration,
            containerColor = colorError,
            contentColor = Color.White,
            verticalPadding = 12.dp,
            horizontalPadding = 12.dp,
            icon = Icons.Default.Warning,
            enterAnimation = expandVertically(
                animationSpec = tween(delayMillis = 300),
                expandFrom = Alignment.Top
            ),
            exitAnimation = shrinkVertically(
                animationSpec = tween(delayMillis = 300),
                shrinkTowards = Alignment.Top
            )
        )
    }
}

@Composable
fun SnackBarSuccess(
    modifier: Modifier = Modifier,
    state: SnackBarState,
    duration: Long = 3000L,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        SnackBarComponent(
            state = state,
            duration = duration,
            containerColor = colorSuccess,
            contentColor = Color.White,
            verticalPadding = 12.dp,
            horizontalPadding = 12.dp,
            icon = Icons.Default.Check,
            enterAnimation = expandVertically(
                animationSpec = tween(delayMillis = 300),
                expandFrom = Alignment.Top
            ),
            exitAnimation = shrinkVertically(
                animationSpec = tween(delayMillis = 300),
                shrinkTowards = Alignment.Top
            )
        )
    }
}


@Composable
internal fun SnackBarComponent(
    state: SnackBarState,
    duration: Long,
    containerColor: Color,
    contentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    icon: ImageVector,
    enterAnimation: EnterTransition,
    exitAnimation: ExitTransition,
) {
    var showSnackBar by remember { mutableStateOf(false) }
    val message by rememberUpdatedState(newValue = state.message.value)

    DisposableEffect(
        key1 = state.updateState
    ) {
        showSnackBar = true
        val timer = Timer("Animation Timer", true)
        timer.schedule(duration) {
            showSnackBar = false
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                bottom = 0.dp
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = state.isNotEmpty() && showSnackBar,
            enter = enterAnimation,
            exit = exitAnimation
        ) {
            SnackBarCustom(
                message,
                containerColor,
                contentColor,
                verticalPadding,
                horizontalPadding,
                icon
            )
        }
    }
}


@Composable
internal fun SnackBarCustom(
    message: String?,
    containerColor: Color,
    contentColor: Color,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    icon: ImageVector,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(
                fraction = 1f
            )
            .background(
                color = containerColor,
                shape = RectangleShape
            )
            .padding(vertical = verticalPadding)
            .padding(horizontal = horizontalPadding)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(4f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "SnackBar Icon",
                tint = contentColor
            )

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = message ?: "Unknown",
                color = contentColor,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}