dependencyResolutionManagement {
    versionCatalogs {
        create("build") {
            from(files("../gradle/build.versions.toml"))
        }
    }
}

rootProject.name = "buildSrc"
