(ns joke-book.handlers
  (require [joke-book.view :as view]))

(defn random-joke []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (view/joke-page view/stub-joke) })
	  
(defn new-joke []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (view/new-joke-page)})

(defn add-joke [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "<p>TODO</p>"})

(defn not-found [] 
  "<html><body><p>NOT FOUND</p></body></html>")