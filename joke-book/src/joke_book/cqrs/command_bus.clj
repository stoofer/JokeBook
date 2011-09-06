(ns joke-book.cqrs.command-bus
  (:require [joke-book.cqrs.repository :as repository]))

(def command-bus (atom {}))

(defn register-handler-with-bus [command-name handler]
  (swap! command-bus conj {command-name handler}))

(defmacro command-handler [command & fnhandler]
  `(do
     (defn ~command ~@fnhandler)
     (register-handler-with-bus (keyword '~command) ~command)))

(defn execute-command [command args]
  (repository/unit-of-work
    ((@command-bus command) args)))
