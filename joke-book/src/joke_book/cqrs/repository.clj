(ns  joke-book.cqrs.repository
  (:require 
    [joke-book.cqrs.event-bus :as bus]
    [joke-book.cqrs.event-store :as event-store]))

(declare *unit-of-work*)

(defn publish-results []
  (map bus/publish-event @*unit-of-work*))

(defmacro unit-of-work [& body]
  `(binding [*unit-of-work* (atom {})]
     (do 
       ~@body
       (event-store/commit)
       (publish-results))))


;==============ENTITY???===========================================
(def domain-event-handlers (atom {}))

(defn clear-handlers! [] 
  (reset! domain-event-handlers {}))

(defn register-domain-event-handler [event-name handler]
  (swap! domain-event-handlers conj {event-name handler}))

(defn update-domain [e a d]
  ((@domain-event-handlers e) e a d))


(defn apply-event! [event aggregate event-data]
  (swap! *unit-of-work* conj {:event event, :aggregate aggregate, :event-data event-data})
  (update-domain event aggregate event-data))



    