package com.sprint.desprintscala.remote

import com.sprint.desprintscala.Application.client
import com.sprint.desprintscala.clients.HttpClient
import com.sprint.desprintscala.configuration.JsonMapper.mapper
import com.sprint.desprintscala.mappers.Mapper
import com.sprint.desprintscala.remote.impl.HHApiImpl
import com.sprint.desprintscala.remote.model
import com.sprint.desprintscala.remote.model.Item
import com.sprint.desprintscala.types.Types.{CityId, Json, VacancyName}

trait HHApi{

  def getVacancy(vacancy:VacancyName, city: CityId):Seq[Item]
  def getVacancy(vacancy:VacancyName): Seq[Item]

}


object HHApi{
  def apply(mapper: Mapper[(Seq[Item],Int),Json]) ={
    new HHApiImpl(mapper);
  }
}


