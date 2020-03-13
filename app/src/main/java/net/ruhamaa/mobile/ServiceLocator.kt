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
package net.ruhamaa.mobile

import android.content.Context
import androidx.annotation.VisibleForTesting
import net.ruhamaa.mobile.data.source.remote.FakeRemoteDataSource
import net.ruhamaa.mobile.data.repsitory.RuhamaaRepository
import net.ruhamaa.mobile.data.repsitory.RuhamaaRepositoryImpl

/**
 * A Service Locator for the [TasksRepository]. This is the mock version, with a
 * [FakeTasksRemoteDataSource].
 */
object ServiceLocator {

    private val lock = Any()

    @Volatile
    var ruhamaaRepository: RuhamaaRepository? = null
        @VisibleForTesting set

    fun provideRuhamaRepository(context: Context): RuhamaaRepository {
        synchronized(this) {
            return ruhamaaRepository ?: ruhamaaRepository ?: createRuhamaaRepository(context)
        }
    }
    private fun createRuhamaaRepository(context: Context): RuhamaaRepository {
        return RuhamaaRepositoryImpl(FakeRemoteDataSource())
    }
}
