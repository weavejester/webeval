(ns example.handler
  (:require [compojure.api.sweet :as api :refer [context GET]]
            [clojure.java.jdbc :as jdbc]
            [hikari-cp.core :as hikari-cp]
            [org.httpkit.server :as httpkit]
            [ring.util.http-response :as response]))

(def db
  {:datasource
   (hikari-cp/make-datasource
    {:jdbc-url "jdbc:postgresql://localhost:5432/cgweb_dev"})})

(def app
  (api/api
   (context "/api" []
     :tags ["api"]
     (GET "/users" []
      :summary "Gets a list of users"
      (response/ok (jdbc/query db ["select id, email from users limit 10"])))
     
     (GET "/test" []
      :summary "Just a test endpoint"
      :return {:result Long}
      (response/ok {:result 1})))))

(defn -main [& args]
  (httpkit/run-server app {:port 3000, :thread 50 :queue-size 2e5}))
