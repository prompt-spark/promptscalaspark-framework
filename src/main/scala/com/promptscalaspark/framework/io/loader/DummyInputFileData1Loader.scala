/*
 * Copyright © 2019 Abhishek Verma (abhishekv3007@gmail.com)
 *
 *                    GNU AFFERO GENERAL PUBLIC LICENSE
 *                       Version 3, 19 November 2007
 * Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *                            Preamble
 *
 *   The GNU Affero General Public License is a free, copyleft license for
 * software and other kinds of works, specifically designed to ensure
 * cooperation with the community in the case of network server software.
 *
 *   The licenses for most software and other practical works are designed
 * to take away your freedom to share and change the works.  By contrast,
 * our General Public Licenses are intended to guarantee your freedom to
 * share and change all versions of a program--to make sure it remains free
 * software for all its users.
 */

package com.promptscalaspark.framework.io.loader

import com.promptscalaspark.framework.io.ioSchema.InputSchema.DummyInputFileData1
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Dataset, SparkSession}

object DummyInputFileData1Loader extends Serializable {

  def loadInputFileData1DS(path: String): Dataset[DummyInputFileData1] = {

    val inputFileData1Path = path

    /**
      * setting SparkConf with application name and allowing multiple spark context to run
      */
    val sparkConf = new SparkConf()
      .setAppName("prompt-spark-framework")
      .set("spark.driver.allowMultipleContexts", "true")

    /**
      * The master URL to connect to, such as "local" to run locally with one thread, "local[*]" to
      * run locally with 4 cores, or "spark://master:7077" to run on a Spark standalone cluster.
      */
    val spark =
      SparkSession
        .builder()
        .config(sparkConf)
        .getOrCreate()

    /**
      * Load input data file in Spark Dataset and bound it with ioSchema Case class
      */
    val inputFileDataRawDF =
      spark.read.format("").load(inputFileData1Path)

    import spark.implicits._

    inputFileDataRawDF
      .as[DummyInputFileData1]

  }
}
