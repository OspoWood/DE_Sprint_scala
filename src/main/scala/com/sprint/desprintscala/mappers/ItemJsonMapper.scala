package com.sprint.desprintscala.mappers

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.sprint.desprintscala.configuration.JsonMapper.mapper
import com.sprint.desprintscala.remote.model.{Item, Salary}
import com.sprint.desprintscala.types.Types.Json

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class ItemJsonMapper extends Mapper[(Seq[Item], Int), Json] {
  override def mapTo(item: Json): (Seq[Item], Int) = {
    val tree= mapper.readTree(item)

    val items = ArrayBuffer[Item]()
    val countPages=tree.get("pages").asInt()

    tree.get("items").elements().forEachRemaining(
      res=>{
        val item:Item =  new Item(
          res.get("id").asText(),
          res.get("name").asText(),
          parseSalary(res.get("salary")), res.get("address").asText())
        items.append(item)
      }
    )
    (items.toSeq, countPages)
  }

  private def parseSalary(node:JsonNode):Salary={
    if (node.isNull){
      return null
    }
    Salary(
      node.get("from").asInt(),
      node.get("to").asInt(),
      node.get("currency").asText(),
      node.get("gross").asBoolean())
  }
}
