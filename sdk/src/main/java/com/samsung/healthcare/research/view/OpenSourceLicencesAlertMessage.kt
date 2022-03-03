package com.samsung.healthcare.research.view

import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.samsung.healthcare.research.R

@Composable
fun OpenSourceLicencesAlertMessage(
    licenceText: String,
    onDismissRequest: () -> Unit,
    onClickDismissButton: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest.invoke() },
        title = { Text(stringResource(R.string.open_source_licence)) },
        text = {
            OutlinedTextField(
                value = licenceText,
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.open_source_licence_textfiled_label)) },
                maxLines = 15,
            )
        },
        confirmButton = { },
        dismissButton = {
            TextButton(onClick = { onClickDismissButton.invoke() }) {
                Text("Close")
            }
        },
    )
}
