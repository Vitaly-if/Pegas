package com.example.pegas.main.presentation

interface Mapper<R, S> {

    fun map(sourse: S): R

    interface Unit<S> : Mapper<kotlin.Unit, S>
}