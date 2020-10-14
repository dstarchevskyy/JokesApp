package com.droiddevstar.jokeapp

class Util {
    companion object {
        fun replaceEscapeChars(s: String): String {
            with(s) {
                replace("\u2013", "-")
                replace("\u2014", "-")
                replace("\u2018", "'")
                replace("\u2019", "'")
                replace("\u201c", "\"")
                replace("\u201d", "\"")
                replace("\u2022", "*")
                replace("\u2026", "...")
                replace("\u2212", "-")
                return this
            }
        }
    }
}