def json = http.get {
    request.uri = "http://httpbin.org/get"
}

jsonResult(
    summary: "worked",
    expected: json,
    actual: json
)