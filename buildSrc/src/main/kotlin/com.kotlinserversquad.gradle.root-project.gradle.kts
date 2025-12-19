plugins {
    base
    id("org.owasp.dependencycheck")
    id("org.sonarqube")
}

dependencyCheck {
    formats = listOf("HTML", "JSON")

    dependencyCheckSuppressionFile
        .takeIf { it.exists() }
        ?.let { suppressionFile = it.path }

    skipConfigurations = listOf("jacocoAgent", "jacocoAnt")

    dependencyCheckNvdApiKey
        ?.let { nvd.apiKey = it }
}

sonar {
    properties {
        property("sonar.dependencyCheck.htmlReportPath", "$dependencyCheckHtmlReport")
        property("sonar.dependencyCheck.jsonReportPath", "$dependencyCheckJsonReport")
    }
}

private val dependencyCheckSuppressionFile
    get() =
        rootProject
            .file("config/dependency-check/suppression.xml")

private val dependencyCheckNvdApiKey
    get() =
        findProperty("dependency-check.nvd-api-key")
            ?.toString()

private val dependencyCheckHtmlReport
    get() =
        layout
            .buildDirectory
            .file("reports/dependency-check-report.html")
            .get()

private val dependencyCheckJsonReport
    get() =
        layout
            .buildDirectory
            .file("reports/dependency-check-report.json")
            .get()
