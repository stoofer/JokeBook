(ns joke-book.cqrs.repository
  (:use [clojure.pprint])
  (:require 
    [joke-book.cqrs.event-bus :as bus]
    [joke-book.cqrs.event-store :as event-store]))

(declare *unit-of-work*)

(defn publish-results []
  (println "Publishing results....")
  (doall (map bus/publish-event @*unit-of-work*)))

(defmacro unit-of-work [& body]
  `(binding [*unit-of-work* (atom ())]
     (do 
       ~@body
       (event-store/commit @*unit-of-work*)
       (publish-results))))


;==============ENTITY???===========================================
(def domain-event-handlers (atom {}))

(defn clear-handlers! [] 
  (reset! domain-event-handlers {}))

(defn register-domain-event-handler [event-name handler]
  (println (str "Registering domain handler for " event-name))
  (swap! domain-event-handlers conj {event-name handler}))

(defn update-domain [event agg data]
  (let [handler (@domain-event-handlers event)]
    (if handler
      (handler agg data)
      (println "EEK - No domain handler found for event " 
               event 
               " I only have " 
               (keys @domain-event-handlers)))))


(defn apply-event! [event aggregate event-data]
  (println "Applying event " event)
  (swap! *unit-of-work* conj {:event event, :aggregate aggregate, :event-data event-data})
  (pprint @*unit-of-work*)
  (update-domain event aggregate event-data))


    