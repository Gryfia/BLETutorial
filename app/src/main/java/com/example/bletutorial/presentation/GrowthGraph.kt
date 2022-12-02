package com.example.bletutorial.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bletutorial.R
import com.example.bletutorial.data.model.WeightModel
import com.example.bletutorial.util.SQLiteHelper
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class GrowthGraph : AppCompatActivity() {

    private lateinit var ourLineChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.growth_graph)
        ourLineChart = findViewById(R.id.ourLineChart)

        try {
            retrieveRecordsAndPopulateCharts()
        }catch (e: Exception) {
            Log.e("Error", e.toString())
        }
    }

    private fun populateLineChart(umur: Array<Int>, berat: Array<Float>) {
        val ourLineChartEntries: ArrayList<Entry> = ArrayList()

        var i = 0

        for (entry in umur) {
            var umur = umur[i].toFloat()
            var berat = berat[i].toFloat()
            ourLineChartEntries.add(Entry(umur, berat))
            i++
        }
        val lineDataSet = LineDataSet(ourLineChartEntries, "")
        val data = LineData(lineDataSet)
        ourLineChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = ourLineChart.xAxis
        xAxis.setDrawGridLines(true)

        ourLineChart.legend.isEnabled = false
        ourLineChart.axisRight.isEnabled = false

        ourLineChart.xAxis.apply {
            isGranularityEnabled = true
            position = XAxis.XAxisPosition.BOTTOM
            axisMinimum = 0f
            axisMaximum = 50f
        }

        ourLineChart.axisLeft.apply {
            isGranularityEnabled = true
            axisMinimum = 0f
            axisMaximum = 50f
        }

        lineDataSet.apply {
            setDrawFilled(true)
            lineWidth = 2f
            circleRadius = 4f
            color = ContextCompat.getColor(this@GrowthGraph, R.color.black_75)
            setCircleColor(ContextCompat.getColor(this@GrowthGraph, R.color.black))
            setDrawCircleHole(false)
            valueTextSize = 8f
            mode = LineDataSet.Mode.CUBIC_BEZIER
            fillDrawable = ContextCompat.getDrawable(this@GrowthGraph, R.drawable.bg_spark_line)
        }

        //remove description label
        ourLineChart.description.isEnabled = false

        //add animation
        ourLineChart.animateX(1000, Easing.EaseInSine)
        ourLineChart.data = data
        //refresh
        ourLineChart.invalidate()
    }

    fun retrieveRecordsAndPopulateCharts() {
        //creating the instance of DatabaseHandler class
        val sqliteHelper: SQLiteHelper = SQLiteHelper(this)
        //calling the retreiveAnimals method of DatabaseHandler class to read the records
        val NIK = intent.getStringExtra("NIK")
        val weight: List<WeightModel> = sqliteHelper.getWeightByNIK(NIK = NIK.toString())
        //create arrays for storing the values gotten
        val weightIDArray = Array<Int>(weight.size) { 0 }
        val weightNIKArray = Array<String>(weight.size) { "" }
        val weightUmurArray = Array<Int>(weight.size) { 0 }
        val weightBeratArray = Array<Float>(weight.size) { 0f }

        //add the records till done
        var index = 0
        for (a in weight) {
            weightIDArray[index] = a.id
            weightNIKArray[index] = a.NIK
            weightUmurArray[index] = a.umur
            weightBeratArray[index] = a.berat
            index++
        }
        //call the methods for populating the charts
        populateLineChart(weightUmurArray, weightBeratArray)

    }

}