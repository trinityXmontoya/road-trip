(defproject road-trip "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.0"]
                 [ring/ring-defaults "0.2.0"]
                 [ring/ring-json "0.4.0"]
                 [environ "1.0.3"]
                 [clj-time "0.11.0"]
                 [selmer "1.0.4"]
                 [congomongo "0.4.8"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-environ "1.0.3"]]
  :ring {:handler road-trip.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
