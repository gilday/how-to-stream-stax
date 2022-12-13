plugins {
    java
    id("com.diffplug.spotless") version "6.12.0"
}

spotless {
    java {
        googleJavaFormat()
    }
}

dependencies {
    compileOnly("com.google.auto.value:auto-value-annotations:1.10.1")
    annotationProcessor("com.google.auto.value:auto-value:1.10.1")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType(Test::class) {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}