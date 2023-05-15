package com.mymind.ui.screens.statistics

import android.graphics.Color
import app.futured.donut.DonutProgressView
import app.futured.donut.DonutSection

class DonutRealisation(var donut: DonutProgressView, var statistic: StatisticsModel) {

    fun initDonut() {
        val section1 = DonutSection(
            name = "section_1",
            color = Color.parseColor("#B80C09"),
            amount = statistic.veryBad.toFloat()
        )

        val section2 = DonutSection(
            name = "section_2",
            color = Color.parseColor("#F88812"),
            amount = statistic.bad.toFloat()
        )

        val section3 = DonutSection(
            name = "section_3",
            color = Color.parseColor("#7C6A0A"),
            amount = statistic.mid.toFloat()
        )

        val section4 = DonutSection(
            name = "section_4",
            color = Color.parseColor("#69A197"),
            amount = statistic.good.toFloat()
        )

        val section5 = DonutSection(
            name = "section_5",
            color = Color.parseColor("#3F3244"),
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