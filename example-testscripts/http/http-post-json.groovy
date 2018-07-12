
def json = http.post{
    request.uri = "http://httpbin.org/post"
    request.contentType = 'application/json'
    request.body = [hello: "world"]
}

jsonResult(
    summary: "worked",
    expected: json,
    actual: json
)