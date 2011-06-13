(ns joke-book.jokes
  (:require [clojure.string :only [split-lines] :as string])
  (:use [clj-time.core :only [now]]))

(def jokes (atom []))

(defn random []
  (rand-nth @jokes))

(defn parse [{:keys [lines]}]
  {
   :lines (string/split-lines lines)
   :status :unmoderated
   :created (now)
  })

(defn save [joke]
  (swap! jokes conj joke))

;TODO - move this out
;Test data only
(def canned-jokes '("What's small brown and sticky?\nA stick!" 
                    "I say, I say, I say\nMy dog has no nose.\nHow does he smell?\nAwful!"
                   "What's black, white and red all over?\nA sunburnt penguin."))
(defn line-to-joke [line]
  (-> {:lines line}
      parse
      save))

(doall (map line-to-joke canned-jokes))