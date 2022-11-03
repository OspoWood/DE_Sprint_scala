package com.sprint.desprintscala.service

import com.sprint.desprintscala.remote.model.Salary

object ConverterCurrency {

  def convertToRub(salary:Salary):Float={
    if (salary==null){
      return 0.0f
    }
    val currency = gerAvg(salary.from, salary.to)
    matchCurr(currency, salary.currency)
  }

  private def gerAvg(from:Float=0, to:Float=0):Float={
    var  avg:Float = 0
    if (from!=null){
      avg = avg+from
    }
    if (to!=null){
      avg = avg+to
    }
    avg/2
  }

  private def matchCurr(cur:Float, currency: String): Float = currency match {
    case "RUR" =>{ cur}
    case "EUR" =>  {cur * 60}
    case "USD" =>  {cur * 60}
    case _ =>  { cur}
  }

}
