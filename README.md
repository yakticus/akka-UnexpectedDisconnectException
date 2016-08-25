## what is this?

This repo reproduces an exception seen when using akka-http client for the purposes of diagnosis.

## install

You need Python & Pip installed to run this example. I used Python 2.7.10 and pip 8.1.2 on Mac OSX 10.11.6

`pushd server/ && pip install -r requirements.txt && popd`

## run the example

```
$ python server/server.py &
$ sbt run
```

## the bug

Eventually, you'll get an exception that looks like this (may take many thousands of requests)

```
[error] (run-main-0) akka.http.impl.engine.client.PoolSlot$UnexpectedDisconnectException: Unexpected disconnect
akka.http.impl.engine.client.PoolSlot$UnexpectedDisconnectException: Unexpected disconnect
	at akka.http.impl.engine.client.PoolSlot$SlotProcessor$$anonfun$5$$anonfun$6.apply(PoolSlot.scala:217)
	at akka.http.impl.engine.client.PoolSlot$SlotProcessor$$anonfun$5$$anonfun$6.apply(PoolSlot.scala:217)
	at scala.Option.fold(Option.scala:158)
	at akka.http.impl.engine.client.PoolSlot$SlotProcessor$$anonfun$5.apply(PoolSlot.scala:217)
	at akka.http.impl.engine.client.PoolSlot$SlotProcessor$$anonfun$5.apply(PoolSlot.scala:215)
	at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:234)
	at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:234)
	at scala.collection.Iterator$class.foreach(Iterator.scala:893)
	at scala.collection.AbstractIterator.foreach(Iterator.scala:1336)
	at scala.collection.IterableLike$class.foreach(IterableLike.scala:72)
	at scala.collection.AbstractIterable.foreach(Iterable.scala:54)
	at scala.collection.TraversableLike$class.map(TraversableLike.scala:234)
	at scala.collection.AbstractTraversable.map(Traversable.scala:104)
	at akka.http.impl.engine.client.PoolSlot$SlotProcessor.handleDisconnect(PoolSlot.scala:221)
	at akka.http.impl.engine.client.PoolSlot$SlotProcessor$$anonfun$running$1.applyOrElse(PoolSlot.scala:201)
	at akka.actor.Actor$class.aroundReceive(Actor.scala:484)
	at akka.http.impl.engine.client.PoolSlot$SlotProcessor.akka$stream$actor$ActorSubscriber$$super$aroundReceive(PoolSlot.scala:92)
	at akka.stream.actor.ActorSubscriber$class.aroundReceive(ActorSubscriber.scala:201)
	at akka.http.impl.engine.client.PoolSlot$SlotProcessor.akka$stream$actor$ActorPublisher$$super$aroundReceive(PoolSlot.scala:92)
	at akka.stream.actor.ActorPublisher$class.aroundReceive(ActorPublisher.scala:325)
	at akka.http.impl.engine.client.PoolSlot$SlotProcessor.aroundReceive(PoolSlot.scala:92)
	at akka.actor.ActorCell.receiveMessage(ActorCell.scala:526)
	at akka.actor.ActorCell.invoke(ActorCell.scala:495)
	at akka.dispatch.Mailbox.processMailbox(Mailbox.scala:257)
	at akka.dispatch.Mailbox.run(Mailbox.scala:224)
	at akka.dispatch.Mailbox.exec(Mailbox.scala:234)
	at scala.concurrent.forkjoin.ForkJoinTask.doExec(ForkJoinTask.java:260)
	at scala.concurrent.forkjoin.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1339)
	at scala.concurrent.forkjoin.ForkJoinPool.runWorker(ForkJoinPool.java:1979)
	at scala.concurrent.forkjoin.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:107)
```