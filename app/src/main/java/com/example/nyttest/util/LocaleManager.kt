package com.example.nyttest.util

import android.content.Context
import androidx.annotation.StringRes

import java.util.Locale

object LocaleManager {

    private var context: Context? = null
    private var repo: SettingsRepo? = null

    fun init(context: Context, repo: SettingsRepo) {
        this.repo = repo
        this.context = applyLanguage(context)
    }

    fun applyLanguage(context: Context): Context {
        val locale = Locale(getLocale().language)
        val configurations = context.resources.configuration

        Locale.setDefault(locale)

        // won't care about versioning, since the app support 21 and above
        configurations.setLocale(locale)
        return context.createConfigurationContext(configurations)
    }

    fun setLanguage(locale: Locale) {
        setLocale(locale)
        context?.let {
            context = applyLanguage(it)
        }
    }

    fun setLocale(lang: Locale) {
        repo?.setLanguage(lang.language)
    }


    fun getLocale(): Locale {
        //in the event of old app users were on chinese language, we set them to English as well
        return Locale(Locale.ENGLISH.language)//Locale(repo?.getLanguage() ?: Locale.ENGLISH.language)
    }

    fun getString(@StringRes resId: Int): String {
        return context?.getString(resId) ?: ""
    }

    fun destroy() {
        context = null
        repo = null
    }
}