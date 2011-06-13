(ns joke-book.core
  (:require [joke-book.routes :only app-routes])
  (:require [appengine-magic.core :as ae]))
  
(ae/def-appengine-app joke-book-app #'joke-book.routes/app-routes)