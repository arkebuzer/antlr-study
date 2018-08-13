package ru.study.antlrstudy

import ru.study.antlrstudy.parsers.labeledexpr.{LabeledExprBaseVisitor, LabeledExprParser}
import scala.collection.mutable.{Map => MutaMap}

class EvalVisitor extends LabeledExprBaseVisitor[Int] {
  /** "memory" for our calculator variable/value pairs go here */
  private val memory: MutaMap[String, Int] = MutaMap()

  /** ID '=' expr NEWLINE */
  override def visitAssign(ctx: LabeledExprParser.AssignContext): Int = {
    val id: String = ctx.ID().getText // id is left-hand side of '='
    val value: Int = visit(ctx.expr()) // compute value of expression on right
    memory += (id -> value) // store it in our memory
    value
  }

  /** expr NEWLINE */
  override def visitPrintExpr(ctx: LabeledExprParser.PrintExprContext): Int = {
    val value: Int = visit(ctx.expr()) // evaluate the expr child
    println(value) // print the result
    0 // return dummy value
  }

  /** INT */
  override def visitInt(ctx: LabeledExprParser.IntContext): Int = {
    ctx.INT().getText.toInt
  }

  /** ID */
  override def visitId(ctx: LabeledExprParser.IdContext): Int = {
    val id: String = ctx.ID().getText
    if (memory.isDefinedAt(id)) {
      memory(id)
    } else {
      0
    }
  }

  /** expr op=('*'|'/') expr */
  override def visitMulDiv(ctx: LabeledExprParser.MulDivContext): Int = {
    val left: Integer = visit(ctx.expr(0)) // get value of left subexpression
    val right: Integer = visit(ctx.expr(1)) // get value of right subexpression
    if (ctx.op.getType == LabeledExprParser.MUL) {
      left * right
    } else {
      left / right
    } // must be DIV
  }

  /** expr op=('+'|'-') expr */
  override def visitAddSub(ctx: LabeledExprParser.AddSubContext): Int = {
    val left: Integer = visit(ctx.expr(0)) // get value of left subexpression
    val right: Integer = visit(ctx.expr(1)) // get value of right subexpression
    if (ctx.op.getType == LabeledExprParser.ADD) {
      left + right
    } else {
      left - right // must be SUB  
    }
  }

  /** '(' expr ')' */
  override def visitParens(ctx: LabeledExprParser.ParensContext): Int = {
    visit(ctx.expr()) // return child expr's value
  }

  /** clear */
  override def visitClear(ctx: LabeledExprParser.ClearContext) : Int = {
    memory.clear()
    0 // return dummy value
  }
}
