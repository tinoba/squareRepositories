package com.square.repository.ui.screen.repository_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.square.repository.domain.model.RepositoryItem

@Preview
@Composable
fun SquareRepositoryCardPreview() {
    SquareRepositoryCard(
        squareRepositoryItem = RepositoryItem(1, "Tino Balint", "https://github.com/tino-balint/SquareRepositories", "description"),
        {},
        modifier = Modifier
    )
}

@Composable
fun SquareRepositoryCard(
    squareRepositoryItem: RepositoryItem,
    onUrlClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .padding(16.dp)
        ) {

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = squareRepositoryItem.fullName,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = squareRepositoryItem.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = squareRepositoryItem.htmlUrl,
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp
                    ),
                    modifier = Modifier.clickable {
                        onUrlClick.invoke(squareRepositoryItem.htmlUrl)
                    }
                )
            }
        }
    }
}
