package com.example.nyttest.util

interface SettingsRepo : BasePrefRepo {
    fun setLanguage(value: String)
}