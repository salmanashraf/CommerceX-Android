pluginManagement {
	repositories {
		google {
			content {
				includeGroupByRegex("com\\.android.*")
				includeGroupByRegex("com\\.google.*")
				includeGroupByRegex("androidx.*")
			}
		}
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

rootProject.name = "CommerceX"
include(":app")
include(":core:ui")
include(":core:network")
include(":core:feature:feature-product")
include(":core:feature:feature-cart")
include(":core:feature:feature-search")
include(":core:feature:feature-auth")
