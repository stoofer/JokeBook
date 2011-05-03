(ns joke-book.core
  (:use compojure.core)
  (:require [appengine-magic.core :as ae]))


(defn joke-book-app-handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str
	  "<html><body><p>What's small brown and sticky?</p>"
	  "<p>A stick!</p></body></html>")})


(ae/def-appengine-app joke-book-app #'joke-book-app-handler)