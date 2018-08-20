package ru.study.antlrstudy

import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import ru.study.antlrstudy.parsers.expr.{ExprLexer, ExprParser}

object ExprJoyRide extends App {
  val is = InputHelper.getInputStream(args)

  val input = CharStreams.fromStream(is)
  val lexer = new ExprLexer(input)
  val tokens: CommonTokenStream = new CommonTokenStream(lexer)
  val parser = new ExprParser(tokens)
  val tree: ParseTree = parser.prog() // parse; start at prog
  println(tree.toStringTree(parser)) // print tree as text
}
