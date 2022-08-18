# kotlin-rop-sam

State-Action-Model pattern with Railway Oriented Programming

## Using Gradle

In your build.gradle.kts.

```kotlin
repositories {
    maven { setUrl("https://funczz.github.io/kotlin-rop") }
    maven { setUrl("https://funczz.github.io/kotlin-rop-sam") }
}
dependencies {
    api("com.github.funczz:rop-sam:<VERSION>")
}
```