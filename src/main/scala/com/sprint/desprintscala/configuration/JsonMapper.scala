package com.sprint.desprintscala.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object JsonMapper {
  def mapper():ObjectMapper={
    val mapper = new ObjectMapper()
    mapper.registerModule(DefaultScalaModule)
  }
}
