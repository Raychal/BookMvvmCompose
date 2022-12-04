package com.raychal.book.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.raychal.book.R
import com.raychal.book.components.ItemBookList
import com.raychal.book.components.TextInputField
import com.raychal.book.model.BookItem
import com.raychal.book.navigation.MainActions
import com.raychal.book.utils.ViewState
import com.raychal.book.viewModel.MainViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalComposeUiApi
@Composable
fun BookListScreen(
    viewModel: MainViewModel,
    actions: MainActions
) {

    when (val result = viewModel.books.value) {
        ViewState.Empty -> Text("No results found!")
        is ViewState.Error -> Text(text = "Error found: ${result.exception}")
        is ViewState.Success -> {
            BookList(result.data, actions)
        }
        ViewState.Loading -> Text(text = "Loading")
    }
}

@ExperimentalComposeUiApi
@Composable
fun TopBar(
    actions: MainActions
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Explore thousands of \nbooks in go",
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colors.primaryVariant,
                maxLines = 2,
                modifier = Modifier
                    .padding(start = 16.dp, end = 24.dp, bottom = 24.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.ic_person),
            contentDescription = "Profile",
            tint = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .size(24.dp)
                .clickable(onClick = actions.gotoProfile)
        )
    }
}

@ExperimentalComposeUiApi
@Composable
fun BookList(
    bookList: List<BookItem>,
    actions: MainActions
) {

    val search = remember {
            mutableStateOf("")
    }

    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        contentPadding = PaddingValues(top = 18.dp, bottom = 24.dp),
        modifier = Modifier
            .background(MaterialTheme.colors.background)
    ) {
        item {
            TopBar(actions)
        }

        item {
            TextInputField(
                label = stringResource(R.string.text_search),
                value = search.value,
                onValueChanged = {
                    search.value = it
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Famous Books",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.primaryVariant,
                modifier = Modifier
                    .padding(start = 16.dp, end = 24.dp, bottom = 8.dp)
            )
        }

        items(bookList.filter { it.title.contains(search.value, ignoreCase = true) }) { book ->
            ItemBookList(book.title, book.authors.toString(), book.thumbnailUrl, book.categories, onItemClick = {
                actions.gotoBookDetails.invoke(book.isbn)
            })
        }
    }
}