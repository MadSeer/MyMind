package com.mymind.ui.screens.moodList.editMood

import android.view.LayoutInflater
import android.view.ViewGroup
import com.mymind.core.EditableMood
import com.mymind.core.Serialisator
import com.mymind.core.UserMoodModel
import com.mymind.core.base.BaseActivity
import com.mymind.databinding.ActivityChangeMoodBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeMoodActivity : BaseActivity<ActivityChangeMoodBinding>() {

    override fun setupBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = ActivityChangeMoodBinding.inflate(inflater, container, false)

    override fun ActivityChangeMoodBinding.initializeLayout() {
        val serializedModel = intent.getSerializableExtra("model")
        var model = Serialisator().deserialiseUserMoodModel(serializedModel.toString())
        val moodEditor = EditableMood()
        moodEditor.setEditableMood(model)
    }
}