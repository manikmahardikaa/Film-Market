package com.example.myapk4.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapk4.ui.theme.Shapes
import com.example.myapk4.R
import com.example.myapk4.ui.theme.MyApk4Theme

@Composable
fun ProductItem(
    image: Int,
    nameProduct: String,
    requiredCost: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.clickable {
            onClick()
        }
    ) {
        Image(
            painter = painterResource(image) ,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = nameProduct,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold)
        )
        Text(
            text = stringResource(R.string.required_cost,requiredCost),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary)
    }
}

@Composable
@Preview(showBackground = true)
fun ProductItemPreview(){
    MyApk4Theme {
        ProductItem(image = R.drawable.wakanda, nameProduct = "Wakanda", requiredCost = 50000, onClick = {} )
    }
}