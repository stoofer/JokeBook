(ns joke-book.core
  (:require [appengine-magic.core :as ae]))


(defn joke-book-app-handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body "Hello, world!"})


(ae/def-appengine-app joke-book-app #'joke-book-app-handler)