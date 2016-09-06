## what is this?

This repo contains a test of scalaj in comparison w/ akka as an HTTP client (for the purposes of reproducing a bug)

## install

You need Python & Pip installed to run this example. I used Python 2.7.10 and pip 8.1.2 on Mac OSX 10.11.6

`pushd server/ && pip install -r requirements.txt && popd`

## run the example

```
$ python server/server.py &
$ cd client/ && sbt run
```