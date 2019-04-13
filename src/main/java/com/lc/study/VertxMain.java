package com.lc.study;

import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;
import io.vertx.ext.web.codec.BodyCodec;

public class VertxMain {

    static Vertx vertx = Vertx.vertx();
    static String q = "女装";
    static String auctionTag = "";
    static String perPageSize = "50";
    static String shopTag = "";
    static String _tb_token_ = "f5a5ee887e37e";
    static String ip = "125.119.236.28";
    static String url = "/items/search.json";

    public static void main(String[] args) {
        new VertxMain().start();
    }

    public void start() {
        WebClientOptions webClientOptions = new WebClientOptions()
                .setDefaultHost("pub.alimama.com")
                .setKeepAlive(false)
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36")
                .setLogActivity(true);
        WebClient webClient = WebClient.create(vertx, webClientOptions);
        Long time = System.currentTimeMillis();
        String _t = String.valueOf(time);
        String t = String.valueOf(time + 1654935);
        String pt = String.valueOf(time - 132);
        String random = "1799";
        String pvid = "10_" + ip + "_" + random + "_" + pt;
        String path = url + "?q=" + q +
                "&_t=" + _t +
                "&auctionTag=" + auctionTag +
                "&perPageSize=" + perPageSize +
                "&shopTag=" + shopTag +
                "&t=" + t +
                "&_tb_token_=" + _tb_token_ +
                "&pvid=" + pvid;
        String referer = "https://pub.alimama.com/promo/search/index.htm?q=" + q + "&_t=" + _t;
        webClient.get(url)
                .putHeader("authority", "pub.alimama.com")
                .putHeader("method", "GET")
                .putHeader("path", path)
                .putHeader("scheme", "https")
                .putHeader("accept", "application/json,text/javascript,*/*; q=0.01")
                .putHeader("accept-encoding", "")
                .putHeader("accept-language", "zh-CN,zh;q=0.9")
                .putHeader("content-type", "application/json;charset=UTF-8")
                .putHeader("cookie", "t=fb32572ae6b34a64db852cd87cda0166; cna=DMYDFTkT4HQCAX14o/S1ERcN; account-path-guide-s1=true; 331140007_yxjh-filter-1=true; undefined_yxjh-filter-1=true; cookie2=195fe975ef20fc7b75ef9c33356d9b42; _tb_token_=f5a5ee887e37e; v=0; alimamapwag=TW96aWxsYS81LjAgKE1hY2ludG9zaDsgSW50ZWwgTWFjIE9TIFggMTBfMTNfNikgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzczLjAuMzY4My4xMDMgU2FmYXJpLzUzNy4zNg%3D%3D; cookie32=5d4a3a690618e16b3bd7e4faef9438f8; alimamapw=E3BXFHZUQ3J0Q3d7QXBUQyYhE3dVFHBZVgA5VgRcUAQFUVJVVAFSCAcCAAFTVQYLV1BVUVQDAgUB%0AVFc%3D; cookie31=OTUzOTEwMzIsJUU5JTgxJTk3JUU1JUJGJTk4JUU1JUI3JUI0JUU5JUJCJThFMDEsNzkwODA5NTY4QHFxLmNvbSxUQg%3D%3D; 95391032_yxjh-filter-1=true; login=VT5L2FSpMGV7TQ%3D%3D; JSESSIONID=1F2261791E40F9CF31E88D0F83FA5AC4; l=bBj5VMoqvmRTgGfXBOCgCuI8UO7tsIRAguPRwdVXi_5CK6L1oHQOlZiSTFp6Vj5R_0LB4hln-U29-etbi; isg=BCgohmay4HqKJswzSkUz7sHD-RD6-Y4y-VB8O-JZcaOWPcinimHb63_7NZVoDUQz")
                .putHeader("referer", referer)
                .putHeader("x-requested-with", "XMLHttpRequest")
                .as(BodyCodec.string())
                .addQueryParam("q", q)
                .addQueryParam("_t", _t)
                .addQueryParam("auctionTag", auctionTag)
                .addQueryParam("perPageSize", perPageSize)
                .addQueryParam("shopTag", shopTag)
                .addQueryParam("t", t)
                .addQueryParam("_tb_token_", _tb_token_)
                .addQueryParam("pvid", pvid)
                .send(handle -> {
                    System.out.println(handle.toString());
                    handle.result().headers().forEach(key -> {
                        System.out.println("headers >>" + key.getKey() + ":" + key.getValue());
                    });
                    // 处理响应的结果
                    if (handle.succeeded()) {
                        String res = handle.result().body();
                        System.out.println("data:" + res);
                    } else {
                        System.out.println("失败");
                    }
                    System.out.println(System.currentTimeMillis());
                    webClient.close();
//                    System.exit(1);
                });

    }
}
