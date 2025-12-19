import org.springframework.boot.gradle.plugin.SpringBootPlugin

plugins {
    alias(libs.plugins.springBootProject)
    alias(libs.plugins.springBootApplication)
}

dependencies {
    implementation(platform(SpringBootPlugin.BOM_COORDINATES))
    implementation(libs.springBootStarter)
    implementation(libs.kotlinReflect)
}
