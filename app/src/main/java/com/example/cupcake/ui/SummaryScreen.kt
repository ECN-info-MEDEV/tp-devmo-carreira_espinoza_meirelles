/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.data.OrderUiState
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.sp
import java.util.*
/**
 * This composable expects [orderUiState] that represents the order state, [onCancelButtonClicked]
 * lambda that triggers canceling the order and passes the final order to [onSendButtonClicked]
 * lambda
 */
@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onSendButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    lateinit var textToSpeech:TextToSpeech

    textToSpeech = TextToSpeech(context) { status ->
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.FRENCH)
        }
    }



    //Load and format a string resource with the parameters.
    val orderSummary = stringResource(
        R.string.transaction_details,
        orderUiState.name,
        orderUiState.valeur,
        orderUiState.date,
        orderUiState.motif
    )
    val newOrder = stringResource(R.string.new_cupcake_order)

    val textToRead = buildString {
        append(stringResource(R.string.direcione))
        append(" ")
        append(orderUiState.name)
        append(". ")
        append(stringResource(R.string.valeur))
        append(" ")
        append(orderUiState.valeur)
        append(". ")
        append(stringResource(R.string.pickup_date))
        append(" ")
        append(orderUiState.date)
        append(". ")
        append(stringResource(R.string.motif))
        append(" ")
        append(orderUiState.motif)
        append(".")
    }


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row {
                    Box(modifier = Modifier
                        .weight(1f)
                        .drawWithContent {
                            drawContent()
                            drawLine(
                                Color.Black,
                                start = Offset(size.width - 1, 0f),
                                end = Offset(size.width - 1, size.height),
                                strokeWidth = 1.dp.toPx()
                            )
                            drawLine(
                                Color.Black,
                                start = Offset(0f, size.height - 1),
                                end = Offset(size.width, size.height - 1),
                                strokeWidth = 1.dp.toPx()
                            )
                        }
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = stringResource(R.string.valeur).uppercase(), fontWeight = FontWeight.Bold, fontSize = 25.sp )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = orderUiState.valeur, fontSize = 22.sp )
                        }
                    }
                    Box(modifier = Modifier.weight(1f)
                        .drawWithContent {
                            drawContent()
                            drawLine(
                                Color.Black,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 1.dp.toPx()
                            )
                            drawLine(
                                Color.Black,
                                start = Offset(0f, size.height - 1),
                                end = Offset(size.width, size.height - 1),
                                strokeWidth = 1.dp.toPx()
                            )
                        }

                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = stringResource(R.string.direcione).uppercase(), fontWeight = FontWeight.Bold, fontSize = 25.sp )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = orderUiState.name, fontSize = 22.sp )
                        }
                    }
                }
                Row {
                    Box(modifier = Modifier.weight(1f)
                        .drawWithContent {
                            drawContent()
                            drawLine(
                                Color.Black,
                                start = Offset(size.width - 1, 0f),
                                end = Offset(size.width - 1, size.height),
                                strokeWidth = 1.dp.toPx()
                            )
                            drawLine(
                                Color.Black,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                strokeWidth = 1.dp.toPx()
                            )
                        }

                    ) {
                        Column(modifier = Modifier.padding(8.dp) ) {
                            Text(text = stringResource(R.string.pickup_date).uppercase(), fontWeight = FontWeight.Bold, fontSize = 25.sp )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = orderUiState.date, fontSize = 22.sp )
                        }
                    }
                    Box(modifier = Modifier.weight(1f)
                        .drawWithContent {
                            drawContent()
                            drawLine(
                                Color.Black,
                                start = Offset(0f, 0f),
                                end = Offset(0f, size.height),
                                strokeWidth = 1.dp.toPx()
                            )
                            drawLine(
                                Color.Black,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                strokeWidth = 1.dp.toPx()
                            )
                        }

                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(
                                text = stringResource(R.string.motif).uppercase(),
                                fontWeight = FontWeight.Bold,
                                fontSize = 25.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = orderUiState.motif, fontSize = 22.sp )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(id = R.drawable.voirplus),
                    contentDescription = "",
                    modifier = Modifier.size(width = 100.dp, height = 100.dp)
                )
                IconButton(
                    onClick = {
                        textToSpeech.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, null) },
                    modifier = Modifier
                        .size(100.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.falando),
                        contentDescription = ""
                    )
                }
            }



            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
        }
        Row(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSendButtonClicked(newOrder, orderSummary) }
                ) {
                    Text(stringResource(R.string.send))
                }
            }
        }
    }
}