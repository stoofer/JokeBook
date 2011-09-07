(ns joke-book.cqrs.event-bus)

(def handlers(atom {}))

(defn clear-handlers! []
  (reset! handlers {}))

(defn register [name handler]
  (println (str "Registering event handler for " name))
  (swap! handlers conj {name handler}))

(defn publish-event [event-info]
  (println "TODO - implement event-bus/publish-event"))