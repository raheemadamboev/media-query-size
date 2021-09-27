package xyz.teamgravity.mediaquerysize

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

fun Modifier.mediaQuery(
    comparator: Dimensions.DimensionComparator,
    modifier: Modifier
): Modifier = composed {
    if (comparator.compare(
            width = LocalContext.current.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density,
            height = LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density
        )
    ) {
        this.then(modifier)
    } else this
}