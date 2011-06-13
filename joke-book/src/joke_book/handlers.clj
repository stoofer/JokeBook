(ns joke-book.handlers
  (require [joke-book.view :as view]))

(defn random-joke []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (view/page "Random Joke" "I say, I say, I say" (view/joke-article view/stub-joke))})
	  
(defn new-joke []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str
	  "<html><body><p>TODO</p></body></html>")})

(defn not-found [] "<html><body><p>NOT FOUND</p></body></html>")