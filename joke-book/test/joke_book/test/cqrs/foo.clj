(ns joke-book.test.cqrs.foo
  (:use [joke-book.cqrs.core]))

(defn create [ignored]
  (apply-event! :foo-created {} ignored))

(defn register []
  (printf "Registering events for foo...")
  (on-event foo-created [aggregate data]
    (conj aggregate {:type :foo})))