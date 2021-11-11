package worksheet.wasd

import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.*

fun main() {
    val client: OkHttpClient = OkHttpClient()
    val file = File("/Users/dmitry/Desktop/wasd/test1.ts").apply {  createNewFile() }
    val fileOutputStream = file.outputStream()
    var sec = 11
//    var o3 = true

    while( sec < 60 ){
        sec += 2

        var response = client.sendRequest2(sec)
        if( response.code() == 404 ) {
            response.body().close()
            response = client.sendRequest2(sec)
            if( response.code() == 404 ) {
                sec -= 1
                println("error $")
                response.body().close()
                continue
            }
        }
        println("code: ${response.code()}")
        response.body().byteStream().use { input ->

                input.copyTo(fileOutputStream)

        }
//        file.writeBytes(response.body().byteStream().readBytes())
        println("sleep")
        Thread.sleep(500)
    }
    print("done")
    fileOutputStream.close()

}

fun OkHttpClient.sendRequest(sec: Int, o3: Boolean): Response {
    val request = Request.Builder()
        .url("https://cdn-curie.wasd.tv/live/300432/tracks-v1a1/2021/10/11/00/36/$sec-0208${if (o3) 3 else 4}.ts")
        .method("GET", null)
        .addHeader("authority", "cdn-curie.wasd.tv")
        .addHeader("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"")
        .addHeader("sec-ch-ua-mobile", "?0")
        .addHeader(
            "user-agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36"
        )
        .addHeader("accept", "*/*")
        .addHeader("origin", "https://wasd.tv")
        .addHeader("sec-fetch-site", "same-site")
        .addHeader("sec-fetch-mode", "cors")
        .addHeader("sec-fetch-dest", "empty")
        .addHeader("referer", "https://wasd.tv/")
        .addHeader("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
        .build()
    return newCall(request).execute()
}
fun OkHttpClient.sendRequest2(sec: Int): Response {
    val request = Request.Builder()
        .url("https://cdn-curie.wasd.tv/live/64469/tracks-v1a1/2021/10/11/00/52/$sec-02000.ts")
        .method("GET", null)
        .addHeader("authority", "cdn-curie.wasd.tv")
        .addHeader("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"")
        .addHeader("sec-ch-ua-mobile", "?0")
        .addHeader(
            "user-agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.131 Safari/537.36"
        )
        .addHeader("accept", "*/*")
        .addHeader("origin", "https://wasd.tv")
        .addHeader("sec-fetch-site", "same-site")
        .addHeader("sec-fetch-mode", "cors")
        .addHeader("sec-fetch-dest", "empty")
        .addHeader("referer", "https://wasd.tv/")
        .addHeader("accept-language", "en-GB,en-US;q=0.9,en;q=0.8")
        .build()
    return newCall(request).execute()
}

