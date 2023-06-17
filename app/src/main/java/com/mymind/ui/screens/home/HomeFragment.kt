package com.mymind.ui.screens.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.mymind.core.QuoteAPI
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentHomeBinding
import com.mymind.ui.screens.newMood.NewMoodActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(layoutInflater, container, false)

    override fun FragmentHomeBinding.initializeLayout() {
        button.setOnClickListener {
            val intent = Intent(activity, NewMoodActivity::class.java)
            activity?.startActivity(intent)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            QuoteAPI().getResponse { response ->
                lifecycleScope.launch(Dispatchers.Main) {
                    quoteTextViev.text = response
                }
            }
        }
    }
}