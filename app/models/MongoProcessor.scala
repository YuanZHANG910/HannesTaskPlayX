package models

import com.mongodb.casbah.Imports._
import models.Products._

/**
  * Created by yuanz on 05/10/2016.
  */

object MongoProcessor {

  def loadProductMapFromMongo(): Unit = {
    val mongoClient =  MongoClient()
    val db = mongoClient("super-shop")
    val productsCol = db("Products")
    val allDocs=productsCol.find()
    allDocs.toList.foreach(setProductDetail)
    mongoClient.close()
  }

}
