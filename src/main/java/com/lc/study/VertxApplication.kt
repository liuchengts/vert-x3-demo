package com.lc.study

import io.vertx.core.Vertx
import io.vertx.kotlin.core.http.httpServerOptionsOf

class VertxApplication {


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

        }
    }

    fun server() {
        val vertx= Vertx.vertx()
        var options = httpServerOptionsOf(
                maxWebsocketFrameSize = 1000000)
        var server = vertx.createHttpServer(options)
    }
}