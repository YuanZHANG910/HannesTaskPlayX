package controllers

import models.Products
import play.api.mvc.{Action, Controller}

class Application extends Controller {

  def index = Action {
    val p = Products.productsMap
    Ok(views.html.index(p.toMap))
  }

}