(ns joke-book.cqrs.core
  (:require 
    [joke-book.cqrs.command-bus :as command-bus]
    [joke-book.cqrs.event-bus :as event-bus]))

(defn reset-all! []
  "For testing - clear out the cqrs system to allow reregistering of handlers"  
  (event-bus/reset-bus!)
  (command-bus/reset-bus!))
  
(defn command-handlers []
  "List all registered command handlers"
  @command-bus/handlers)

(defn event-handlers []
  "List all registered event handlers"
  @event-bus/handlers)

(defmacro command-handler [command & fnhandler]
  "Register a command handler"
  `(do
     (defn ~command ~@fnhandler)
     (command-bus/register (keyword '~command) ~command)))

(defmacro event-handler [event & fnhandler]
  "Register an event handler"  
  `(do
     (defn ~event ~@fnhandler)
     (event-bus/register (keyword '~event) ~event)))
