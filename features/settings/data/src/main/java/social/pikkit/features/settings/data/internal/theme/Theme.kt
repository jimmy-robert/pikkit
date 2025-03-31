package social.pikkit.features.settings.data.internal.theme

import social.pikkit.features.settings.Settings

internal enum class Theme(val value: String) {
    Light("light"),
    Dark("dark"),
    System("system");

    companion object {
        fun from(theme: Settings.Theme) : Theme {
            return when (theme) {
                Settings.Theme.Light -> Light
                Settings.Theme.Dark -> Dark
                Settings.Theme.System -> System
            }
        }

        fun Theme.toTheme() : Settings.Theme {
            return when (this) {
                Light -> Settings.Theme.Light
                Dark -> Settings.Theme.Dark
                System -> Settings.Theme.System
            }
        }

        fun parse(value: String) : Theme? {
            return Theme.entries.find { it.value == value }
        }
    }
}