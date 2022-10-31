package com.sprint.desprintscala.remote.impl

import com.sprint.desprintscala.clients.HttpClient
import com.sprint.desprintscala.remote.HHApi
import com.sprint.desprintscala.remote.model.RootInterface
import net.liftweb.json._

class HHApiImpl(client:HttpClient) extends HHApi[RootInterface]{
  override def getVacancy(text: String): RootInterface = {
    val json = client.get("https://api.hh.ru/vacancies?text=java&area=3")
    val parsed = parse(json)
    val elements = (parsed \\ "items").children

    elements.foreach(println)

    RootInterface()
  }
}
