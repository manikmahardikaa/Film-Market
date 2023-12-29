package com.example.myapk4.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapk4.di.Injection
import com.example.myapk4.model.OrderProduct
import com.example.myapk4.ui.ViewModelFactory
import com.example.myapk4.ui.common.UiState
import com.example.myapk4.ui.component.ProductItem
import com.example.myapk4.R
import com.example.myapk4.ui.component.Search
import com.example.myapk4.ui.component.SectionText

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getAllProduct()
            }
            is UiState.Success -> {
                HomeContent(
                    orderProduct = uiState.data ,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                    )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    orderProduct: List<OrderProduct>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val query by viewModel.query
    Column {
        Box {
            Image(
                painter = painterResource(R.drawable.img_banner),
                contentDescription = "" ,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(250.dp)
                    .width(600.dp)
            )
            Search(
                query = query,
                onQueryChange = { newQuery -> viewModel.search(newQuery) }
            )
        }

        SectionText(stringResource(R.string.section))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {
            items(orderProduct) { data ->
                ProductItem(
                    image = data.product.image,
                    nameProduct = data.product.nameProduct,
                    requiredCost = data.product.cost,
                    onClick = { navigateToDetail(data.product.id) }
                )
            }
        }
    }
}
