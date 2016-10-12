package models

import scala.annotation.tailrec

/**
  * Created by yuanz on 11/10/2016.
  */
case class ProductInTrolley(name: String, price: String, mainImage: String, qty: String)

class Trolley {

  var productsInTrolley:List[ProductInTrolley] = List.empty

  def getTotal: Double = {
    var sum = 0.00
    for (p <- productsInTrolley) {
      sum += p.qty.toInt * p.price.toDouble
    }
    sum
  }

  def addProductsToTrolley(product:Product, qty: String): List[ProductInTrolley]  = {
    def checkTrolley (productsList: List[ProductInTrolley], product: Product): List[ProductInTrolley] = {
      if (productsList.isEmpty) productsList
      else if (product.name == productsList.head.name) checkTrolley(productsList.tail, product)
      else checkTrolley(productsList.tail, product) :+ productsList.head
    }
    productsInTrolley = checkTrolley(productsInTrolley, product)
    productsInTrolley = productsInTrolley :+ ProductInTrolley(product.name, product.price.toString, product.mainImage, qty)
    productsInTrolley
  }

  def removeFromTrolley(productName: String): List[ProductInTrolley]  = {
    def checkTrolley (productsList: List[ProductInTrolley], productName: String): List[ProductInTrolley] = {
      if (productsList.isEmpty) productsList
      else if (productName == productsList.head.name) checkTrolley(productsList.tail, productName)
      else checkTrolley(productsList.tail, productName) :+ productsList.head
    }
    productsInTrolley = checkTrolley(productsInTrolley, productName)
    productsInTrolley
  }
}
