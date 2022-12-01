package com.example.bletutorial.data

import com.example.bletutorial.util.Resource
import kotlinx.coroutines.flow.MutableSharedFlow

interface ScaleReceiveManager {

    val data: MutableSharedFlow<Resource<ScaleResult>>

    fun reconnect()

    fun disconnect()

    fun startReceiving()

    fun closeConnection()

}