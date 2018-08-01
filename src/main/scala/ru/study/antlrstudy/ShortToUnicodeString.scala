package ru.study.antlrstudy

import ru.study.antlrstudy.parsers.arrayinit.{ArrayInitBaseListener, ArrayInitParser}

class ShortToUnicodeString extends ArrayInitBaseListener {
  /** Translate { to " */
  override def enterInit(ctx: ArrayInitParser.InitContext): Unit = {
    System.out.print('"')
  }

  /** Translate } to " */
  override def exitInit(ctx: ArrayInitParser.InitContext): Unit = {
    System.out.print('"')
  }

  /** Translate integers to 4-digit hexadecimal strings prefixed with \\u */
  override def enterValue(ctx: ArrayInitParser.ValueContext): Unit = {
    // Assumes no nested array initializers
    val value = ctx.INT().getText.toShort
    println(f"\\u$value%04x")
  }
}
