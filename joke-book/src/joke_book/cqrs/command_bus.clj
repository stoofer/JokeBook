(ns joke-book.cqrs.command-bus
  (:use [joke-book.cqrs.repository :only [unit-of-work]]))

(def handlers(atom {}))

(defn clear-handlers! []
  (reset! handlers {}))

(defn register [name handler]
  (swap! handlers conj {name handler}))

(defn execute [name args]
  (unit-of-work
    ((@handlers name) args)))
