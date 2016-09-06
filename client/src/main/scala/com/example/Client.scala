package com.example

  import scalaj.{http => scalaj}

object Client  {
  val serverUri = "http://localhost:5000"

  var lastRequestTimeMs = 0l

    private def timeIt[T](f: => T): T = {
    val start = System.currentTimeMillis()
    val result = f
    val end = System.currentTimeMillis()
    lastRequestTimeMs = end - start
    result
  }

  def moveAlong(): String = {
    getResponseBody("/")
  }

  def doSomethingCool(thing: Double): String = {
    postJson("/doSomethingCool", "{\"thing\": " + thing + "}")
  }

  private[this] def http(uri: String): scalaj.HttpRequest = {
    scalaj.Http(serverUri + uri).timeout(1000, 10000)
  }

  private[this] def getResponseBody(uri: String): String = {
    val response = timeIt {
      http(uri).asString
    }
    response.body
  }

  private[this] def postJson(uri: String, json: String): String = {
    val response = timeIt {
      http(uri).postData(json).header("Content-Type", "application/json").asString
    }
    response.body
  }
}
