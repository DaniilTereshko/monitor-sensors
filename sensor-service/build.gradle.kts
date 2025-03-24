plugins {
    id("conventions-plugin.spring")
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.liquibase.core)
    implementation(libs.driver.postgresql)
    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)
}