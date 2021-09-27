package xyz.teamgravity.mediaquerysize

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class Dimensions {
    object Width : Dimensions()
    object Height : Dimensions()

    sealed class DimensionOperator {
        object LessThan : DimensionOperator()
        object GreaterThan : DimensionOperator()
    }

    class DimensionComparator(
        val operator: DimensionOperator,
        private val dimension: Dimensions,
        val value: Dp
    ) {

        fun compare(width: Dp, height: Dp): Boolean {
            return when (dimension) {
                Width -> when (operator) {
                    DimensionOperator.LessThan -> width < value
                    DimensionOperator.GreaterThan -> width > value
                }
                Height -> when (operator) {
                    DimensionOperator.LessThan -> height < value
                    DimensionOperator.GreaterThan -> height > value
                }
            }
        }
    }
}

@Composable
fun MediaQuery(
    comparator: Dimensions.DimensionComparator,
    content: @Composable () -> Unit
) {
    if (comparator.compare(
            width = LocalContext.current.resources.displayMetrics.widthPixels.dp / LocalDensity.current.density,
            height = LocalContext.current.resources.displayMetrics.heightPixels.dp / LocalDensity.current.density
        )
    ) {
        content()
    }
}

infix fun Dimensions.lessThan(value: Dp): Dimensions.DimensionComparator {
    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.LessThan,
        dimension = this,
        value = value
    )
}

infix fun Dimensions.greaterThan(value: Dp): Dimensions.DimensionComparator {
    return Dimensions.DimensionComparator(
        operator = Dimensions.DimensionOperator.GreaterThan,
        dimension = this,
        value = value
    )
}