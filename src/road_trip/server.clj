(ns road-trip.server
  (:require [somnium.congomongo :as mongo]))

(def conn
  (delay
    (mongo/make-connection "mydb"
                           :host "127.0.0.1"
                           :port 27017)))

(mongo/set-connection! @conn)

; (defn seed-app
;   []
;   (mongo/insert! :users {:username "trinity"})
;   (mongo/insert! :albums {:title "Peru"})
;   )

(defn find-user
  [username]
  (mongo/fetch-one :users :where {:username username}))

(defn find-user-album
  [username album-title]
  (let [user (find-user username)]
    (filter (:albums user))
  )
  (mongo/fetch-one :users :where {:username username}))

(defn insert-tile
  [tile-url]
  (mongo/insert! :tiles {:url tile-url}))

(defn find-user-albums
  [username]
  (let [album-ids (:albums (find-user username))]
    (map #(mongo/fetch-one :albums :where {:_id %} :as :json) album-ids)))

(defn find-album-photos
  [username album-title]
    (let [user (find-user username)]
    ))

(defn create-album
  [album-title username]
  (let [
    ; album-id (mongo/insert! :albums {:title album-title :photos []})
        user (find-user username)]
        (println "im the user")
        ; USERS.update({username: params[:username]}, {"$push"=> {albums: album_id }})
    ; (mongo/update! :users user )
    ))
; db.users.update({"username":"trinity"},{"$push": {"albums":{
(defn add-photo-to-album
  [username album-title file]
  (let [album (mongo/fetch-one :albums :where {:album_title album-title})]
  (mongo/update! :albums album {})))
