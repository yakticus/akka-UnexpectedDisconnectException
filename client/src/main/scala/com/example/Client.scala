package com.example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object Client  {
  private[this] implicit val system = ActorSystem()
  private[this] implicit val materializer = ActorMaterializer()
  import system.dispatcher // implicit execution context for futures

  private[this] val timeout = 10.seconds
  private[this] val serverUri = "http://localhost:5000"

  def moveAlong(): String = {
    getResponseBody(s"$serverUri/")
  }

  def doSomethingCool(thing: Double): String = {
    postJson(s"$serverUri/doSomethingCool", "{\"thing\": " + thing + "}")
  }

  private def postJson(uri: String, json: String): String = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(
      uri = uri,
      method = HttpMethods.POST,
      entity = HttpEntity(ContentTypes.`application/json`, json)
    ))

    awaitString(responseFuture)
  }

  private def getResponseBody(uri: String): String = {
    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(uri = uri))
    awaitString(responseFuture)
  }

  private def awaitString(responseFuture: Future[HttpResponse]): String = {
    // just block and wait for the response
    val response = Await.result(responseFuture, timeout)

    // get the response body as a string. block on it.
    Await.result(response.entity.toStrict(timeout).map(_.data.decodeString("UTF-8")), timeout)
  }

}
