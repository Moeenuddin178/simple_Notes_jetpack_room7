package com.example.simple_notes_jetpack_room.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.simple_notes_jetpack_room.R

@Composable
fun NotePopup(
    txtState: MutableState<String> = rememberSaveable { mutableStateOf("")},
    onClickSave: (String) -> Unit,
    onClickDismiss: () -> Unit
) {

    Dialog(onDismissRequest = onClickDismiss) {

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            BasicTextField(
                modifier=Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp),
                value = txtState.value,
                onValueChange = {
                    txtState.value = it
                })

            Row(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            ) {
                Button(
                    onClick = { onClickSave(txtState.value) },
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(text = stringResource(R.string.save))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = onClickDismiss,
                    modifier = Modifier.width(120.dp)
                ) {
                    Text(text = stringResource(R.string.dismiss))
                }
            }
        }


    }

}


@Preview(showBackground = true)
@Composable
fun Previews() {
    Scaffold(
        modifier = Modifier,
        containerColor = Color.Transparent
    ) {
        it
        NotePopup( onClickSave = { /*TODO*/ }) {

        }
    }

}