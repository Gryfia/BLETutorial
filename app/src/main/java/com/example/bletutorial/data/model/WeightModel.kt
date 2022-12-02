package com.example.bletutorial.data.model

class WeightModel (
    var id: Int = getAutoId(),
    var NIK: String = "",
    var umur: Int = 0,
    var berat: Float = 0.0f,
){
    companion object{
        private var autoId = 0
        fun getAutoId(): Int{
            autoId++
            return autoId
        }
    }
}