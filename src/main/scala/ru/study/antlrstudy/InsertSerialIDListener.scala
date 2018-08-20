package ru.study.antlrstudy

import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.TokenStreamRewriter
import ru.study.antlrstudy.parsers.java.{JavaBaseListener, JavaParser}

class InsertSerialIDListener(tokens: TokenStream) extends JavaBaseListener {
  val rewriter: TokenStreamRewriter = new TokenStreamRewriter(tokens)

  override def enterClassBody(ctx: JavaParser.ClassBodyContext): Unit = {
    val field = "\n\tpublic static final long serialVersionUID = 1L;"
    rewriter.insertAfter(ctx.start, field)
  }
}