package com.raychal.book.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.raychal.book.ui.theme.primary
import com.raychal.book.ui.theme.text
import com.raychal.book.ui.theme.typography
import com.raychal.book.utils.coloredShadow

@Composable
fun ItemBookList(
    title: String,
    author: String,
    thumbnailUrl: String,
    categories: List<String>,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onItemClick)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colors.background)
            .clip(RoundedCornerShape(20.dp))
            .padding(12.dp)
    ) {

        // Row - Image + Content
        Row(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.onSurface),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            Image(
                painter = rememberImagePainter(
                    data = thumbnailUrl
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(96.dp, 145.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Content
            Column {
                Text(
                    text = "by ".plus(author),
                    style = typography.caption,
                    color = MaterialTheme.colors.primaryVariant.copy(0.7F)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = title,
                    style = typography.subtitle1,
                    color = MaterialTheme.colors.primaryVariant
                )
                Spacer(modifier = Modifier.height(12.dp))
                FlowRow {
                    categories.forEach {
                        ChipView(category = it)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

        }

    }
}


@Composable
fun ChipView(category: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(primary.copy(.10F))
            .padding(start = 12.dp, end = 12.dp, top = 5.dp, bottom = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = category, style = typography.caption, color = primary)
    }
}

//.coloredShadow(
//color = MaterialTheme.colors.primary,
//alpha = 0.2F,
//12.dp,
//shadowRadius = 20.dp,
//offsetX = 0.dp,
//offsetY = 4.dp
//)