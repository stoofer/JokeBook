(ns joke-book.view
  (:use [hiccup.core :only (html)]
        [hiccup.page-helpers :only (include-css include-js link-to html5 unordered-list url)]
        [hiccup.form-helpers :only (form-to text-field text-area label submit-button)]))


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
     [:header 
      [:h1 "Jokebook"]
      [:nav (unordered-list 
              (list 
                (link-to (url "/") "Random Joke!")
                (link-to (url "/joke/new") "New Joke")))  ]]
     [:section {:id "content"}
      [:h1 heading]      
      body]
     [:footer {:id "footer"} "Caborn jokebook. Just for fun, and to learn a little Clojure."]]))

(defn- joke-article[{:keys [lines]}]
  [:article (map #(vector :p %) lines)])



(defn- joke-form []
   (form-to [:post "/joke/new"] 
     (label :lines "Joke") 
     (text-area :lines) 
     (submit-button "Submit")))

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

(defn joke-created [joke]
  (master-page
    "Thanks" 
    "Saved" 
    [:section
     (joke)
     [:article "Your joke has been created, we're just checking it isn't too naughty for this site"]]))
