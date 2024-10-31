package com.dalvik.newpokedex.navigation

sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Unauthenticated(route = "unauthenticated")
        data object Login : Unauthenticated(route = "login")
        data object Registration : Unauthenticated(route = "registration")
        data object RecoveryPassword : Unauthenticated(route = "recovery_password")
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {
        data object NavigationRoute : Authenticated(route = "authenticated")
        data object Dashboard : Authenticated(route = "Dashboard")
    }
}