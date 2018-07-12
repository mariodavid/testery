class Person {
    String name
}

def json = http.post{
    request.uri = "http://httpbin.org/post"
    request.contentType = 'application/json'
    request.body = new Person(name: "Max Tester")
}

jsonResult(
    summary: "worked",
    expected: json,
    actual: json
)