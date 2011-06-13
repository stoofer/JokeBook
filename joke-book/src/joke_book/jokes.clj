(ns joke-book.jokes
  (:require [clojure.string :only [split-lines] :as string]))

(defn random []
  {:lines '("What's small brown and sticky?"
            "A stick!")})

(defn parse [{:keys [lines]}]
  {:lines (string/split-lines lines)})

(defn save [joke]
  (defn random [] joke))