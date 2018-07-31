package ru.study.antlrstudy

import org.antlr.v4.runtime.{CharStreams, CommonTokenStream}
import org.antlr.v4.runtime.tree.ParseTreeWalker
import ru.study.antlrstudy.parsers.arrayinit.{ArrayInitLexer, ArrayInitParser}

object Translate extends App {
  // create a CharStream that reads from standard input
  val input = CharStreams.fromStream(System.in)
  // create a lexer that feeds off of input CharStream
  val lexer = new ArrayInitLexer(input)
  // create a buffer of tokens pulled from the lexer
  val tokens = new CommonTokenStream(lexer)
  // create a parser that feeds off the tokens buffer
  val parser = new ArrayInitParser(tokens)
  val tree = parser.init // begin parsing at init rule

  // Create a generic parse tree walker that can trigger callbacks
  val walker: ParseTreeWalker = new ParseTreeWalker()
  // Walk the tree created during the parse, trigger callbacks
  walker.walk(new ShortToUnicodeString(), tree)
  System.out.println(); // print a \n after translation
}
