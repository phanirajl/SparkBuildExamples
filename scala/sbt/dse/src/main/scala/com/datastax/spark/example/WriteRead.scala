package com.datastax.spark.example

import org.apache.spark.{SparkConf, SparkContext}
import com.datastax.driver.core.utils.UUIDs

// For DSE it is not necessary to set connection parameters for spark.master (since it will be done
// automatically)
object WriteRead extends App {

  val conf = new SparkConf()
    .setAppName("Datastax Scala example")

  // A SparkContext
  val sc = new SparkContext(conf)

  val count = sc.parallelize((for(i <- 0 to 100000) yield i), 12).map(_ =>UUIDs.timeBased()).distinct.count

  println(count)

  sc.stop()
  sys.exit(0)
}