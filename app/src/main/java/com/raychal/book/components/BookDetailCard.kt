package com.raychal.book.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.raychal.book.ui.theme.typography
import com.raychal.book.utils.DateFormatter.formatLongDate

@Composable
fun BookDetailCard(
    title: String,
    authors: List<String>,
    thumbnailUrl: String,
    date: String?,
    status: String,
    categories: List<String>
) {
    // transparent white bg
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(start = 20.dp, end = 16.dp, top = 40.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.Transparent),
        contentAlignment = Alignment.Center
    ) {

        // white box layout
        Box(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colors.onSurface),
        )

        // Content
        BookImageContentView(title, authors, thumbnailUrl, date, status, categories)
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun BookImageContentView(
    title: String,
    authors: List<String>,
    thumbnailUrl: String,
    date: String?,
    status: String,
    categories: List<String>
) {
    // content
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

        // image
        Image(
            painter = rememberImagePainter(
                data = thumbnailUrl
            ),
            contentDescription = title,
            modifier = Modifier
                .size(240.dp, 140.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.onSurface)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = typography.h6,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primaryVariant
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "by ".plus(authors.toString()),
                style = typography.caption,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primaryVariant.copy(0.7F)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Published Date ".plus(formatLongDate(date)),
                style = typography.caption,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primaryVariant.copy(0.7F)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Status ".plus(status),
                style = typography.caption,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.primaryVariant.copy(0.7F)
            )
            Spacer(modifier = Modifier.height(12.dp))
            FlowRow {
                categories.forEach {
                    ChipView(category = it)
                    Spacer(modifier = Modifier.width(16.dp))
                }
            }
        }
    }
}
