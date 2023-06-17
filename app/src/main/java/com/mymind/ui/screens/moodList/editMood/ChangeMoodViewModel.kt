package com.mymind.ui.screens.moodList.editMood

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.mymind.core.Database
import com.mymind.core.EditableMood
import com.mymind.core.UserMoodModel
import com.mymind.databinding.FragmentChangeMoodBinding
import com.mymind.ui.screens.home.CarouselMoodRealisation
import io.realm.kotlin.types.RealmUUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ChangeMoodViewModel(
    private val database: Database
) : ViewModel() {

    fun logick(
        model: UserMoodModel,
        binding: FragmentChangeMoodBinding,
        lifecycleScope: LifecycleCoroutineScope,
        fragment: FragmentActivity
    ) {
        binding.moodCommentaryPlainText.setText(model.commentary)
        binding.timePicker1.setIs24HourView(true)
        binding.timePicker1.minute = model.minute.toInt()
        binding.timePicker1.hour = model.hour.toInt()
        binding.timePicker1.isClickable = false
        CarouselMoodRealisation(binding.viewPagerActivityMain)
        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPagerActivityMain
        ) { tab, position -> }.attach()
        binding.viewPagerActivityMain.setCurrentItem(model.mood, false)
        binding.writeMood.setOnClickListener {
            model.mood = binding.viewPagerActivityMain.currentItem
            model.commentary = binding.moodCommentaryPlainText.text.toString()
            runBlocking {
                database.changeMood(model)
            }
            lifecycleScope.launch(Dispatchers.Main) {
                fragment.finish()
            }
        }
    }
}