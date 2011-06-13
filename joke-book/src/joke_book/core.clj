(ns joke-book.core
  (:require [joke-book.routes :only app-routes]
            [appengine-magic.core :as ae]
            [compojure.handler :as handler]))
  
(ae/def-appengine-app joke-book-app (handler/api app-routes))