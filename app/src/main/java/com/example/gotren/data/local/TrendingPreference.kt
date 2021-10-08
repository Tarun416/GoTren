package com.example.gotren.data.local

class TrendingPreference : Preferences() {
    var lastdatafetched by stringPref("lastdatafetched")
}