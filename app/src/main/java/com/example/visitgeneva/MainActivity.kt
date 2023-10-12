package com.example.visitgeneva

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.visitgeneva.ui.VisitGenevaApp
import com.example.visitgeneva.ui.theme.VisitGenevaTheme

/**
 * App Ideas and Plans
 *
 * This is a simple app that showcases the different activities and places to visit in the city of
 * Geneva, Switzerland. The app is an exercise to practice creating multi-screen applications in
 * jetpack compose. The idea for this project is from:
 * https://developer.android.com/codelabs/basic-android-kotlin-compose-my-city
 *
 * _________________________________________________________________________________________________
 *
 * Ideas: Show a grid that displays a list of activity categories (e.g Food, Attractions, History, Art, etc..)
 * -Clicking on a grid item will navigate to a new screen that displays a list of recommended
 * places within that category
 * -Clicking on a place within the list of places will bring up a details screen for that place.
 * -The app will be adaptive and show a different layout for tablets/foldables (show list and details
 * in the same screen for tablets). Achieved using WindowSizeClass API.
 * -Include images within the details screen
 * _________________________________________________________________________________________________
 *
 * Steps:
 * 1. Create Data classes and enums
 *      -create a data class for the categories, places, (possible to do list)
 * 2. Create small previews for the list items for places, and grid items for categories
 * 3. Create larger composables for groupings of these items (List only, list and detail, home screen)
 * 4. Make use of lazyColumns and LazyGrids, and use local data within data package
 * 5. Make adaptive for screen sizes, and customize layout/navigation based on screen size
 * 6. Themes, colors, typography, string resources, cleanup
 */


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VisitGenevaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VisitGenevaApp()
                }
            }
        }
    }
}
