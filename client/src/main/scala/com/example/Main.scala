package com.example

import scala.util.Random

object Main extends App {

  println(Client.moveAlong())
  val numBatches = 100
  val batchSize = 1000
  (1 to numBatches).foreach {
    _ =>
      val requestTimes = (1 to batchSize).map {
      _ =>
        Client.doSomethingCool(Random.nextDouble())
        Client.lastRequestTimeMs
      }

      val total = requestTimes.sum
      val numRequests = requestTimes.size
      val mean = total / numRequests
      val sortedRequestTimes = requestTimes.sorted
      def percentile(p: Double) =
        if (sortedRequestTimes.size == 1) {
          sortedRequestTimes.head
        } else {
          sortedRequestTimes((p * numRequests).toInt - 1)
        }
      def sqr(x: Double) = x * x
      val stdDev = math.sqrt(requestTimes.map(rt => sqr(mean - rt)).sum / numRequests)
      println(s"request times (ms): total = $total µ = $mean, σ = $stdDev," +
      s" 50%ile = ${percentile(.5)}, 95%ile = ${percentile(.95)}, 99%ile = ${percentile(.99)}")
  }
}
