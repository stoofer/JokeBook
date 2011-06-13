(ns joke-book.jokes
  (:require [clojure.string :only [split-lines] :as string])
  (:use [clj-time.core :only [now]]))

(def jokes (atom []))

(defn- approved-jokes []
  (vec (filter #(= :approved (:status %)) @jokes)))

(defn random []
  (rand-nth (approved-jokes)))

(defn parse [{:keys [lines]}]
  {
   :lines (string/split-lines lines)
   :status :unmoderated
   :created (now)
  })

(defn save [joke]
  (do 
    (swap! jokes conj joke)
    joke))

;TODO - move this out
;Test data only
(def canned-jokes '("What's small brown and sticky?\nA stick!" 
                    "I say, I say, I say\nMy dog has no nose.\nHow does he smell?\nAwful!"
                   "What's black, white and red all over?\nA sunburnt penguin."))
(defn line-to-joke [line]
  (-> {:lines line}
      parse
      (assoc :status :approved)
      save))

(doall (map line-to-joke canned-jokes))