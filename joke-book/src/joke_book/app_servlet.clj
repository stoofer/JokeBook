(ns joke-book.app_servlet
  (:gen-class :extends javax.servlet.http.HttpServlet)
  (:use joke-book.core)
  (:use [appengine-magic.servlet :only [make-servlet-service-method]]))


(defn -service [this request response]
  ((make-servlet-service-method joke-book-app) this request response))
