import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.Constants.VERSION
import org.jooq.codegen.gradle.CodegenTask

plugins {
    kotlin("jvm")
    id("org.jooq.jooq-codegen-gradle")
}

dependencies {
    jooqCodegen("org.jooq:jooq-meta-extensions:$VERSION")
}

jooq {
    configuration {
        generator {
            name = "org.jooq.codegen.KotlinGenerator"
            database {
                name = "org.jooq.meta.extensions.ddl.DDLDatabase"
                properties {
                    property {
                        key = "scripts"
                        value = jooqCodegenScripts
                    }
                }
            }
            target {
                packageName = jooqCodegenPackage
                directory = jooqCodegenDirectory
            }
        }
    }
}

tasks.withType<KotlinCompile>().configureEach {
    dependsOn(tasks.withType<CodegenTask>())
}

sourceSets {
    main {
        kotlin {
            srcDir(jooqCodegenDirectory)
        }
    }
}

private val jooqCodegenScripts
    get() =
        findProperty("jooq-codegen.scripts")
            ?.toString()
            ?: "src/main/resources/schema.sql"

private val jooqCodegenPackage
    get() =
        findProperty("jooq-codegen.package")
            ?.toString()
            ?: "$group.db.schema"

private val jooqCodegenDirectory
    get() =
        layout
            .buildDirectory
            .dir("generated-sources/jooq/kotlin")
            .get()
            .toString()
