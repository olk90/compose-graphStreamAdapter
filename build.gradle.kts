import org.gradle.kotlin.dsl.invoke
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
}

group = "de.olk90"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {

    // https://mvnrepository.com/artifact/org.graphstream/gs-core
    implementation("org.graphstream:gs-core:2.0")

    // https://mvnrepository.com/artifact/org.graphstream/gs-ui-swing
    implementation("org.graphstream:gs-ui-swing:2.0")

    implementation(compose.desktop.currentOs)

    implementation("androidx.compose.material:material-icons-extended:1.7.3")

}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "compose-graphStreamAdapter"
            packageVersion = "1.0.0"
        }
    }
}

kotlin {
    jvmToolchain(21)
}