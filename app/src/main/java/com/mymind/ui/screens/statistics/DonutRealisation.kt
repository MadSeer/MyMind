package com.mymind.ui.screens.statistics

import android.graphics.Color
import androidx.core.content.ContextCompat
import app.futured.donut.DonutProgressView
import app.futured.donut.DonutSection
import com.mymind.R
import com.mymind.databinding.ActivityHomeBinding
import com.mymind.ui.screens.home.HomeActivity

class DonutRealisation(var donut: DonutProgressView, var statistic: StatisticsModel) {

    fun initDonut() {
        val section1 = DonutSection(
            name = "section_1",
            color = Color.parseColor("#240046"),
            amount = statistic.veryBad.toFloat()
        )

        val section2 = DonutSection(
            name = "section_2",
            color = Color.parseColor("#3c096c"),
            amount = statistic.bad.toFloat()
        )

        val section3 = DonutSection(
            name = "section_3",
            color = Color.parseColor("#5a189a"),
            amount = statistic.mid.toFloat()
        )

        val section4 = DonutSection(
            name = "section_4",
            color = Color.parseColor("#7b2cbf"),
            amount = statistic.good.toFloat()
        )

        val section5 = DonutSection(
            name = "section_5",
            color = Color.parseColor("#9d4edd"), // color = R.color.statistic_4,
            amount = statistic.veryGood.toFloat()
        )
        donut.submitData(
            listOf(
                section1,
                section2,
                section3,
                section4,
                section5
            )
        )
        donut.cap = statistic.sum.toFloat()
    }
}