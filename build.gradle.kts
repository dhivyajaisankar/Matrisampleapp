// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false

}


buildscript {
    repositories {
        jcenter()
        google()
        mavenCentral()


    }

        repositories {
            mavenLocal()
            google()
            jcenter()

        }
        dependencies {
            classpath("io.realm:realm-gradle-plugin:10.16.1")
        }

    }


