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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cupcake.R
import com.example.cupcake.ui.theme.CupcakeTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import android.app.DatePickerDialog
import androidx.compose.ui.res.painterResource
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.text.input.TextFieldValue


data class SelectionData(val valeur: String, val date: String, val motif: String)
/**
 * Composable that displays the list of items as [RadioButton] options,
 * [onSelectionChanged] lambda that notifies the parent composable when a new value is selected,
 * [onCancelButtonClicked] lambda that cancels the order when user clicks cancel and
 * [onNextButtonClicked] lambda that triggers the navigation to next screen
 */
@Composable
fun SelectOptionScreen(
    name: String,
    options: List<String>,
    onNextButtonClicked: (SelectionData) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedValue by rememberSaveable { mutableStateOf("") }
    var textFieldValue by rememberSaveable { mutableStateOf("") }
    var selectedDate by rememberSaveable { mutableStateOf("") }
    var selectedMotif by remember { mutableStateOf("") }



    val context = LocalContext.current
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val calendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            selectedDate = dateFormat.format(calendar.time)
        },
        Calendar.getInstance().get(Calendar.YEAR),
        Calendar.getInstance().get(Calendar.MONTH),
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "VALEUR",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding( top = 32.dp, bottom = 8.dp),
        )
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                        }
                    )
                    Text(item)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = selectedValue.startsWith("Outro"),
                        onClick = {
                            selectedValue = "Outro: $textFieldValue"
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue.startsWith("Outro"),
                    onClick = {
                        selectedValue = "Outro: $textFieldValue"
                    }
                )
                TextField(
                    value = textFieldValue,
                    onValueChange = { newValue ->
                        textFieldValue = newValue
                        selectedValue = "Outro: $newValue"
                    },
                    modifier = Modifier.weight(1f)
                )
            }
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
            )
            Text(
                text = "Motif",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( top = 32.dp, bottom = 8.dp),
            )
            TextField(
                value = selectedMotif,
                onValueChange = { newValeur ->
                    selectedMotif = newValeur
                },
                label = { Text("Motif") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
            )
            Text(
                text = "Selecionner une Date",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( top = 32.dp, bottom = 8.dp),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { datePickerDialog.show() },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "Selecionner Date"
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_small)))
            Text(
                text = "Direcioné à $name",
                fontSize = 32.sp,
                modifier = modifier,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                onClick = {onNextButtonClicked(SelectionData(selectedValue, selectedDate, selectedMotif))}
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }

}

