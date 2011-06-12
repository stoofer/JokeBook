(ns joke-book.core
  (:use compojure.core)
  (:require [joke-book.handlers :as h]
            [appengine-magic.core :as ae]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/" [] (h/random-joke))
  (GET "/joke/new" [] (h/new-joke))
  (route/not-found (h/not-found)))
  
(ae/def-appengine-app joke-book-app #'app-routes)