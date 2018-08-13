package ru.study.antlrstudy

import org.antlr.v4.runtime.TokenStream
import ru.study.antlrstudy.parsers.java.{JavaBaseListener, JavaParser}

class ExtractInterfaceListener(private val parser: JavaParser) extends JavaBaseListener {

  /** Listen to matches of classDeclaration */
  override def enterClassDeclaration(ctx: JavaParser.ClassDeclarationContext): Unit = {
    System.out.println(f"interface I${ctx.Identifier()} {")
  }

  override def exitClassDeclaration(ctx: JavaParser.ClassDeclarationContext): Unit = {
    System.out.println("}")
  }

  /** Listen to matches of methodDeclaration */
  override def enterMethodDeclaration(ctx: JavaParser.MethodDeclarationContext): Unit = {
    // need parser to get tokens
    val tokens: TokenStream = parser.getTokenStream
    var methodType: String = "void"
    if (ctx.`type`() != null) {
      methodType = tokens.getText(ctx.`type`())
    }
    val args: String = tokens.getText(ctx.formalParameters())
    println(f"\t$methodType ${ctx.Identifier()}$args;")
  }

  /** Listen to matches of importDeclaration */
  override def enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext): Unit = {
    val importText : String = parser.getTokenStream.getText(ctx)
    println(importText)
  }
}
