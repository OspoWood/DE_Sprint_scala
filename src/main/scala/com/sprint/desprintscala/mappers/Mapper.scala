package com.sprint.desprintscala.mappers

trait Mapper [T, P]{
  def mapTo(item:P):T
}
