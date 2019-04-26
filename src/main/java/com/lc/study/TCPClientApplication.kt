package com.lc.study

import io.vertx.core.Vertx
import io.vertx.core.net.NetClientOptions
import java.nio.charset.Charset


class TCPClientApplication {
    var vertx: Vertx? = null
    val PORT = 1943
    val HOST = "localhost"

    init {
        vertx = Vertx.vertx()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            TCPClientApplication().netClient()
        }
    }


    fun netClient() {
        val options = NetClientOptions().setReconnectAttempts(10).setReconnectInterval(500).setLogActivity(true)
        val client = vertx!!.createNetClient(options)
        client.connect(PORT, HOST) { conn ->
            if (conn.succeeded()) {
                println("客户端连接成功")
                val socket = conn.result()
                socket.write("你好，我是客户端: ${System.currentTimeMillis()}")
                socket.handler { buffer ->
                    val mgs = buffer.toString(Charset.defaultCharset())
                    println("收到服务器消息: $mgs")
                }
                socket.closeHandler{
                    println("连接关闭: ${socket.remoteAddress()}")
                }
            } else {
                System.out.println("客户端连接服务器失败: ${conn.cause().message}")
            }
        }
    }
}