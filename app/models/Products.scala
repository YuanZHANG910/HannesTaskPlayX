package models

import com.mongodb.casbah.Imports._
import MongoProcessor._

/**
  * Created by yuan on 30/09/16.
  */

case class Product(name: String, price: Double, category: String, mainImage: String)

case class SearchProduct(name: String)

class Products {

  val productsMap = scala.collection.mutable.Map[String, Product]()

  def getProductByName(name: String): Option[Product] = {
    productsMap.get(name.toLowerCase)
  }

  def getCategory: List[String] = {
    productsMap.map(_._2.category).toList.distinct.sorted
  }

  def getProductsInACategory(catName: String): Map[String, Product] = {
    productsMap.filter(_._2.category==catName).toMap
  }

  def setProductDetail(l:DBObject): Unit = {
    productsMap += (l.toList(1)._2.toString.toLowerCase -> Product(l.toList(1)._2.toString, l.toList(2)._2.toString.toDouble, l.toList(3)._2.toString, l.toList(4)._2.toString))
  }
}

object Products extends Products {
  loadProductMapFromMongo()
}