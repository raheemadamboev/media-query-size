package xyz.teamgravity.mediaquerysize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Raheem",
                    modifier = Modifier
                        .background(Color.Green)
                        .mediaQuery(
                            comparator = Dimensions.Width greaterThan 400.dp,
                            modifier = Modifier.background(Color.Red)
                        )
                )

                MediaQuery(comparator = Dimensions.Height lessThan 400.dp) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "I'm only shown below a height of 400 dp")
                }
            }
        }
    }
}