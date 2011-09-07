(ns joke-book.test.cqrs.core
  (:use [joke-book.cqrs.core])
  (:use [clojure.test])
  (:require [joke-book.test.cqrs.foo :as foo]))

(deftest can-register-command-handler
  (reset-all!)
  (command-handler test-command []())
  (is (= 1 (count (command-handlers)))))

(deftest can-register-event-handler
  (reset-all!)
  (event-handler test-event1[]())
  (event-handler test-event2[]())
  (is (= 2 (count (event-handlers))))
  (is (function? (:test-event1 (event-handlers))))
  (is (nil? (:test-event-missing (event-handlers)))))

(deftest can-register-domin-event-handler
  (reset-all!)
  (on-event domain-event[]())
  (is (= 1 (count (domain-event-handlers))))
  (is (function? (:domain-event (domain-event-handlers)))))

(deftest reset-clears-out-everything
  (reset-all!)
	(command-handler test-command []())
  (event-handler test-event []())
	(reset-all!)
  (is (= 0  (count (command-handlers))))
  (is (= 0  (count (domain-event-handlers))))
  (is (= 0  (count (event-handlers)))))
            
(deftest invoking-command-calls-handler
  (reset-all!)
  (let [called (atom {})
        cmd-args {:some :command-data}]
    (command-handler cmd [a] (reset! called a))
    (execute-command :cmd cmd-args)
    (is (= cmd-args @called))))

(deftest creating-aggregate-fires-event-to-subscribers
  (reset-all!)
  (foo/register)
  (command-handler create-foo [args] 
    (foo/create args))
  (let [called (atom {})]
    (event-handler foo-created [a] (reset! called a))
    (execute-command :create-foo {})
    (is (= :foo-created (keys @called)))))


;(deftest command-can-load-an-aggregate