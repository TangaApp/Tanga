package app.books.tanga.feature.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.books.tanga.R
import app.books.tanga.ui.theme.TangaBluePale
import app.books.tanga.ui.theme.TangaLightGray

@Composable
fun EmptyLibraryScreen() {
    Column(
        modifier = Modifier.padding(top = 64.dp, bottom = 34.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.library_empty),
            style = MaterialTheme.typography.h4,
            color = TangaLightGray,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Image(
            modifier = Modifier
                .weight(3f)
                .padding(horizontal = 10.dp),
            painter = painterResource(id = R.drawable.graphic_empty_library),
            contentDescription = "empty library image"
        )
        Button(
            modifier = Modifier.fillMaxWidth().height(64.dp),
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
                backgroundColor = TangaBluePale,
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.elevation(0.dp, 0.dp)
        ) {
            Box(modifier = Modifier.padding(start = 30.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "explore summaries icon"
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.library_explore_summaries),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
            }
        }
    }
}