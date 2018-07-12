def json = http.get {
    request.uri = "https://httpbin.org/headers"
    request.headers['Custom-Header'] = 'some-custom-value'
}

jsonResult(
        summary: "worked",
        expected: json,
        actual: json
)