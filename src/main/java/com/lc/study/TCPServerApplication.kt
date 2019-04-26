package com.lc.study

import io.vertx.core.Vertx
import io.vertx.core.buffer.Buffer
import java.nio.charset.Charset


class TCPServerApplication {
    var vertx: Vertx? = null
    val PORT = 1943
    val HOST = "localhost"

    init {
        vertx = Vertx.vertx()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TCPServerApplication().netServer()
        }
    }

    fun netServer() {
        val server = vertx!!.createNetServer()
        server.connectHandler { socket ->
            socket.handler { buffer ->
                val mgs = buffer.toString(Charset.defaultCharset())
                println("收到内容: $mgs")
                socket.write("服务器应答: $mgs")
            }
            socket.closeHandler {
                println("连接关闭: ${socket.remoteAddress()}")
            }
        }
        server.listen(PORT) { res ->
            if (res.succeeded()) {
                System.out.println("Server is nowlistening on actual port: " + server.actualPort())
            } else {
                System.out.println("Failed tobind!")
            }
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


}