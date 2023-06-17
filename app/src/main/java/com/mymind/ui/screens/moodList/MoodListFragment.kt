package com.mymind.ui.screens.moodList

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mymind.core.Serialisator
import com.mymind.core.UserMoodModel
import com.mymind.core.base.BaseFragment
import com.mymind.databinding.FragmentMoodListBinding
import com.mymind.ui.screens.moodList.editMood.ChangeMoodActivity
import eightbitlab.com.blurview.RenderScriptBlur
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmUUID
import java.io.Serializable
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoodListFragment : BaseFragment<FragmentMoodListBinding>() {

    private val viewModel: MoodListViewModel by viewModel()
    override fun setupBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMoodListBinding.inflate(layoutInflater, container, false)

    override fun FragmentMoodListBinding.initializeLayout() {
        viewModel.mooddbLiveData.observe(viewLifecycleOwner, ::handleList)

        val radius = 5f
        val decorView = requireActivity().window.decorView
        val rootView = recyclerViewMoodList
        val windowBackground = decorView.background

        blurView.setupWith(
            rootView,
            RenderScriptBlur(requireContext())
        ) // or RenderEffectBlur
            .setFrameClearDrawable(windowBackground) // Optional
            .setBlurRadius(radius)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }

    private fun handleList(
        userMoodModels: RealmResults<UserMoodModel>?
    ) {
        val adapter = userMoodModels?.let {
            MoodListFragmentRecyclerViewAdapter(
                it
            ) {
                val currentModel = it
                val blur = Blur(binding, requireActivity(), it, viewModel) {
                    when (it) {
                        "delete" -> {
                            Toast
                                .makeText(context, "Mood deleted", Toast.LENGTH_SHORT)
                                .show()
                        }
                        "edit" -> {
                            val intent = Intent(context, ChangeMoodActivity::class.java)
                            val serialisedModel = Serialisator()
                                .serialiseUserMoodModel(currentModel)
                            intent.putExtra("model", serialisedModel)
                            activity?.startActivity(intent)
                        }
                    }
                }
                blur.recyclerViewItemActions()
            }
        }
        binding.recyclerViewMoodList.adapter = adapter
        binding.recyclerViewMoodList.layoutManager = LinearLayoutManager(requireContext())
    }
}
