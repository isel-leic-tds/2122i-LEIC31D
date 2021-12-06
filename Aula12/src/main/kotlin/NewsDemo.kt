import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun NewsDisplay() {
    Column {
        NewsView(news = News(
            title = "Isto é um título",
            content = "E isto é um longo pedaço de texto. Provavelmente um rant de alguém. O pessoal anda arreliado."
        ))
    }
}

data class News(val title: String, val content: String)

@Composable
fun NewsView(news: News) {
    Row {
        val image = painterResource("black-king.png")
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Column {
            Text(
                text = news.title,
                fontSize = 24.sp,
                modifier = Modifier.padding(all = 8.dp)
            )
            Text(
                text = news.content,
                modifier = Modifier.padding(all = 8.dp)
            )
        }
    }
}
