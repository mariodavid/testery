/**
 * assume this is the input JSON from JsonTeststepInput:
 *
 * {
 *     "hello": "world",
 *     "nested": {
 *         "hello": "world",
 *         "listOf": [
 *             {"hello": "world1"},
 *             {"hello": "world2"},
 *         ]
 *     }
 * }
 */

jsonResult(
        summary: "worked - " + input.hello,
        expected: input.nested,
        actual: input.nested.listOf
)
