package com.sprint.desprintscala.service


import com.sprint.desprintscala.remote.model.Item
import com.sprint.desprintscala.types.{Junior, Middle, Senior, WorkingLevels}

trait ProcessVacancy {
  def getMeanSalary(vacancy:Seq[Item], WorkingLevels:WorkingLevels):Float
}


object ProcessVacancy extends ProcessVacancy {
  private def isJunior (arg:Float):Boolean=arg<100000f
  private def isMiddle (arg:Float):Boolean=arg>=100000f&&arg<200000f
  private def isSenior  (arg:Float):Boolean=arg>=200000f

  override def getMeanSalary(vacancy: Seq[Item], levels: WorkingLevels): Float = {
    val salaryInRub= vacancy.map(res=>ConverterCurrency.convertToRub(res.salary))

    levels match {
      case _: Junior => getMean(salaryInRub, isJunior)
      case _: Middle => getMean(salaryInRub, isMiddle)
      case _: Senior => getMean(salaryInRub, isSenior)
      case _ => 0f
    }
  }

  private def getMean(salary:Seq[Float], condition: (Float)=>Boolean):Float={
      val filteredSalary = salary.filter(_>0).filter(condition)
      filteredSalary.sum/filteredSalary.size
  }
}
