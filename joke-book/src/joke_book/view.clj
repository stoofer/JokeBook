(ns joke-book.view
  (:use [hiccup.core :only (html)]
        [hiccup.page-helpers :only (include-css include-js link-to html5)]
        [hiccup.form-helpers :only (form-to text-area label submit-button)]))


(def default-stylesheets
  [
;   "/stylesheets/application.css"
])

(def default-javascripts
  [
;"/javascripts/jquery.js"
;   "/javascripts/application.js"
])

(defn- master-page [title heading body]
  (html5
    [:head
     [:title title]
     (apply include-css default-stylesheets)
     (apply include-js default-javascripts)
     ]
    [:body 
     [:header {:id "header"} [:h1 heading]]
     [:section {:id "content"} body]
     [:footer {:id "footer"} "Caborn jokebook. Just for fun, and to learn a little Clojure."]]))



(defn- joke-article[{:keys [lines]}]
  [:article (map #(vector :p %) lines)])

(def stub-joke {:lines '(
                          "What's small brown and sticky?"
                          "A stick!")})

(defn- joke-form []
   (form-to [:post "/joke/new"] 
    (label :lines "Joke") 
      (text-area :lines) 
    (submit-button "Add")))

(defn joke-page [joke]
  (master-page
    "Random Joke" 
    "I say, I say, I say" 
    (joke-article joke)))


(defn new-joke-page []
  (master-page
    "Tell us a joke" 
    "What's your joke?" 
    (joke-form)))