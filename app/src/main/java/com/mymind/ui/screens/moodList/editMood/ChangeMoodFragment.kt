package com.mymind.ui.screens.moodList.editMood

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.mymind.core.EditableMood
import com.mymind.core.UserMoodModel
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentChangeMoodBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChangeMoodFragment : BaseFragment<FragmentChangeMoodBinding>() {

    private val viewModel: ChangeMoodViewModel by viewModel()

    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangeMoodBinding.inflate(layoutInflater, container, false)

    override fun FragmentChangeMoodBinding.initializeLayout() {
        val moodEditor = EditableMood()
        val model = moodEditor.getEditableMood()
        viewModel.logick(
            model,
            binding,
            lifecycleScope,
            requireActivity()
        )
    }
}