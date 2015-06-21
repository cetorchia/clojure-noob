(ns clojure-noob.radial-parts
  (:gen-class))

(def asym-body-parts [{:name "head" :size 3}
                      {:name "-eye" :size 1}
                      {:name "-ear" :size 1}
                      {:name "mouth" :size 1}
                      {:name "nose" :size 1}
                      {:name "neck" :size 2}
                      {:name "-shoulder" :size 3}
                      {:name "-upper-arm" :size 3}
                      {:name "chest" :size 10}
                      {:name "back" :size 10}
                      {:name "-forearm" :size 3}
                      {:name "abdomen" :size 6}
                      {:name "-kidney" :size 1}
                      {:name "-hand" :size 2}
                      {:name "-knee" :size 2}
                      {:name "-thigh" :size 4}
                      {:name "-lower-leg" :size 3}
                      {:name "-achilles" :size 1}
                      {:name "-foot" :size 2}])

(defn needs-matching-part?
  [part]
    (re-find #"^-" (:name part)))

(defn make-matching-parts
  [part sides]
  (map
    (fn [side]
      {:name (clojure.string/replace (:name part) #"^-" (str side "-"))
       :size (:size part)})
    sides))

(defn symmetrize-body-parts-old
  [asym-body-parts sides]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining-asym-parts] remaining-asym-parts]
        (if (needs-matching-part? part)
          (recur remaining-asym-parts (into final-body-parts (make-matching-parts part sides)))
          (recur remaining-asym-parts (conj final-body-parts part)))))))

(defn symmetrize-body-parts
  [asym-body-parts sides]
  (mapcat 
    (fn [part]
      (if (needs-matching-part? part)
        (make-matching-parts part sides)
        [part]))
    asym-body-parts))
