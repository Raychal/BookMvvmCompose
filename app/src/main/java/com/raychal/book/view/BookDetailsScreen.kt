package com.raychal.book.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raychal.book.R
import com.raychal.book.components.BookDetailCard
import com.raychal.book.components.TopBar
import com.raychal.book.navigation.MainActions
import com.raychal.book.ui.theme.typography
import com.raychal.book.utils.DetailViewState
import com.raychal.book.viewModel.MainViewModel

@Composable
fun BookDetailsScreen(
    viewModel: MainViewModel,
    actions: MainActions
) {
    Scaffold(topBar = {
        TopBar(title = stringResource(id = R.string.text_bookDetails), actions = actions)
    }) {
        BookDetails(viewModel = viewModel)
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun BookDetails(viewModel: MainViewModel) {
    when (val result = viewModel.bookDetails.value) {
        DetailViewState.Loading -> Text(text = "Loading")
        is DetailViewState.Error -> Text(text = "Error found: ${result.exception}")
        is DetailViewState.Success -> {
            val book = result.data

            LazyColumn {
                item {
                    BookDetailCard(book.title, book.authors, book.thumbnailUrl, book.publishedDate, book.status, book.categories)
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = stringResource(id = R.string.text_bookDetails),
                        style = typography.subtitle1,
                        color = MaterialTheme.colors.primaryVariant,
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = book.longDescription.ifEmpty { "There is no description" },
                        style = typography.body1,
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colors.primaryVariant.copy(0.7F),
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
        DetailViewState.Empty -> Text("No results found!")
    }
}