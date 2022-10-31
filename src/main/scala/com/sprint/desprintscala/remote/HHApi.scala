package com.sprint.desprintscala.remote

import com.sprint.desprintscala.remote.model.RootInterface

trait HHApi[T]{
  def getVacancy(text:String):T
}
