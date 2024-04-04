package com.alexisdev.product_catalog.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alexisdev.product_catalog.R
import com.alexisdev.product_catalog.presentation.utils.PhonePreview

@Composable
fun FilterBottomSheet(
    filterTags: List<String>,
    showFilterBottomSheet: @Composable () -> Unit
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheetLayout(
    filterTags: List<String>,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(true) }

   if (showBottomSheet) {
       ModalBottomSheet(
           sheetState = sheetState,
           dragHandle = { BottomSheetDefaults.DragHandle() },
           onDismissRequest = onDismiss
       ) {
           FilterBottomSheetContent(filterTags = filterTags)
       }
   }
}

@Composable
fun FilterBottomSheetContent(
    filterTags: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.filter_bottom_sheet_title),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            for (tag in filterTags) {
                FilterItem(filterTag = tag)
            }
        }
        Button(
            shape = ShapeDefaults.Small,
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(bottom = 32.dp),
        ) {
            Text(
                text = "Готово",//stringResource(id = R.string.filter_ready),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


@Composable
fun FilterItem(filterTag: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
        ) {
        Text(
            text = filterTag,
            style = MaterialTheme.typography.bodyLarge
            )
        Checkbox(
            checked = true,
            onCheckedChange = {}
            )
    }
}

@PhonePreview
@Composable
fun FilterBottomSheetContentPreview() {
    FilterBottomSheetContent(
        listOf("По скидке", "Без мяса", "Острое"),
        Modifier.padding(24.dp)
    )
}
