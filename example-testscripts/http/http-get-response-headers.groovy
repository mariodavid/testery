def headerList = http.get{
    request.uri = "https://httpbin.org/headers"
    response.success { fs ->
        return fs.headers
    }
}

jsonResult(
        summary: "worked",
        expected: headerList,
        actual: headerList
)