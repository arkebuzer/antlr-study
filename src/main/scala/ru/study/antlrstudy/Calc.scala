package ru.study.antlrstudy

import java.io.FileInputStream

import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import ru.study.antlrstudy.parsers.labeledexpr.{LabeledExprLexer, LabeledExprParser}

object Calc extends App {
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
  val lexer = new LabeledExprLexer(input)
  val tokens = new CommonTokenStream(lexer)
  val parser = new LabeledExprParser(tokens)
  val tree: ParseTree = parser.prog() // parse

  val eval: EvalVisitor = new EvalVisitor()
  eval.visit(tree)

}
