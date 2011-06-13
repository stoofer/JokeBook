(ns joke-book.handlers
  (require [joke-book.view :as view]
           [joke-book.jokes :as jokes]))

(defn ok [body]
  {:status 200 :headers {"Content-Type" "text/html"} :body body})

(defn random-joke []
  (-> (jokes/random)
      view/joke-page
      ok))
	  
(defn new-joke [] (ok (view/new-joke-page)))

(defn add-joke [{:keys [params]}]
  (-> params
      jokes/parse
      jokes/save
      view/joke-created
      ok))
      
(defn not-found [] 
  "<html><body><p>NOT FOUND</p></body></html>")