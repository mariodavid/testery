def json = http.get{
    request.uri = "https://httpbin.org/basic-auth/username/secretpassword"
    request.auth.basic 'username', 'secretpassword'
}

jsonResult(
        summary: "worked",
        expected: json,
        actual: json
)