package ru.study.antlrstudy

import org.antlr.v4.runtime._
import org.antlr.v4.runtime.tree._
import ru.study.antlrstudy.parsers.arrayinit.{ArrayInitLexer, ArrayInitParser}


object Main extends App {
  // create a CharStream that reads from standard input
  val input = CharStreams.fromStream(System.in)
  // create a lexer that feeds off of input CharStream
  val lexer = new ArrayInitLexer(input)
  // create a buffer of tokens pulled from the lexer
  val tokens = new CommonTokenStream(lexer)
  // create a parser that feeds off the tokens buffer
  val parser = new ArrayInitParser(tokens)
  val tree = parser.init // begin parsing at init rule
  System.out.println(tree.toStringTree(parser)) // print LISP-style tree
}
