package org.example


class TypeSafeBuilderDemo {
    private val data = mutableListOf<Any>()

    fun head(block: Head.() -> Unit) {
        val head = Head()
        head.block()
        data.add(head)
    }

    fun body(block: Body.() -> Unit) {
        val body = Body()
        body.block()
        data.add(body)
    }

    override fun toString(): String {
        return "<html>\n${data.joinToString("\n")}\n</html>"
    }
}

class Head {
    private val headData = mutableListOf<HeadData>()

    fun title(titleText: String) {
        headData.add(HeadData.Title(titleText))
    }

    override fun toString(): String {
        return "<head>\n${headData.joinToString("\n")}\n</head>"
    }
}

sealed class HeadData {
    data class Title(val text: String) : HeadData() {
        override fun toString(): String = "<title>$text</title>"
    }
}

class Body {
    private val bodyData = mutableListOf<BodyElement>()

    fun h1(text: String) {
        bodyData.add(BodyElement.H1(text))
    }

    fun p(text: String) {
        bodyData.add(BodyElement.P(text))
    }

    override fun toString(): String {
        return "<body>\n${bodyData.joinToString("\n")}\n</body>"
    }
}

sealed class BodyElement {
    data class H1(val text: String) : BodyElement() {
        override fun toString(): String = "<h1>$text</h1>"
    }

    data class P(val text: String) : BodyElement() {
        override fun toString(): String = "<p>$text</p>"
    }
}

fun main() {
    val html = TypeSafeBuilderDemo()

    html.head {
        title("My HTML Page")
    }

    html.body {
        h1("Welcome to HTML Page")
        p("Basic HTML DSL in Kotlin.")
    }

    println(html)
}