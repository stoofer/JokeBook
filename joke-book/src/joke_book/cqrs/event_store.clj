(ns joke-book.cqrs.event-store)

(def fake-store (atom ()))

(defn commit [events]
  (swap! fake-store concat (seq events))) 