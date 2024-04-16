pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Foodies"
include(":app")

include(":features:product_catalog")
include(":features:splashscreen")
include(":features:product_details")
include(":features:cart")

include(":core:products_api")
include(":core:ui")
include(":core:model")
include(":core:domain")
include(":core:database")
include(":core:data")
