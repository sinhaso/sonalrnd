package com.sonal.scala.scalabasics.scalabasicsproj

object HelloScalaMain {

  def main(args: Array[String]) {
    println("Hello Scala World !!")

    var learnFunctionClass: LearnAllFunctionTypes = new LearnAllFunctionTypes()

    learnToCallFunction(learnFunctionClass)

    underStandAnnonymousFunction(learnFunctionClass)

    underStandAnnonymousApplyOnVarible(learnFunctionClass)

  }

  def random(x: Int): Unit = {
    println(x)
  }

  def learnToCallFunction(learnFunctionClass: LearnAllFunctionTypes): Unit = {
    var z = learnFunctionClass.sumWay1(1, 2)
    println(z)

    var u = learnFunctionClass.sumWay2(1, 2)
    println(u)

    var v = learnFunctionClass.sumWay3(1, 2)
    println(v)
  }

  def underStandAnnonymousFunction(learnFunctionClass: LearnAllFunctionTypes) {

    learnFunctionClass.callGreetPeople()
    learnFunctionClass.callSumAnnonymousWay1()
    var result1 = AnnonymousFunction()(2, 4)
    println(result1)

    var result2 = AnnonymousFunction()(21, 4)
    println(result2)

  }

  def underStandAnnonymousApplyOnVarible(learnFunctionClass: LearnAllFunctionTypes) {
    var someValue1 = (a: Int) => a + 1;

    var someValue2 = (a: Int, b: Int) => {
      if (a % 2 == 0)
        a + b
      else
        a - b
    }

    random(someValue1(10))
    random(someValue2(21, 20))
    random(someValue2(10, 20))

  }

  def AnnonymousFunction() = (a: Int, b: Int) => {
    if (a % 2 == 0)
      a + b
    else
      a - b
  }

}
