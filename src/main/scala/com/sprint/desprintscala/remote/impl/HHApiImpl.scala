package com.sprint.desprintscala.remote.impl

import com.sprint.desprintscala.clients.HttpClient
import com.sprint.desprintscala.mappers.Mapper
import com.sprint.desprintscala.remote.HHApi
import com.sprint.desprintscala.remote.model.Item
import com.sprint.desprintscala.types.Types.{CityId, Json, VacancyName}

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

class HHApiImpl (val mapper: Mapper[(Seq[Item],Int),Json]) extends HHApi {
  override def getVacancy(vacancy: VacancyName, city: CityId): Seq[Item] = {
    var url = f"https://api.hh.ru/vacancies?text=$vacancy"
    if (city>0){
      url = url.concat(f"&area=$city")
    }
    val items = ArrayBuffer[Item]()
    val json:String = HttpClient.get(url)

    val vacancies = mapper.mapTo(json)._1
    val pages = mapper.mapTo(json)._2
    print(f"Total pages ${pages}")
    vacancies.foreach(res=>items.append(res))
    val range = Range(1, pages)
    range.foreach(res=>{
      var innerUrl = f"https://api.hh.ru/vacancies?text=$vacancy&page=$res"
      if (city>0){
        innerUrl = innerUrl.concat(f"&area=$city")
      }
      val json:String = HttpClient.get(innerUrl)
      mapper.mapTo(json)._1.foreach(r=>items.append(r))
    })
    items.toSeq
  }
  override def getVacancy(vacancy: VacancyName): Seq[Item] = {
    getVacancy(vacancy, 0)
  }
}
