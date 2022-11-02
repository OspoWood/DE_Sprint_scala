package com.sprint.desprintscala.clients

import com.sprint.desprintscala.exceptions.BadRequestException


object HttpClient {

  def get(url: String):String={
    val response = requests.get(url = url,params = Map("User-Agent" -> "api-test-agent"))
    if (response.statusCode==200) {
      response.text()
    }else{
      throw new BadRequestException()
    }
  }
}
