package controllers

import models.Products
import play.api.mvc.{Action, Controller}

/**
  * Created by yuanz on 10/10/2016.
  */
class HomeController extends Controller {

  def home = Action {
    val p = Products.productsMap
    Ok(views.html.index(p.toMap))
  }

}
