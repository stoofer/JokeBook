(ns joke-book.routes
  (:use compojure.core)
  (:require [joke-book.handlers :as h]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (h/random-joke))
  (GET "/joke/new" [] (h/new-joke))
  (POST "/joke/new" [request] (h/add-joke request))
  (route/files "/static/")
  (route/not-found (h/not-found)))