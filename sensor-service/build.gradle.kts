plugins {
    id("conventions-plugin.spring")
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.cloud.starter.openfeign)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.jjwt)
    implementation(libs.liquibase.core)
    implementation(libs.driver.postgresql)
    implementation(libs.mapstruct)
    annotationProcessor(libs.mapstruct.processor)
}