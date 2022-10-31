package com.sprint.desprintscala.remote.model;


case class Area(
                 id: String,
                 name: String,
                 url: String
               )

case class Department(
                       id: String,
                       name: String
                     )

case

class Employer(
                id: String,
                name: String,
                url: String,
                alternate_url: String,
                logo_urls: LogoUrls,
                vacancies_url: String,
                trusted: Boolean
              )

case class Items(
                  id: String,
                  premium: Boolean,
                  name: String,
                  department: Department,
                  has_test: Boolean,
                  response_letter_required: Boolean,
                  area: Area,
                  salary: String,
                  typ: Department,
                  address: String,
                  response_url: String,
                  sort_point_distance: String,
                  published_at: String,
                  created_at: String,
                  archived: Boolean,
                  apply_alternate_url: String,
                  insider_interview: String,
                  url: String,
                  adv_response_url: String,
                  alternate_url: String,
                  relations: Seq[String],
                  employer: Employer,
                  snippet: Snippet,
                  contacts: String,
                  schedule: Department,
                  accept_temporary: Boolean
                )

case class LogoUrls(

                     original: String
                   )

case class RootInterface(
//                          items: Seq[Items],
//                          found: Int,
//                          pages: Int,
//                          per_page: Int,
//                          page: Int,
//                          clusters: String,
//                          arguments: String,
//                          alternate_url: String
                        )

case class Snippet(
                    requirement: String,
                    responsibility: String
                  )