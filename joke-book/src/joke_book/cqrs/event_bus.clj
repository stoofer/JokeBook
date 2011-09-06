(ns joke-book.cqrs.event-bus)

(def event-bus (atom {}))

(defn register-handler-with-bus [event-name handler]
  (swap! event-bus conj {event-name handler}))

(defmacro event-handler [event & fnhandler]
  `(do
     (defn ~event ~@fnhandler)
     (register-handler-with-bus (keyword '~event) ~event)))

(defn publish-event [event-info]
  (printf "TODO - implement event-bus/publish-event"))