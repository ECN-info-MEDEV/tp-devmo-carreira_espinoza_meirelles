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
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.ui.theme.CupcakeTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Composable that allows the user to select the desired cupcake quantity and expects
 * [onNextButtonClicked] lambda that expects the selected quantity and triggers the navigation to
 * next screen
 */
@Composable
fun StartOrderScreen(
    names: List<String>,
    onNextButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(stringResource(R.string.nouveau))
                }
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(stringResource(R.string.bénéficiaire))
                }
                Button(
                    onClick = { },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                ) {
                    Text(stringResource(R.string.historique))
                }

            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .border(1.dp, Color.Black, shape = RoundedCornerShape(15.dp))
                    .padding(8.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {

                    for (i in names.indices step 3) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            for (j in 0..2) {
                                if (i + j < names.size) {
                                    val item = names[i + j]
                                    Button(
                                        onClick = { onNextButtonClicked(item) },
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(horizontal = 4.dp)
                                    ) {
                                        Text(item)
                                    }
                                } else {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                        if (i==0){
                            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_big)))
                        }

                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_big)))
        val searchText = remember { mutableStateOf("") }
        OutlinedTextField(
            value = searchText.value,
            onValueChange = { searchText.value = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Rechercher...") }
        )
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = "A",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )
        Text(
            text = "Association AB",
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "Association XY",
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = "M",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 32.dp, bottom = 8.dp)
        )
        Text(
            text = "Magasin AB",
            modifier = Modifier.padding(start = 16.dp)
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.falando),
                contentDescription = "Descrição da imagem"
            )
        }
    }
}


