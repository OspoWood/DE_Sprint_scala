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

case class Employer(
                id: String,
                name: String,
                url: String,
                alternate_url: String,
                logo_urls: LogoUrls,
                vacancies_url: String,
                trusted: Boolean
              )



case class LogoUrls(

                     original: String
                   )



case class Snippet(
                    requirement: String,
                    responsibility: String
                  )