package com.lc.study

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.kotlin.core.http.httpServerOptionsOf

class VertxApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            VertxApplication().server()
            VertxApplication().clinet()
        }
    }

//    fun server() {
//        val vertx = Vertx.vertx()
//        val options = httpServerOptionsOf(
//                maxWebsocketFrameSize = 1000000,//设置最大数据帧大小
//                logActivity = true)//记录网络活动
//        var server = vertx.createHttpServer(options)
//                .requestHandler {
//                    val path = it.path()
//                    val params = it.params()
//                    it.response().end("收到你的消息了:$path$params", "UTF-8")
//                }.listen(8080) {
//                    if (it.succeeded()) {
//                        println("Server is now listening!")
//                    } else {
//                        println("Failed to bind!")
//                    }
//                }
//    }

    fun clinet() {
        val vertx = Vertx.vertx()
        vertx.setPeriodic(100) { l ->
            vertx.createHttpClient().getNow(80, "www.taobao.com", "/") { resp ->
                resp.bodyHandler { body ->
                    println(body.toString("ISO-8859-1"))
                }
            }
        }
    }
}