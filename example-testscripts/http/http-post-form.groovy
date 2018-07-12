import groovyx.net.http.*

def json = http.post{
    request.uri = "http://httpbin.org/post"
    request.body = [hello: "World"]
    request.contentType = 'application/x-www-form-urlencoded'
    request.encoder 'application/x-www-form-urlencoded', NativeHandlers.Encoders.&form
}

jsonResult(
    summary: "worked",
    expected: json,
    actual: json
)