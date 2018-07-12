def sql = getSql()

def result = sql.rows("SELECT * FROM SEC_USER")
jsonResult(
        summary: "worked",
        expected: result,
        actual: result
)
