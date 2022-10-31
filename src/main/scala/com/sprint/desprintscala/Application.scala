package com.sprint.desprintscala


import com.sprint.desprintscala.clients.HttpClient
import com.sprint.desprintscala.remote.impl.HHApiImpl
import requests.Response

import scala.language.postfixOps

object Application extends App {

  println("\nTask a")
  processPhrase("Hello, Scala!")

  println("\nTask b")
  val firstIncome = EmployeeIncomeAttributes(1300, 15, 30)
  val ms = monthSalary(firstIncome)
  println(ms)
  println("\nTask c")
  val listSalary: Seq[Float] = Seq[Float](100, 150, 200, 80, 120, 75)
  println(monthSalariesDeviation(listSalary))

  println("\nTask d")
  val min = listSalary.min
  val max = listSalary.max
  println(s"Min: $min,  Max: $max")

  println("\nTask e")
  val newSalaries = addNeSalariesAndSort(listSalary, Seq(350, 90))
  println(newSalaries)


  println("\nTask f")
  val salariesWithAddedIndex = addNewSalaryInSortedIndex(newSalaries, 130)
  println(salariesWithAddedIndex)

  println("\nTask g")
  val indexMiddle = getMiddleEmployeesIndex(salariesWithAddedIndex, 120, 160)
  println(indexMiddle)


  println("\nTask h")
  val upperSalaries = incrementSalaries(salariesWithAddedIndex, 7)
  println(upperSalaries)

  val client = new HHApiImpl(new HttpClient())
  println(client.getVacancy("scala"))




  def processPhrase(phrase: String): Unit = {
    println(phrase.reverse)
    val total = phrase.replaceAll("!", " ").toLowerCase.concat(" and goodbye python!")
    println(total)
  }


  def monthSalary(emplAttr: EmployeeIncomeAttributes): Float = {
    val yearIncomeWithTax = (emplAttr.yearIncome + (emplAttr.yearIncome * emplAttr.premiumAmountPercent / 100)) * 0.87
    (yearIncomeWithTax.toFloat + emplAttr.foodCompensation) / 12
  }

  def std(salaryList: Seq[Float]): Float = {
    math.sqrt((salaryList.map(res => math.pow((res - mean(salaryList)), 2)).sum.toFloat) / salaryList.size).toFloat
  }

  def mean(salaryList: Seq[Float]): Float = {
    salaryList.sum / salaryList.size
  }

  def monthSalariesDeviation(salaries: Seq[Float]): Seq[Float] = {
    val meanSalary = mean(salaries)
    val oneProcent = meanSalary / 100
    listSalary.map(s => s - meanSalary).map(s => s * oneProcent)
  }

  def addNeSalariesAndSort(oldSalaries: Seq[Float], newSalaries: Seq[Float]): Seq[Float] = {
    (oldSalaries ++ newSalaries).sorted
  }

  def addNewSalaryInSortedIndex(salaries: Seq[Float], salary: Float): Seq[Float] = {
    val indexEl: Int = salaries.count(res => res < 130)
    salaries.take(indexEl) ++ List(130f) ++ salaries.drop(indexEl)
  }

  def getMiddleEmployeesIndex(salaries: Seq[Float], middlelavelSalaryFrom: Float, middlelavelSalaryTo: Float): Seq[Int] = {

    salaries.filter(res => res >= middlelavelSalaryFrom && res <= middlelavelSalaryTo)
      .map(res => salaries.indexOf(res))

  }

  def incrementSalaries(salaries: Seq[Float], procent:Float):Seq[Float]={
    salaries.map(res=>res+(res*procent/100))
  }

  //      val listSalary = List[Float](100, 150, 200, 80, 120, 75)
  //      // println(monthSalary(1200000, 15, 3000))
  //
  //      // println(monthSalaryStd(100, 15, 3000, listSalary))
  //
  //      // Task f
  //      val newListSalary = (listSalary:+90F:+350F).sorted
  //      val indexEl:Int = newListSalary.count(_ < 130)
  //      val salaryWithNewEmployee:List[Float] = newListSalary.take(indexEl) ++ List(130f) ++ newListSalary.drop(indexEl)
  //      println(salaryWithNewEmployee)
  //      // Task g
  //
  //    }
  //
  //
  //    def phrasePipeline(phrase: String)(fun: String => String)(fun2: String => String)(fun3: String => String): String = {
  //      fun3(fun2(fun(phrase)))
  //    }
  //
  //    def helloScalaReversed(): Unit = {
  //      printer("Hello, Scala!")(res => res.reverse)
  //    }
  //
  //    def printer[T](variable: T)(fun: T => T): Unit = {
  //      println("Result: " + fun(variable))
  //    }
  //
  //    def monthSalary(yearIncome: Float, premiumAmountPercent: Float, foodCompensation: Float): Float = {
  //      val yearIncomeWithTax = (yearIncome + (yearIncome * premiumAmountPercent / 100)) * 0.87
  //      (yearIncomeWithTax.toFloat + foodCompensation) / 12
  //    }
  //
  //
  //    def monthSalaryStd(yearIncome: Float, premiumAmountPercent: Float, foodCompensation: Float, salaryList: Seq[Float]): Float = {
  //      val ms = monthSalary(yearIncome, premiumAmountPercent, foodCompensation)
  //      val mean = salaryList.sum / salaryList.size
  //      val std = math.sqrt((salaryList.map(res => math.pow((res - mean), 2))
  //        .sum.toFloat) / salaryList.size).toFloat
  //      val procent = ms / 100
  //      ((ms - std) / procent)
  //    }
  //
  //
  //
  //
  //
  //
  //  }
  //  def getMiddleEmployees(myEmployeesSalary: List[Float], middleSalary:List[Float]): List[Float]={
  //
  //    val lbEmpl = myEmployeesSalary.to(ListBuffer)
  //    val lbMiddle = middleSalary.to(ListBuffer)
  //    lbEmpl--=lbMiddle
  //    lbEmpl.toList
  //  }
  //
  ////  h.     Однако наступил кризис и ваши сотрудники требуют
  ////  повысить зарплату. Вам необходимо проиндексировать зарплату
  ////    каждого сотрудника на уровень инфляции – 7%
  //
  //  def incriminateSalary(allSalary:Seq[Float], pracent:Float): Seq[Float]={
  //    allSalary.map(res =>res+(res*(pracent/100))).map(res=>res.toFloat)
  //  }

}


case class EmployeeIncomeAttributes(yearIncome: Float, premiumAmountPercent: Float, foodCompensation: Float)