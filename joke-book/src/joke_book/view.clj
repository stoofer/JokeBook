(ns joke-book.view
  (:use [hiccup.core :only (html)]
        [hiccup.page-helpers :only (include-css include-js link-to)]))

(def default-stylesheets
  [
;   "/stylesheets/application.css"
])

(def default-javascripts
  [
;"/javascripts/jquery.js"
;   "/javascripts/application.js"
])

(defn page [title heading body]
  (html
    [:head
     [:title title]
     (apply include-css default-stylesheets)
     (apply include-js default-javascripts)
     ]
    [:body 
     [:header {:id "header"} [:h1 heading]]
     [:section {:id "content"} body]
     [:footer {:id "footer"} "Caborn jokebook. Just for fun, and to learn a little Clojure."]]))

(defn joke-article[{:keys [lines]}]
  [:article (map #(vector :p %) lines)])

(def stub-joke {:lines '(
                          "What's small brown and sticky?"
                          "A stick!")})
