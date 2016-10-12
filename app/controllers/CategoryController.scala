package controllers

import models.Products
import play.api.mvc.{Action, Controller}

/**
  * Created by yuanz on 11/10/2016.
  */
class CategoryController extends Controller{

  def getCategory = Action {
    val category = Products.getCategory
    Ok(views.html.category(category))
  }

  def getProductsInAnCategory(catName: String) = Action {
    val products = Products.getProductsInACategory(catName)
    Ok(views.html.index(products))
  }

}
