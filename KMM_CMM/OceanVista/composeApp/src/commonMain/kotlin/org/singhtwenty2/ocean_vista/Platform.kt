package org.singhtwenty2.ocean_vista

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform