package com.raychal.book.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.raychal.book.R
import com.raychal.book.components.BookDetailCard
import com.raychal.book.components.ProfileCard
import com.raychal.book.navigation.MainActions
import com.raychal.book.ui.theme.typography
import com.raychal.book.utils.DetailViewState
import com.raychal.book.utils.ProfileViewState
import com.raychal.book.utils.ViewState
import com.raychal.book.viewModel.MainViewModel

@Composable
fun ProfileScreen(
    viewModel: MainViewModel,
    actions: MainActions
) {
    Scaffold(topBar = {
        com.raychal.book.components.TopBar(title = stringResource(id = R.string.profile), actions = actions)
    }) {
        Profile(viewModel = viewModel)
    }
}

@SuppressLint("StateFlowValueCalledInComposition", "UnrememberedMutableState")
@Composable
fun Profile(viewModel: MainViewModel) {
    when (val result = viewModel.profile.value) {
        ProfileViewState.Loading -> Text(text = "Loading")
        is ProfileViewState.Error -> Text(text = "Error found: ${result.exception}")
        is ProfileViewState.Success -> {
            val profile = result.data

            var darkMode by mutableStateOf(true)

            LazyColumn {
                // Profile Card
                item {
                    ProfileCard(profile.first().fullName, profile.first().nicName, profile.first().email, profile.first().photo)
                }

                item {
                    Spacer(modifier = Modifier.height(400.dp))
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = stringResource(id = R.string.version),
                            style = typography.subtitle1,
                            color = MaterialTheme.colors.primaryVariant,
                            modifier = Modifier
                                .padding(start = 20.dp, end = 20.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
            }

        }
        ProfileViewState.Empty -> Text("No results found!")
    }
}