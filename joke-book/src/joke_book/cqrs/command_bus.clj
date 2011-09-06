(ns joke-book.cqrs.command-bus
  (:require [joke-book.cqrs.repository :as repository]))

(def handlers(atom {}))

(defn reset-bus! []
  (reset! handlers {}))

(defn register [name handler]
  (swap! handlers conj {name handler}))


(defn execute [name args]
  (repository/unit-of-work
    ((@handlers name) args)))
