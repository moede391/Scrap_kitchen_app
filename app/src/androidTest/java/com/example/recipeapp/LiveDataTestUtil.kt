package com.example.recipeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * This function will make sure live data is fetched before its return because tests wont work without it
 * */
fun <T> LiveData<T>.getOrAwaitValue() : T{
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T>{
        override fun onChanged(t: T) {
            data = t
            this@getOrAwaitValue.removeObserver(this)
            latch.countDown()
        }
    }
    this.observeForever(observer)
    try {
        if(!latch.await(2, TimeUnit.SECONDS)){
            throw TimeoutException("Live data never init")
        }
    } finally {
        this.removeObserver(observer)
    }
    return data as T
}