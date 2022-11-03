package com.sprint.desprintscala.remote.impl

import com.sprint.desprintscala.clients.HttpClient
import com.sprint.desprintscala.mappers.Mapper
import com.sprint.desprintscala.remote.HHApi
import com.sprint.desprintscala.remote.model.Item
import com.sprint.desprintscala.types.Types.{CityId, Json, VacancyName}

import scala.collection.mutable.ArrayBuffer

class HHApiImpl (val mapper: Mapper[(Seq[Item],Int),Json]) extends HHApi {
  override def getVacancy(vacancy: VacancyName, city: CityId): Seq[Item] = {
    val url = generateUrl(vacancy, city, 0)
    val items = ArrayBuffer[Item]()
    val json:String = HttpClient.get(url)
    mapper.mapTo(json)._1.foreach(res=>items.append(res))
    val pages = mapper.mapTo(json)._2
    println(f"Total pages vacancy ${pages}")
    if (pages>1){
      val range = Range(1, pages)
      range.foreach(pageNum=>{
        val innerUrl = generateUrl(vacancy, city, pageNum)
        val jsonInner:String = HttpClient.get(innerUrl)
        mapper.mapTo(jsonInner)._1.foreach(res=>items.append(res))
      })
    }
    items.toSeq
  }
  override def  getVacancy(vacancy: VacancyName): Seq[Item] = {
    getVacancy(vacancy, 0)
  }

  private def generateUrl(vacancy: String, city:Int, pageNumber:Int):String={
    var innerUrl = f"https://api.hh.ru/vacancies?text=$vacancy"
    if (city>0){
      innerUrl = innerUrl.concat(f"&area=$city")
    }
    if (pageNumber>0){
      innerUrl = innerUrl.concat(f"&page=$pageNumber")
    }
    innerUrl
  }

}
