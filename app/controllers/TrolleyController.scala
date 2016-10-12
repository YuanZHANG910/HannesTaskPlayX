package controllers

import models.{ProductInTrolley, Products, Trolley}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}

/**
  * Created by yuanz on 11/10/2016.
  */
class TrolleyController extends Controller  {

  val trolley = new Trolley

  val orderForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "price" -> nonEmptyText,
      "mainImage" -> nonEmptyText,
      "qty" -> nonEmptyText
    )(ProductInTrolley.apply)(ProductInTrolley.unapply)
  )

  def updateTrolley() =  Action {
    implicit request =>
      val product = Products.getProductByName(orderForm.bindFromRequest().data("name")).get
      val qty = orderForm.bindFromRequest().data("qty")
      trolley.addProductsToTrolley(product,qty)
      Redirect(routes.HomeController.home())
  }

  def updateTrolleyFromTrolley() =  Action {
    implicit request =>
      val sum = trolley.getTotal
      val product = Products.getProductByName(orderForm.bindFromRequest().data("name")).get
      val qty = orderForm.bindFromRequest().data("qty")
      trolley.addProductsToTrolley(product,qty)
      Ok(views.html.trolley(trolley.productsInTrolley)(sum))
  }

  def loadTrolley() = Action {
    val sum = trolley.getTotal
    Ok(views.html.trolley(trolley.productsInTrolley)(sum))
  }

  def removerAProductByName(productName: String) = Action {
    trolley.removeFromTrolley(productName)
    val sum = trolley.getTotal
    Ok(views.html.trolley(trolley.productsInTrolley)(sum))
  }
}
