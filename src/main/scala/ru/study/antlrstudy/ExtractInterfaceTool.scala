package ru.study.antlrstudy

import java.io.FileInputStream

import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import ru.study.antlrstudy.parsers.java.{JavaLexer, JavaParser}

object ExtractInterfaceTool extends App {

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

  val lexer = new JavaLexer(input)
  val tokens = new CommonTokenStream(lexer)
  val parser = new JavaParser(tokens)
  val tree = parser.compilationUnit // parse
  val walker = new ParseTreeWalker // create standard walker
  val extractor = new ExtractInterfaceListener(parser)
  walker.walk(extractor, tree) // initiate walk of tree with listener
}
