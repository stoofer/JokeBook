(ns joke-book.cqrs.event-bus)

(def handlers(atom {}))

(defn clear-handlers! []
  (reset! handlers {}))

(defn register [name handler]
  (swap! handlers conj {name handler}))

(defn publish-event [event-info]
  (printf "TODO - implement event-bus/publish-event"))