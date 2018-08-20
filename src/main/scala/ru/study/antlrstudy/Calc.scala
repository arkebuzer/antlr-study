package ru.study.antlrstudy

import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import ru.study.antlrstudy.parsers.labeledexpr.{LabeledExprLexer, LabeledExprParser}

object Calc extends App {
  val is = InputHelper.getInputStream(args)

  val input = CharStreams.fromStream(is)
  val lexer = new LabeledExprLexer(input)
  val tokens = new CommonTokenStream(lexer)
  val parser = new LabeledExprParser(tokens)
  val tree: ParseTree = parser.prog() // parse

  val eval: EvalVisitor = new EvalVisitor()
  eval.visit(tree)

}
