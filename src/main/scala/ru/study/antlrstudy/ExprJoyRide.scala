package ru.study.antlrstudy

import java.io.FileInputStream

import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import ru.study.antlrstudy.parsers.expr.{ExprLexer, ExprParser}

object ExprJoyRide extends App {
  val inputFile: Option[String] =
    if (args.length > 0) {
      Some(args(0))
    } else {
      None
    }

  val is =
    if (inputFile.isDefined) {
      new FileInputStream(inputFile.get)
    } else {
      System.in
    }

  val input = CharStreams.fromStream(is)
  val lexer = new ExprLexer(input)
  val tokens: CommonTokenStream = new CommonTokenStream(lexer)
  val parser = new ExprParser(tokens)
  val tree: ParseTree = parser.prog() // parse; start at prog
  println(tree.toStringTree(parser)) // print tree as text
}
