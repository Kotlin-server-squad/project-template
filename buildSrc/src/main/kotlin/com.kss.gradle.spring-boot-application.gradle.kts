import com.gorylenko.GenerateGitPropertiesTask

plugins {
    id("org.springframework.boot")
    id("com.gorylenko.gradle-git-properties")
}

springBoot {
    buildInfo()
}

tasks.named<Jar>("jar").configure {
    enabled = false
}

gitProperties {
    failOnNoGitDirectory = false
}

tasks.withType<GenerateGitPropertiesTask>().configureEach {
    outputs.upToDateWhen { false }
}
