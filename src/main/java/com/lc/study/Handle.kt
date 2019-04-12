package com.lc.study

import io.vertx.core.Future
import io.vertx.core.Handler

class Handle : Handler<Future<Void>> {
    override fun handle(event: Future<Void>?) {
        println(11111)
    }

}