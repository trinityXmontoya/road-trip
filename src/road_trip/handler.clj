(ns road-trip.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :refer [wrap-json-response]]
            [clojure.java.io :as io]
            [selmer.parser :as selmer :refer [render-file]]
            [road-trip.server :as server]))

(selmer/set-resource-path! (io/resource "../resources/public/views/"))

(defroutes app-routes
  (GET "/" [] (render-file "home.html" {}))
  (GET "/go" [] (render-file "go.html" {}))

  (context "/:username/albums" [username]
    (GET "/" [] (render-file "albums.html" {}))
    (GET "/:album-title/photos" [] (render-file "photos.html" {})))

  (context "/api" []
    (POST "/tiles" [] (server/insert-tile tile-url))

    (context "/:username/albums" [username]
      (GET "/" [] (server/find-user-albums username))
      (POST "/new" [] (server/create-album 1 username))
      (GET "/:album-title/photos" [album-title] (server/find-album-photos username album-title))
      (GET "/:album-title/add_to_album" [album-name] (server/add-photo-to-album username album-title 1))))

  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults (assoc site-defaults :security {:anti-forgery true}))
      (wrap-json-response)))
