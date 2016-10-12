package controllers

import models.{Products, SearchProduct}
import play.api.Play.current
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.i18n.Messages
import play.api.i18n.Messages.Implicits._
import play.api.mvc.{Action, Controller, Flash}

/**
  * Created by yuanz on 10/10/2016.
  */
class SearchController extends Controller {

  val SearchForm : Form[SearchProduct] = {
    Form(
      mapping(
        "SearchIN" -> nonEmptyText
      )
      (SearchProduct.apply)(SearchProduct.unapply)
    )
  }

  def search = Action {
    implicit request =>
      val searchRequest = SearchForm.bindFromRequest()
      searchRequest.fold(
        hasErrors = {
        form =>
          Redirect(routes.HomeController.home()).flashing()
      },
        success = {
        newSearch =>
          Redirect(controllers.routes.SearchController.listResult(searchRequest.value.get.name))
      }
      )
  }

  def listResult(Searched: String) = Action {
    implicit request =>
      Products.getProductByName(Searched) match {
        case Some(p) => Ok(views.html.listResult(p.toString))
        case _ => Ok(views.html.listResult("not found"))
      }
  }
}

