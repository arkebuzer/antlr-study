package ru.study.antlrstudy

import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import ru.study.antlrstudy.parsers.rows.{RowsLexer, RowsParser}

object Col extends App {
  val is = InputHelper.getInputStream(args)

  val input = CharStreams.fromStream(is)
  val lexer = new RowsLexer(input)
  val tokens = new CommonTokenStream(lexer)
  val col: Int = Integer.valueOf(args(1))
  val parser = new RowsParser(tokens, col) // pass column number!
  parser.setBuildParseTree(false) // don't waste time building a tree
  parser.file()
}
