package ru.study.antlrstudy

import java.io.{FileInputStream, InputStream}

object InputHelper {

  def getInputStream(args: Array[String]): InputStream = {
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

    is
  }

}
