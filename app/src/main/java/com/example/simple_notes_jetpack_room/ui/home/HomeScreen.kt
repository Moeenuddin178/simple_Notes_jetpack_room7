package com.example.simple_notes_jetpack_room.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.simple_notes_jetpack_room.R
import com.example.simple_notes_jetpack_room.model.NoteEntity
import com.example.simple_notes_jetpack_room.viewmodel.HomeViewModelAbstract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

private enum class PopupState {
    Open, Close, Edit
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModelAbstract, onClickNote: (NoteEntity) -> Unit) {
    val noteListState = homeViewModel.noteListFlow.collectAsState(initial = listOf())
    var popupState = rememberSaveable { mutableStateOf(PopupState.Close) }
    val mContext = LocalContext.current
    val txtState = rememberSaveable { mutableStateOf("") }
    val noteIdState: MutableState<Long?> = rememberSaveable { mutableStateOf(null) }


    Scaffold(
        modifier = Modifier, containerColor = Color.Transparent
    ) {
        it
        Column(Modifier.fillMaxSize()) {
            LazyColumn(Modifier.weight(1f)) {
                ////Note items List
                items(noteListState.value.size) { index ->
                    val note = noteListState.value[index]
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(1.dp)
                        .combinedClickable(
                            onClick = {
                                noteIdState.value = note.roomId
                                txtState.value = note.text
                                //  popupState.value = PopupState.Edit

                                homeViewModel.selectNote(note)
                                onClickNote(note)

                            },
                            onLongClick = {
                                homeViewModel.deleteNote(note)
                            }
                        )
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 16.dp, end = 16.dp), text = note.text, maxLines = 1
                        )
                        Spacer(
                            modifier = Modifier
                                .background(color = Color.Gray.copy(alpha = 0.54f))
                                .fillMaxWidth()
                                .height(0.5.dp)
                                .align(Alignment.BottomCenter)
                        )
                    }
                }

            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Button(modifier = Modifier.align(Alignment.Center), onClick = {
                    popupState.value = PopupState.Open
                    //                            note++
                    //                            homeViewModelAbstract.addNote(NoteEntity(text = "note  $note"))
                }) {
                    Text(text = stringResource(R.string.add_note_button))
                }

            }
        }


    }

    when (popupState.value) {
        PopupState.Open -> {
            NotePopup(onClickDismiss = {
                popupState.value = PopupState.Close
            }, onClickSave = {
                homeViewModel.insertOrupdate(note = NoteEntity(text = it))
                popupState.value = PopupState.Close
            })
        }

        PopupState.Close -> {
        }

        PopupState.Edit -> {
            NotePopup(txtState = txtState, onClickDismiss = {
                popupState.value = PopupState.Close
            }, onClickSave = {
                homeViewModel.updateNote(
                    note = NoteEntity(
                        roomId = noteIdState.value, text = it
                    )
                )
                popupState.value = PopupState.Close
            })
        }
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    HomeScreen(homeViewModel = object : HomeViewModelAbstract {
        override val selectedNoteState: State<NoteEntity?>
            get() = mutableStateOf(null)
        override val noteListFlow: Flow<List<NoteEntity>>
            get() = flowOf(
                listOf(
                    NoteEntity(text = "note 1"),
                    NoteEntity(text = "note 2"),
                    NoteEntity(text = "note 3"),
                    NoteEntity(text = "note 4"),
                    NoteEntity(text = "note 7"),
                )
            )

        override fun insertOrupdate(note: NoteEntity) {
            TODO("Not yet implemented")
        }


        override fun deleteNote(note: NoteEntity) {
            TODO("Not yet implemented")
        }

        override fun updateNote(note: NoteEntity) {
            TODO("Not yet implemented")
        }

        override fun selectNote(note: NoteEntity) {
            TODO("Not yet implemented")
        }

    }, onClickNote = {}


    )
}