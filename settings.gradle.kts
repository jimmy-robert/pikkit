enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "Pikkit"

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

listOf("app", "core", "features").forEach { includeAll(file(it)) }

private fun includeAll(file: File) {
    // Include if folder contains a build.gradle.kts file
    val buildFile = File(file, "build.gradle.kts")
    if (buildFile.exists()) {
        val relativePath = file.toRelativeString(rootProject.projectDir)
        val module = relativePath.replace(File.separator, ":")
        include(":$module")
    }
    // Recursive for subdirectories
    else {
        file.listFiles()
            ?.filter { it.isDirectory && it.name != "build" }
            ?.forEach { includeAll(it) }
    }
}
