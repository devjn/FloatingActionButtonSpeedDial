/*
 * Copyright 2022 Roberto Leinardi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leinardi.android.speeddial.sample.compose.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.leinardi.android.speeddial.sample.compose.theme.SampleTheme

@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    onNavigateUp: (() -> Unit)? = null,
    elevation: Dp = AppBarDefaults.TopAppBarElevation,
    actions: @Composable RowScope.() -> Unit = {},
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(MaterialTheme.colors.primaryVariant)
    androidx.compose.material.TopAppBar(
        title = {
            Column {
                Text(text = title)
                if (!subtitle.isNullOrEmpty()) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.alpha(ContentAlpha.medium),
                    )
                }
            }
        },
        modifier = modifier,
        navigationIcon = onNavigateUp?.let {
            {
                IconButton(
                    onClick = it,
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = actions,
        backgroundColor = MaterialTheme.colors.primary,
        elevation = elevation,
    )
}

@Composable
@Preview
private fun PreviewTopAppBarWithNavigationIcon() {
    SampleTheme {
        TopAppBar(
            title = "Page title",
        ) { }
    }
}

@Composable
@Preview
private fun PreviewTopAppBarWithSubtitle() {
    SampleTheme {
        TopAppBar(
            title = "Page title",
            subtitle = "Page subtitle",
        ) { }
    }
}

@Composable
@Preview
private fun PreviewTopAppBarWithAction() {
    SampleTheme {
        TopAppBar(
            title = "Page title",
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Share, contentDescription = "")
                }
            },
        )
    }
}

@Composable
@Preview
private fun PreviewTopAppBarWithNavigationIconAndAction() {
    SampleTheme {
        TopAppBar(
            title = "Page title",
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Share, contentDescription = "")
                }
            },
            onNavigateUp = { },
        )
    }
}
