package com.sprint.desprintscala.remote.model

import scala.reflect.internal.util.TriState.False

case class Item(
                 id: String,
                 name: String,
                 salary: Salary,
                 address: String,
                 response_url: String,
                 published_at: String,
                 created_at: String,
                 archived: Boolean,
                 apply_alternate_url: String,
                 url: String,
                 contacts: String
               ){
  def this(id: String, name: String, salary:Salary, address:String) ={
    this(id,name, salary,address ,"","","",false,"","","")
  }


}
