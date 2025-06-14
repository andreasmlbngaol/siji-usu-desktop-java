plugins {
    java
    application
    alias(libs.plugins.modularity.module)
    alias(libs.plugins.openjfx.javafx)
    alias(libs.plugins.beryx.jlink)
}

group = "com.jawapbo"
version = "1.0"

repositories {
    google()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

application {
    mainClass.set("com.jawapbo.sijiusu.MainApp")
}

javafx {
    version = libs.versions.javafx.get()
    modules = listOf("javafx.controls", "javafx.fxml")
}

dependencies {
    implementation(libs.javafx.controls)
    implementation(libs.javafx.fxml)

//    implementation(libs.postgresql)
//    implementation(libs.hibernate.core)
//
//    implementation(libs.jbcrypt)

    implementation(libs.ikonli)
    implementation(libs.material.icon)

    implementation(libs.jackson)
}
