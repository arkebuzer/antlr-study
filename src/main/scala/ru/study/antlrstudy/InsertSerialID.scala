package ru.study.antlrstudy

import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import ru.study.antlrstudy.parsers.java.{JavaLexer, JavaParser}

object InsertSerialID extends App {
  val is = InputHelper.getInputStream(args)

  val input = CharStreams.fromStream(is)

  val lexer = new JavaLexer(input)
  val tokens = new CommonTokenStream(lexer)
  val parser = new JavaParser(tokens)
  val tree = parser.compilationUnit // parse
  val walker = new ParseTreeWalker // create standard walker
  val extractor = new InsertSerialIDListener(tokens)
  walker.walk(extractor, tree) // initiate walk of tree with listener

  // print back ALTERED stream
  println(extractor.rewriter.getText())
}
