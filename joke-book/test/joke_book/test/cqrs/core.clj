(ns joke-book.test.cqrs.core
  (:use [joke-book.cqrs.core])
  (:use [clojure.test]))

(deftest can-register-command-handler
  (reset-all!)
  (command-handler test-command []())
  (is (= 1 (count (command-handlers)))))

(deftest can-register-event-handler
  (reset-all!)
  (event-handler test-event1[]())
  (event-handler test-event2[]())
  (is (= 2 (count (event-handlers)))))

(deftest can-register-domin-event-handler
  (reset-all!)
  (on-event domain-event[]())
  (is (= 1 (count (domain-event-handlers)))))

(deftest reset-clears-out-everything
  (reset-all!)
	(command-handler test-command []())
  (event-handler test-event []())
	(reset-all!)
  (is (= 0  (count (command-handlers))))
  (is (= 0  (count (domain-event-handlers))))
  (is (= 0  (count (event-handlers)))))
            

