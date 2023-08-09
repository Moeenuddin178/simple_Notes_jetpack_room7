package com.example.simple_notes_jetpack_room.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.simple_notes_jetpack_room.R
import com.example.simple_notes_jetpack_room.viewmodel.HomeViewModelAbstract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: HomeViewModelAbstract,
    onClickClose: () -> Unit
) {
    val note = viewModel.selectedNoteState.value

    val txtState = rememberSaveable { mutableStateOf(note?.text ?: "") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Edit") },
                actions = {
                   IconButton(onClick = {
                       note?.let {
                         viewModel.insertOrupdate(it)
                       }
                       onClickClose()
                   }) {
                       Icon(imageVector = Icons.Rounded.Done
                           , contentDescription = stringResource(id = R.string.save) )
                   }

                },
                navigationIcon = {
                    IconButton(onClick = onClickClose) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.dismiss
                            )
                        )
                    }
                }
            )
        }
    ) {
        it
        Column(
            modifier = Modifier
                .padding(it)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            BasicTextField(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                value = txtState.value,
                onValueChange = {
                    txtState.value = it
                })


        }
    }

}