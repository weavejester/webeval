(defproject example "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [metosin/compojure-api "1.2.0-alpha6"]
                 [ring "1.6.1"]
                 [compojure "1.6.0"]
                 [manifold "0.1.6"]
                 [org.clojure/core.async "0.3.442"]
                 [alaisi/postgres.async "0.8.0"]
                 [http.async.client "1.2.0"]
                 [org.clojure/java.jdbc "0.7.0-alpha3"]
                 [org.postgresql/postgresql "9.4.1212.jre7"]]
  :main example.handler)
