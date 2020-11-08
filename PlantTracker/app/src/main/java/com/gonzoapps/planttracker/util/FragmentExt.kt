/*
 * Copyright (C) 2019 The Android Open Source Project
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
package com.gonzoapps.planttracker.util

/**
 * Extension functions for Fragment.
 */

import androidx.fragment.app.Fragment
import com.gonzoapps.planttracker.App
import com.gonzoapps.planttracker.ViewModelFactory
import com.gonzoapps.planttracker.db.PlantDatabase

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val dataSource = PlantDatabase.getInstance(requireContext().applicationContext as App).plantDatabaseDao
    return ViewModelFactory(dataSource, this)
}