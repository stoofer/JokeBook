(ns joke-book.cqrs.core
  (:require 
    [joke-book.cqrs.command-bus :as command-bus]
    [joke-book.cqrs.event-bus :as event-bus]
    [joke-book.cqrs.repository :as repository]))

(defn reset-all! []
  "For testing - clear out the cqrs system to allow reregistering of handlers"  
  (event-bus/clear-handlers!)
  (command-bus/clear-handlers!)
  (repository/clear-handlers!))
  
(defn command-handlers []
  "List all registered command handlers"
  @command-bus/handlers)

(defn event-handlers []
  "List all registered event handlers"
  @event-bus/handlers)

(defn domain-event-handlers []
  "List all registered domain event handlers"
  @repository/domain-event-handlers)

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

(defmacro on-event [event & fnhandler]
  "Register a domain event handler for an entity"
  `(do
     (defn ~event ~@fnhandler)
     (repository/register-domain-event-handler (keyword '~event) ~event)))

(defn execute-command [command args]
  (command-bus/execute command args))