(ns joke-book.handlers)

(defn random-joke []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str
	  "<html><body><p>What's small brown and sticky?</p>"
	  "<p>A stick!</p></body></html>")})


(defn new-joke []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str
	  "<html><body><p>TODO</p></body></html>")})

(defn not-found [] "<html><body><p>NOT FOUND</p></body></html>")