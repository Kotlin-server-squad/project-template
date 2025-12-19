import dev.detekt.gradle.Detekt
import java.time.LocalDate

plugins {
    kotlin("jvm")
    jacoco
    id("org.jmailen.kotlinter")
    id("dev.detekt")
    id("org.jetbrains.dokka")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(javaVersion)
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict", "-Xannotation-default-target=param-property")
        if (kotlinExperimentalPathApi) {
            freeCompilerArgs.addAll("-opt-in=kotlin.io.path.ExperimentalPathApi")
        }
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    failOnNoDiscoveredTests = false
    finalizedBy(tasks.withType<JacocoReport>())
}

tasks.withType<Jar>().configureEach {
    from(rootProject.file("LICENSE")) {
        into("META-INF")
    }
}

tasks.withType<JacocoReport>().configureEach {
    dependsOn(tasks.withType<Test>())
    reports {
        html.required = true
        xml.required = true
        csv.required = false
    }
}

kotlinter {
    reporters = arrayOf("html")
}

detekt {
    parallel = true

    detektConfig
        .takeIf { it.exists() }
        ?.let { config.setFrom(it) }

    buildUponDefaultConfig = true

    detektBaseline
        .takeIf { it.exists() }
        ?.let { baseline = it }
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required = true
        checkstyle.required = false
        sarif.required = false
        markdown.required = false
    }
}

dokka {
    pluginsConfiguration.html {
        footerMessage = "Copyright &copy; ${LocalDate.now().year}, Kotlin Server Squad"
    }
}

private val Project.javaVersion
    get() =
        findProperty("java.version")
            ?.toString()
            ?.toInt()
            ?: 21

private val kotlinExperimentalPathApi
    get() =
        findProperty("kotlin.experimental-path-api")
            ?.toString()
            ?.toBoolean()
            ?: false

private val Project.detektConfig
    get() =
        rootProject
            .file("config/detekt/detekt.yml")

private val Project.detektBaseline
    get() =
        rootProject
            .file("config/detekt/baseline.xml")
