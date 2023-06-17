package com.mymind.di

import com.mymind.core.Database
import com.mymind.core.EditableMood
import com.mymind.ui.screens.moodList.MoodListViewModel
import com.mymind.ui.screens.moodList.editMood.ChangeMoodFragment
import com.mymind.ui.screens.moodList.editMood.ChangeMoodViewModel
import com.mymind.ui.screens.statistics.StatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object Modules {

    val viewModelModules = module {
        single { Database() }
    }

    val appModule = module {
        viewModel { MoodListViewModel(get()) }
        viewModel { StatisticsViewModel(get()) }
        viewModel { ChangeMoodViewModel(get()) }
    }
}