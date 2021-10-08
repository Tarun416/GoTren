package com.example.gotren.data.local

import android.content.Context
import android.content.SharedPreferences
import kotlin.reflect.KProperty

abstract class Preferences {
    companion object {
        private var context: Context? = null

        /**
         * Initialize PrefDelegate with a Context reference.
         *
         * **This method needs to be called before any other usage of PrefDelegate!!**
         */
        fun init(context: Context) {
            Companion.context = context
        }
    }

    private val prefs: SharedPreferences by lazy {
        if (context != null)
            context!!.getSharedPreferences(javaClass.simpleName, Context.MODE_PRIVATE)
        else
            throw IllegalStateException("Context was not initialized. Call Preferences.init(context) before using it")
    }

    private val listeners = mutableListOf<SharedPrefsListener>()

    abstract class PrefDelegate<T>(val prefKey: String?) {


        abstract operator fun getValue(thisRef: Any?, property: KProperty<*>): T
        abstract operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
    }

    interface SharedPrefsListener {
        fun onSharedPrefChanged(property: KProperty<*>)
    }


    fun stringPref(prefKey: String? = null, defaultValue: String? = null) =
            StringPrefDelegate(prefKey, defaultValue)

    inner class StringPrefDelegate(prefKey: String? = null, val defaultValue: String?) :
            PrefDelegate<String?>(prefKey) {
        override fun getValue(thisRef: Any?, property: KProperty<*>): String? =
                prefs.getString(prefKey ?: property.name, defaultValue)

        override fun setValue(thisRef: Any?, property: KProperty<*>, value: String?) {
            prefs.edit().putString(prefKey ?: property.name, value).apply()
            onPrefChanged(property)
        }
    }

    private fun onPrefChanged(property: KProperty<*>) {
        listeners.forEach { it.onSharedPrefChanged(property) }
    }
}
