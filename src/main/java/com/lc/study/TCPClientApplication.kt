package com.lc.study

import io.vertx.core.Vertx
import io.vertx.core.net.NetClientOptions
import java.nio.charset.Charset


class TCPClientApplication {
    var vertx: Vertx? = null
    val PORT = 502
    val HOST = "192.168.100.27"

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
                val socket = conn.result()
                println("客户端连接成功: ${socket.remoteAddress()}")
//                socket.write("你好，我是客户端: ${System.currentTimeMillis()}")
                socket.write("AA 21 00 01 EE")
                socket.handler { buffer ->
                    println("收到服务器消息: ${buffer.length()}")
                    val mgs = buffer.toString(Charset.defaultCharset())
                    println("收到服务器消息: $mgs")
                }
                socket.closeHandler {
                    println("连接关闭: ${socket.remoteAddress()}")
                }
            } else {
                System.out.println("客户端连接服务器失败: ${conn.cause().message}")
            }
        }
    }
}