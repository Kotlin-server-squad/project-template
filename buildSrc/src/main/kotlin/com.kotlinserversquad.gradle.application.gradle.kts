plugins {
    application
}

application {
    mainClass = mainClass
}

tasks.withType<Jar>().configureEach {
    manifest {
        attributes["Main-Class"] = mainClass
    }
}

private val Project.mainClass
    get() = property("application.main-class") as String
