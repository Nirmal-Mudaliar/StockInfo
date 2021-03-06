package com.example.stockmarket.presentation.company_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.BackHand
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stockmarket.presentation.destinations.CompanyListingsScreenDestination
import com.example.stockmarket.ui.theme.DarkBlue
import com.example.stockmarket.ui.theme.cardColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.result.ResultBackNavigator


@Composable
@Destination
fun CompanyInfoScreen(
    navigator: ResultBackNavigator<Boolean>,
    symbol: String,
    viewModel: CompanyInfoViewModel = hiltViewModel()
) {
    val state = viewModel.state
    if (state.error == null) {
        Column(
            Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .padding(16.dp)
        ) {
            state.company?.let { company ->
                IconButton(
                    modifier = Modifier
                        .align(Start),

                    onClick = {
                    navigator.navigateBack(true)
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back button")
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = company.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "( ${company.symbol} )",
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Industry :  ${company.industry}",
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Country :  ${company.country}",
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(12.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = company.description,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                )
                if (state.stocksInfos.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Market Summary")
                    Spacer(modifier = Modifier.height(16.dp))
                    Card(
                        modifier = Modifier
                        .fillMaxWidth(),
                        backgroundColor = cardColor,
                        elevation = 8.dp,

                    ) {
                        StockChart(
                            infos = state.stocksInfos,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .align(CenterHorizontally)
                                .padding(16.dp)
                        )
                    }

                }
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        else if(state.error != null) {
            Text(text = state.error, color = MaterialTheme.colors.error)
        }
    }

}