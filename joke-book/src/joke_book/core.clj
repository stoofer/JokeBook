(ns joke-book.core
  (:use
    [ring.middleware.keyword-params]
    [ring.middleware.params]
    [ring.middleware.nested-params]
    [appengine-magic.multipart-params :only [wrap-multipart-params]])
  (:require 
    [joke-book.routes :only app-routes]
    [appengine-magic.core :as ae]))
  
(ae/def-appengine-app joke-book-app
  (-> 
    joke-book.routes/app-routes
    (wrap-keyword-params)
    (wrap-nested-params)
    (wrap-params)
    (wrap-multipart-params)))