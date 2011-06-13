(ns joke-book.handlers
  (require [joke-book.view :as view]))

(defn ok[body]
  {:status 200 :headers {"Content-Type" "text/html"} :body body})

(defn random-joke [](ok (view/joke-page view/stub-joke)))
	  
(defn new-joke [](ok (view/new-joke-page)))

(defn add-joke [request]
  (ok "<p>TODO</p>"))

(defn not-found [] 
  "<html><body><p>NOT FOUND</p></body></html>")