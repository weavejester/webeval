(ns example.handler
  (:require [compojure.api.sweet :as api :refer [context GET]]
            [compojure.api.async]
            [clojure.core.async :as a :refer [go <!]]
            [clojure.java.jdbc :as jdbc]
            [postgres.async :as pg]
            [ring.adapter.jetty :as jetty]
            [ring.util.http-response :as response]))

(def db
  (pg/open-db {:hostname "localhost"
               :database "cgweb_dev"
               :username "postgres"
               :password "password"}))

(def app
  (api/api
   (context "/api" []
     :tags ["api"]
     (GET "/users" []
      :summary "Gets a list of users"
      (go (response/ok (<! (pg/query! db ["select id, email from users limit 10"])))))
     
     (GET "/test" []
      :summary "Just a test endpoint"
      :return {:result Long}
      (response/ok {:result 1})))))

(defn -main [& args]
  (jetty/run-jetty app {:port 3000, :async? true}))
