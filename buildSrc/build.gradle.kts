plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    api(build.dependencyCheckGradlePlugin)
    api(build.detektGradlePlugin)
    api(build.dokkaGradlePlugin)
    api(build.gitPropertiesGradlePlugin)
    api(build.jooqCodegenGradlePlugin)
    api(build.jooqMetaExtensions)
    api(build.kotlinJvmGradlePlugin)
    api(build.kotlinSpringGradlePlugin)
    api(build.kotlinterGradlePlugin)
    api(build.sonarqubeGradlePlugin)
    api(build.springBootGradlePlugin)
}
