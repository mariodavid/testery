def sql = getSql("myexternaldb")

def result = sql.rows("SELECT * FROM MY_TABLE")

jsonResult(
        summary: "worked",
        expected: result,
        actual: result
)
